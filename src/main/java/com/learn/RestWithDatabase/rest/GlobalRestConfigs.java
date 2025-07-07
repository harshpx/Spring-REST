package com.learn.RestWithDatabase.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.learn.RestWithDatabase.customExceptions.EmployeeNotFoundException;
import com.learn.RestWithDatabase.customResponses.ErrorResponse;

@ControllerAdvice
public class GlobalRestConfigs {
  @ExceptionHandler
  public ResponseEntity<ErrorResponse> handleNotFoundError(EmployeeNotFoundException excep) {
    ErrorResponse error = new ErrorResponse();
    error.setStatus(HttpStatus.NOT_FOUND.value());
    error.setMessage(excep.getLocalizedMessage());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }
  @ExceptionHandler
  public ResponseEntity<ErrorResponse> handleAllErrors(Exception excep) {
    ErrorResponse error = new ErrorResponse();
    error.setStatus(HttpStatus.BAD_REQUEST.value());
    error.setMessage(excep.getLocalizedMessage());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
}
