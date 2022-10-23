package com.teafarm.production.service;

import java.util.List;

import com.teafarm.production.entity.Account;
import com.teafarm.production.entity.Employee;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.web.dto.EmployeeDto;

public interface EmployeeService {
	List<Employee> getAllEmployees();
	Employee addEmployee(EmployeeDto empDto);
	Employee updateEmployee(int id,Employee employee) throws ResourceNotFoundException;
	void deleteEmployee(int id) throws ResourceNotFoundException;
	Employee getEmployeeById(int id) throws ResourceNotFoundException;
	List<Employee> getEmployeesByAccount(Account account);	

}
