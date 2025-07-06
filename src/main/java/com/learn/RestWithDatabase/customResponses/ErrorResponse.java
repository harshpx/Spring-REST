package com.learn.RestWithDatabase.customResponses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
  private int status;
  private boolean success;
  private String message;
  private long timeStamp;

  public ErrorResponse() {
    this.success = false;
    this.timeStamp = System.currentTimeMillis();
  };
  public ErrorResponse(int status, String message) {
    this.success = false;
    this.status = status;
    this.message = message;
    this.timeStamp = System.currentTimeMillis();
  }
}
