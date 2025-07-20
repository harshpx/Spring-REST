package com.learn.RestWithDatabase.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learn.RestWithDatabase.dao.UserRepository;
import com.learn.RestWithDatabase.entity.User;

// implementation class of UserDetailsService interface
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User foundUser = userRepository.findByUsername(username);
    if (foundUser == null) {
      throw new UsernameNotFoundException("Username not found");
    }
    return new UserDetailsImpl(foundUser);
  }
}
