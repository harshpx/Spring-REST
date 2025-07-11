package com.learn.RestWithDatabase.customExceptions;

public class EmployeeNotFoundException extends RuntimeException {
	public EmployeeNotFoundException() {
		super("Employee not found");
	}
	public EmployeeNotFoundException(String message) {
		super(message);
	}
}
