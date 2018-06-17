package com.sample.exception;
/**
 * @Author Mahesh
 *
 * @Version 1.0
 *
 */
public class EmployeeNotFoundException extends Exception {
	private static final long serialVersionUID = 100L;

	public EmployeeNotFoundException() {

	}

	public EmployeeNotFoundException(String message) {
		super(message);
	}

	public EmployeeNotFoundException(Throwable cause) {
		super(cause);
	}

	public EmployeeNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
} // end EmployeeNotFoundException class