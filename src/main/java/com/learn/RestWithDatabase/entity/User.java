package com.learn.RestWithDatabase.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {
  
  @Column(name = "id")
  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  private int id;
  
  @Column(name = "username", unique = true)
  @NotNull(message = "Username is required")
  private String username;

  @Column(name = "password")
  @NotNull(message = "Password is required")
  private String password;

  public User() {}
  public User(String username, String password) {
    this.id = 0;
    this.username = username;
    this.password = password;
  }
}
