package com.learn.RestWithDatabase.services;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.learn.RestWithDatabase.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

  @Value("${jwt.secret}")
  public String jwtSecret;

  private SecretKey getKey() {
    byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public String generateToken(User user) {
    return Jwts.builder()
        .subject(user.getUsername())
        .claim("id", user.getId())
        .claim("username", user.getUsername())
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30))
        .signWith(getKey())
        .compact();
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    Claims claims = extractAllClaims(token);
    Date expirationDate = claims.getExpiration();
    boolean isTokenExpired = expirationDate.before(new Date());
    return username.equals(userDetails.getUsername()) && !isTokenExpired;
  }

  public String extractUsername(String token) {
    // extract the username from jwt token
    Claims claims = extractAllClaims(token);
    return claims.getSubject();
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser()
        .verifyWith(getKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }
}
