package com.learn.RestWithDatabase.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.learn.RestWithDatabase.customExceptions.EmployeeNotFoundException;
import com.learn.RestWithDatabase.dto.CommonResponse;

@ControllerAdvice
public class GlobalRestConfigs {
  @ExceptionHandler
  public ResponseEntity<CommonResponse<String>> handleInvalidPayloadError(MethodArgumentNotValidException excep) {
    CommonResponse<String> error = new CommonResponse<>("Invalid payload, check all fields", HttpStatus.BAD_REQUEST.value());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
  @ExceptionHandler
  public ResponseEntity<CommonResponse<String>> handleNotFoundError(EmployeeNotFoundException excep) {
    CommonResponse<String> error = new CommonResponse<>(excep.getLocalizedMessage(), HttpStatus.NOT_FOUND.value());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }
  @ExceptionHandler
  public ResponseEntity<CommonResponse<String>> handleAllErrors(Exception excep) {
    CommonResponse<String> error = new CommonResponse<>(excep.getLocalizedMessage(), HttpStatus.BAD_REQUEST.value());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
}
