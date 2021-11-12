package com.teafarm.production.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teafarm.production.entity.Employee;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.repository.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired 
	EmployeeRepo employeeRepo;
	@Override
	public List<Employee> getAllEmployees() {

		return employeeRepo.findAll();
	}

	@Override
	public Employee addEmployee(Employee employee) {
		
		return employeeRepo.save(employee);
	}

	@Override
	public Employee updateEmployee(int id, Employee employee) throws ResourceNotFoundException {
		Employee emp=getEmployeeById(id);
		emp.setFullname(employee.getFullname());
		emp.setRole(employee.getRole());
		emp.setStatus(employee.isStatus());
		return employeeRepo.save(emp);
	
	}

	@Override
	public void deleteEmployee(int id) throws ResourceNotFoundException {
		Employee employee=getEmployeeById(id);
		employeeRepo.delete(employee);
		
	}

	@Override
	public Employee getEmployeeById(int id) throws ResourceNotFoundException {
		Employee employee=employeeRepo.findById(id).
				orElseThrow(()->new ResourceNotFoundException("Employee not found!!"));
		return employee;
	}

}
