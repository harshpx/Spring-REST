package com.learn.RestWithDatabase.dto;

import com.learn.RestWithDatabase.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessfulAuthResponse {
  private int id;
  private String username;
  private String token;
  public SuccessfulAuthResponse(User user, String token) {
    this.id = user.getId();
    this.username = user.getUsername();
    this.token = token;
  }
}
