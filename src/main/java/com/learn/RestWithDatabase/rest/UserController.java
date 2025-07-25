package com.learn.RestWithDatabase.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.RestWithDatabase.dto.CommonResponse;
import com.learn.RestWithDatabase.dto.SuccessfulAuthResponse;
import com.learn.RestWithDatabase.entity.User;
import com.learn.RestWithDatabase.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity<CommonResponse<User>> registerUser(@Valid @RequestBody User user) {
    User registeredUser = userService.registerUser(user);
    CommonResponse<User> response = new CommonResponse<>(registeredUser);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("/login")
  public ResponseEntity<CommonResponse<SuccessfulAuthResponse>> loginUser(@Valid @RequestBody User user) {
    SuccessfulAuthResponse authResponse = userService.verify(user);
    CommonResponse<SuccessfulAuthResponse> response = new CommonResponse<>(authResponse);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
