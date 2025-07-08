package com.learn.RestWithDatabase.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatchEmployeeRequest {
	@NotNull(message = "Id is required for modifying an employee data")
	private int id;
    private String firstName;
	private String lastName;
	private String email;

	public PatchEmployeeRequest() {};
	public PatchEmployeeRequest(int id, String firstName, String lastName, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
}
