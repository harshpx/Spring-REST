package com.learn.RestWithDatabase.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {
	private String firstName;
	private String lastName;
	private String email;

	public EmployeeDTO() {};
	public EmployeeDTO(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
}
// test comment