package com.sample.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author Mahesh
 *
 * @Version 1.0
 *
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EmployeeCreationException extends RuntimeException {

	public EmployeeCreationException(String firstName, String lastName) {
		super(" Employee " + firstName + " " + lastName + " could not created");
	}
} // end EmployeeCreateException class
