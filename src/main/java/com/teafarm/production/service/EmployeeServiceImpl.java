package com.teafarm.production.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teafarm.production.entity.Account;
import com.teafarm.production.entity.Employee;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.repository.EmployeeRepo;
import com.teafarm.production.web.dto.EmployeeDto;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired 
	EmployeeRepo employeeRepo;
	@Autowired
	AccountService accService;
	@Autowired
	ModelMapper modelMapper;
	@Override
	public List<Employee> getAllEmployees() {

		return employeeRepo.findAll();
	}

	@Override
	public Employee addEmployee(EmployeeDto employeeDto) throws ResourceNotFoundException {
		Account account=accService.getAccountById(employeeDto.getAccId());
		
		employeeDto.setStatus(true);
		Employee employee=modelMapper.map(employeeDto, Employee.class);
		employee.setAccount(account);
		return employeeRepo.save(employee);
	}

	@Override
	public Employee updateEmployee(int id, Employee employee) throws ResourceNotFoundException {
		Employee emp=getEmployeeById(id);
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
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

	@Override
	public List<Employee> getEmployeesByAccount(Account account) {
		// TODO Auto-generated method stub
		return employeeRepo.findByAccount(account);
	}

}
