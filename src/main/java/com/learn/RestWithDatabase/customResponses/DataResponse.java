package com.learn.RestWithDatabase.customResponses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataResponse<T> {
  private int status;
  private boolean success;
  private long timeStamp;
  private T data;

  // constructors
  public DataResponse() {
    this.success = true;
    this.timeStamp = System.currentTimeMillis();
  };
  public DataResponse(T data) {
    this.status = 200;
    this.success = true;
    this.timeStamp = System.currentTimeMillis();
    this.data = data;
  }
  public DataResponse(int status, T data) {
    this.status = status;
    this.success = true;
    this.timeStamp = System.currentTimeMillis();
    this.data = data;
  }
}
