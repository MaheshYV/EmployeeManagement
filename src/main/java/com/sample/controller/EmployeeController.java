package com.sample.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.domain.Employee;
import com.sample.exception.EmployeeCreateException;
import com.sample.exception.EmployeeException;
import com.sample.exception.EmployeeNotFoundException;
import com.sample.request.EmployeeRequest;
import com.sample.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeService employeeService;

	/***
	 * Returns all the employees
	 * 
	 * @return List<Employee> - The list of employees
	 */
	@GetMapping("/list")
	public List<Employee> getEmployeeList() {
		return employeeService.getEmployeeList();
	}

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createEmployee(@RequestBody @NotNull EmployeeRequest employeeRequest) {

		ResponseEntity<?> responseEntity = null;

		try {
			employeeService.createEmployee(employeeRequest);
			responseEntity = new ResponseEntity<>("Employee " + employeeRequest.getFirstName() + " " + employeeRequest.getLastName() + " succesfully created",
					
					HttpStatus.CREATED);
		} catch (EmployeeCreateException e) {
			logger.error(e.getMessage());
			responseEntity = new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	} // end createEmployee method

	@PutMapping(path = "/update/{employeeId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateEmployee(@PathVariable("employeeId") Long employeeId,
			@RequestBody EmployeeRequest employeeRequest) {

		ResponseEntity<?> responseEntity = null;

		try {
			employeeService.updateEmployee(employeeId, employeeRequest);
			responseEntity = new ResponseEntity<>("Employee " + employeeRequest.getFirstName() +  " " + employeeRequest.getLastName() + " succesfuly updated",
					HttpStatus.OK);
		} catch (EmployeeNotFoundException e) {
			logger.error(e.getMessage());
			responseEntity = new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
			e.printStackTrace();
		} catch (EmployeeException e) {
			logger.error(e.getMessage());
			responseEntity = new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return responseEntity;
	} // end updateEmployee method

	@DeleteMapping("/delete/{employeeId}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("employeeId") Long employeeId) {

		ResponseEntity<?> responseEntity = null;

		try {
			employeeService.deleteEmployee(employeeId);
			responseEntity = new ResponseEntity<>("Employee " + employeeId + " succesfuly deleted", HttpStatus.NO_CONTENT);
		} catch (EmployeeException e) {
			logger.error(e.getMessage());
			responseEntity = new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return responseEntity;
	} // end deleteEmployee method

} // end EmployeeController class