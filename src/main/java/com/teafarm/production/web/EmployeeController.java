package com.teafarm.production.web;


import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teafarm.production.entity.Account;
import com.teafarm.production.entity.Employee;

import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.service.AccountService;
import com.teafarm.production.service.EmployeeService;
import com.teafarm.production.service.UserService;
import com.teafarm.production.web.dto.EmployeeDto;

@RestController
@RequestMapping("/api/v1/employees/")
@CrossOrigin("http://localhost:8080/")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	UserService userService;
	@Autowired
	AccountService accService;
	
	@GetMapping
	public List<EmployeeDto> getAllEmployees(){
	
		List<Employee> employees= employeeService.getAllEmployees();
		List<EmployeeDto> employeeList=modelMapper.map(employees, new TypeToken<List<EmployeeDto>>() {}.getType());
		return employeeList;
	}
	
	@GetMapping("{id}")
	public Employee getEmployeeById(@PathVariable(name="id") int id) throws ResourceNotFoundException{
		Employee employee= employeeService.getEmployeeById(id);
		return employee;
	}
	@GetMapping("account/{accountId}")
	public List<Employee> getEmployeesByAccount(@PathVariable(name="accountId") int accountId) throws ResourceNotFoundException{
		Account account=accService.getAccountById(accountId);
		List<Employee> employees=employeeService.getEmployeesByAccount(account);
		return employees;
	}
	@PostMapping
	public EmployeeDto addEmployee(@Valid @RequestBody EmployeeDto employeeDto,HttpServletRequest request) 
			throws ResourceNotFoundException, ParseException{
		Account account=accService.getAccountById(employeeDto.getAccId());
		employeeDto.setAccount(account);
		Employee employee=employeeService.addEmployee(employeeDto);
		EmployeeDto responseEmployee=modelMapper.map(employee,EmployeeDto.class);
		
		return responseEmployee;
	}
	
	@PutMapping("{id}")
	public EmployeeDto updateEmployee(@Valid @RequestBody EmployeeDto employeeDto,@PathVariable(name="id") int id)
			throws ResourceNotFoundException{
		Employee requestEmployee=modelMapper.map(employeeDto, Employee.class);
		Employee employee=employeeService.updateEmployee(id, requestEmployee);
		EmployeeDto responseEmployee=modelMapper.map(employee,EmployeeDto.class);
		return responseEmployee;
	} 
	
	
	@DeleteMapping("{id}")
	public void deleteEmployee(@PathVariable(name="id") int id) throws ResourceNotFoundException{
		employeeService.deleteEmployee(id);
	
	}
		
}
