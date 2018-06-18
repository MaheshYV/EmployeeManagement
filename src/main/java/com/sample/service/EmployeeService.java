package com.sample.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sample.dao.EmployeeRepository;
import com.sample.domain.Employee;
import com.sample.exception.EmployeeCreateException;
import com.sample.exception.EmployeeException;
import com.sample.exception.EmployeeNotFoundException;
import com.sample.request.EmployeeRequest;

@Service
public class EmployeeService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeProducer employeeProducer;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Value("${queue.employee}")
	private String queueName;
	
	public List<Employee> getEmployeeList() {
		logger.info("getEmployeeList method");
		return employeeRepository.findAll();
	} // end getEmployeeList method

	@Transactional
	public Employee createEmployee(@NotNull EmployeeRequest employeeRequest) throws EmployeeCreateException {

		logger.info("Start of createEmployee method");
		Employee employee = new Employee();

		setEmployee(employee, employeeRequest);

		try {
			employeeRepository.save(employee);
			// send employee request json to queue
		} catch (Exception e) {
			String errorMessage = "Employee " + "'" + employeeRequest.getFirstName() + " "
					+ employeeRequest.getLastName() + "'" + " cannot be created";
			logger.error(errorMessage);
			throw new EmployeeCreateException(errorMessage);
		}
		
		logger.info("End of createEmployee method");
		
		return employee;
		
	} // end createEmployee method

	public void updateEmployee(@NotNull Long employeeId, @NotNull EmployeeRequest employeeRequest)
			throws EmployeeNotFoundException, EmployeeException {
		logger.info("Start of updateEmployee method");
		logger.info("employeeId = " + employeeId);

		Optional<Employee> employee = Optional.ofNullable(null);

		try {
			employee = Optional.of(employeeRepository.findOne(employeeId));
		} catch (Exception e) {
			String errorMessage = "Employee " + "'" + employeeId + "'" + " Not Found";
			logger.error(errorMessage);
			throw new EmployeeNotFoundException(errorMessage);
		}

		logger.info(" updateEmployee " + employee);

		if (employee.isPresent()) {
			try {
				if (Optional.ofNullable(employeeRequest.getFirstName()).isPresent()) {
					employee.get().setFirstName(employeeRequest.getFirstName());
				}

				if (Optional.ofNullable(employeeRequest.getLastName()).isPresent()) {
					employee.get().setLastName(employeeRequest.getLastName());
				}

				if (Optional.ofNullable(employeeRequest.getEmailId()).isPresent()) {
					employee.get().setEmailId(employeeRequest.getEmailId());
				}
			} catch (Exception e) {
				String errorMessage = "Employee " + "'" + employeeId + "'" + " cannot be updated";
				logger.error(errorMessage + " Exception details " + e);
				e.printStackTrace();
				throw new EmployeeException(errorMessage);
			}

		} // end if employee is not null

		employeeRepository.save(employee.get());

		logger.info("End of updateEmployee method");

	} // end updateEmployee method

	public void deleteEmployee(Long employeeId) throws EmployeeException {

		logger.info("Start of deleteEmployee method");

		try {
			employeeRepository.delete(employeeId);
		} catch (Exception e) {
			String errorMessage = "Employee " + "'" + employeeId + "'" + " can not be deleted";
			logger.error(errorMessage);
			throw new EmployeeException(errorMessage);
		}

		logger.info("End of deleteEmployee method");

	} // end deleteEmployee method

	private Employee setEmployee(Employee employee, EmployeeRequest employeeRequest) {
		employee.setFirstName(employeeRequest.getFirstName());
		employee.setLastName(employeeRequest.getLastName());
		employee.setEmailId(employeeRequest.getEmailId());

		return employee;
	} // end setEmployee method

} // end EmployeeService class