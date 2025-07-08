package com.learn.RestWithDatabase.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEmployeeRequest {
	@NotBlank(message = "First name is mandatory")
	private String firstName;
	@NotBlank(message = "Last name is mandatory")
	private String lastName;
	private String email;

	public CreateEmployeeRequest() {};
	public CreateEmployeeRequest(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
}