package com.teafarm.production.service;

import java.util.List;

import com.teafarm.production.entity.Employee;
import com.teafarm.production.exception.ResourceNotFoundException;

public interface EmployeeService {
	List<Employee> getAllEmployees();
	Employee addEmployee(Employee employee);
	Employee updateEmployee(int id,Employee employee) throws ResourceNotFoundException;
	void deleteEmployee(int id) throws ResourceNotFoundException;
	Employee getEmployeeById(int id) throws ResourceNotFoundException;

}
