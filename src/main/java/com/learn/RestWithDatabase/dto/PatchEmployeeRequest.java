package com.learn.RestWithDatabase.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatchEmployeeRequest {
  private String firstName;
	private String lastName;
	private String email;

	public PatchEmployeeRequest() {};
	public PatchEmployeeRequest(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
}
