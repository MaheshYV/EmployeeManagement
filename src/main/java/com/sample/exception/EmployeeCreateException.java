package com.sample.exception;
/**
 * @Author Mahesh
 *
 * @Version 1.0
 *
 */
public class EmployeeCreateException extends Exception {
	private static final long serialVersionUID = 100L;

	public EmployeeCreateException() {

	}

	public EmployeeCreateException(String message) {
		super(message);
	}

	public EmployeeCreateException(Throwable cause) {
		super(cause);
	}

	public EmployeeCreateException(String message, Throwable cause) {
		super(message, cause);
	}
} // end EmployeeCreateException class
