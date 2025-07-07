package com.learn.RestWithDatabase.customResponses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponse {
  private int status;
  private boolean success;
  private String message;
  private long timeStamp;

	public MessageResponse() {
		timeStamp = System.currentTimeMillis();
	}
	public MessageResponse(String message) {
		this.status = 200;
		this.success = true;
		this.message = message;
		this.timeStamp = System.currentTimeMillis();
	}
	public MessageResponse(String message, boolean success) {
		if (success) {
			this.status = 200;
		} else {
			this.status = 404;
		}
		this.success = success;
		this.message = message;
		this.timeStamp = System.currentTimeMillis();
	}
	public MessageResponse(String message, boolean success, int status) {
		this.status = status;
		this.success = success;
		this.message = message;
		this.timeStamp = System.currentTimeMillis();
	}
}
