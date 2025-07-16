package com.learn.RestWithDatabase.rest;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HomeController {
  @GetMapping("/")
  public String home() {
    return "API is running! ";
  }
  @GetMapping("/csrf")
  public CsrfToken getToken(HttpServletRequest request) {
    return (CsrfToken) request.getAttribute("_csrf");
  }
  @GetMapping("/session")
  public String session(HttpServletRequest request) {
    return "Session ID: " + request.getSession().getId();
  }
}
