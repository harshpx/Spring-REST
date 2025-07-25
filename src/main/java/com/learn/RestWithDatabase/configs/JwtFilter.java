package com.learn.RestWithDatabase.configs;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.RestWithDatabase.dto.CommonResponse;
import com.learn.RestWithDatabase.services.JWTService;
import com.learn.RestWithDatabase.services.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

  @Autowired
  private JWTService jwtService;

  @Autowired
  ApplicationContext context;

  @Autowired
  public ObjectMapper objectMapper;

  @SuppressWarnings("null")
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    try {
      String authHeader = request.getHeader("Authorization");
      String token = null;
      String username = null;

      if (authHeader != null && authHeader.startsWith("Bearer ")) {
        token = authHeader.substring(7);
        username = jwtService.extractUsername(token);
      }

      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

        UserDetails userDetails = context.getBean(UserDetailsServiceImpl.class).loadUserByUsername(username);

        if (jwtService.validateToken(token, userDetails)) {
          UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
              userDetails, null, userDetails.getAuthorities());
          usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        } else {
          throw new ExpiredJwtException(null, null, "JWT Token has expired");
        }
      }
      filterChain.doFilter(request, response);

    } catch (ExpiredJwtException e) {
      CommonResponse<String> errorResponse = new CommonResponse<>("JWT Token Expired", HttpStatus.UNAUTHORIZED.value());
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.setContentType("application/json");
      response.getWriter().write(objectMapper.writeValueAsString(errorResponse));

    } catch (Exception e) {
      CommonResponse<String> errorResponse = new CommonResponse<>("Unexpected Error", HttpStatus.BAD_REQUEST.value());
      response.setStatus(HttpStatus.BAD_REQUEST.value());
      response.setContentType("application/json");
      response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
  }
}
