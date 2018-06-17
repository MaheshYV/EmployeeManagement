package com.sample.exception;
/**
 * @Author Mahesh
 *
 * @Version 1.0
 *
 */
public class EmployeeException extends Exception {
	private static final long serialVersionUID = 100L;

	public EmployeeException() {

	}

	public EmployeeException(String message) {
		super(message);
	}

	public EmployeeException(Throwable cause) {
		super(cause);
	}

	public EmployeeException(String message, Throwable cause) {
		super(message, cause);
	}
} // end EmployeeException class
