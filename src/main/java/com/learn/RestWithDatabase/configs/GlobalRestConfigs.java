package com.learn.RestWithDatabase.configs;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.learn.RestWithDatabase.customExceptions.EmployeeNotFoundException;
import com.learn.RestWithDatabase.customExceptions.UserNotAuthenticatedException;
import com.learn.RestWithDatabase.dto.CommonResponse;

import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
public class GlobalRestConfigs {
  @ExceptionHandler
  public ResponseEntity<CommonResponse<String>> handleInvalidPayloadError(MethodArgumentNotValidException excep) {
    CommonResponse<String> error = new CommonResponse<>("Invalid payload, check all fields", HttpStatus.BAD_REQUEST.value());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
  @ExceptionHandler
  public ResponseEntity<CommonResponse<String>> handleEmployeeNotFoundError(EmployeeNotFoundException excep) {
    CommonResponse<String> error = new CommonResponse<>(excep.getLocalizedMessage(), HttpStatus.NOT_FOUND.value());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }
  @ExceptionHandler
  public ResponseEntity<CommonResponse<String>> handleUserNotFoundError(UsernameNotFoundException excep) {
    CommonResponse<String> error = new CommonResponse<>(excep.getLocalizedMessage(), HttpStatus.NOT_FOUND.value());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }
  @ExceptionHandler
  public ResponseEntity<CommonResponse<String>> handleAuthFailError(UserNotAuthenticatedException excep) {
    CommonResponse<String> error = new CommonResponse<>("Invalid payload, check all fields", HttpStatus.FORBIDDEN.value());
    return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
  }
  @ExceptionHandler
  public ResponseEntity<CommonResponse<String>> handleExpiredJwtError(ExpiredJwtException excep) {
    CommonResponse<String> error = new CommonResponse<>("JWT token expired", HttpStatus.UNAUTHORIZED.value());
    return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
  }
  @ExceptionHandler
  public ResponseEntity<CommonResponse<String>> handleAllErrors(Exception excep) {
    CommonResponse<String> error = new CommonResponse<>(excep.getLocalizedMessage(), HttpStatus.BAD_REQUEST.value());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
}
