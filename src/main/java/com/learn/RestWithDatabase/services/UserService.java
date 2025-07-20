package com.learn.RestWithDatabase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.learn.RestWithDatabase.dao.UserRepository;
import com.learn.RestWithDatabase.dto.SuccessfulAuthResponse;
import com.learn.RestWithDatabase.entity.User;

import jakarta.transaction.Transactional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AuthenticationManager authManager;

  @Autowired
  private JWTService jwtService;

  @Transactional
  public User registerUser(User user) {
    user.setId(0);
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
    user.setPassword(encoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public SuccessfulAuthResponse verify(User user) {
    Authentication authentication = authManager
        .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        
      if (!authentication.isAuthenticated()) {
        throw new RuntimeException("User unauthenticated");
      }
      UserDetailsImpl authenticatedUserDetailsObject = (UserDetailsImpl) authentication.getPrincipal();
      User authenticatedUser = authenticatedUserDetailsObject.getUser();
      return new SuccessfulAuthResponse(authenticatedUser, jwtService.generateToken(authenticatedUser));
  }
}
