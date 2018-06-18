package com.sample.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	// Employee findById(@Param("employeeId") Long employeeId);
} // end EmployeeRepository interface