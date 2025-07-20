package com.learn.RestWithDatabase.services;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.learn.RestWithDatabase.entity.User;


public class UserDetailsImpl implements UserDetails {

  private User user;

  public UserDetailsImpl(User user) {
    this.user = user;
  }

  public User getUser() {
    return this.user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singleton(() -> "USER");
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }
}
