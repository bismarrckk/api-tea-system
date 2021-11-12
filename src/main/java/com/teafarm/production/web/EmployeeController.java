package com.teafarm.production.web;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.teafarm.production.web.dto.EmployeeDto;

@RestController
@RequestMapping("/api/v1/employees/")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	AccountService accountService;
	
	@GetMapping
	public List<EmployeeDto> getAllEmployees(){
	
		List<Employee> employees= employeeService.getAllEmployees();
		List<EmployeeDto> employeeList=modelMapper.map(employees, new TypeToken<List<EmployeeDto>>() {}.getType());
		return employeeList;
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(name="id") int id) throws ResourceNotFoundException{
		Employee employee= employeeService.getEmployeeById(id);
		return new  ResponseEntity<>(employee,HttpStatus.OK);
	}
	@PostMapping("save")
	public ResponseEntity<EmployeeDto> addEmployee(@Valid @RequestBody EmployeeDto employeeDto) throws ResourceNotFoundException{
		Account account=accountService.getAccountById(1);
		employeeDto.setAccount(account);
		Date date=new Date();
		employeeDto.setRegDate(date);
		Employee requestEmployee=modelMapper.map(employeeDto, Employee.class);
		Employee employee=employeeService.addEmployee(requestEmployee);
		EmployeeDto responseEmployee=modelMapper.map(employee,EmployeeDto.class);
		
		return new ResponseEntity<>(responseEmployee,HttpStatus.CREATED);
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@Valid @RequestBody EmployeeDto employeeDto,@PathVariable(name="id") int id)
			throws ResourceNotFoundException{
		Employee requestEmployee=modelMapper.map(employeeDto, Employee.class);
		Employee employee=employeeService.updateEmployee(id, requestEmployee);
		EmployeeDto responseEmployee=modelMapper.map(employee,EmployeeDto.class);
		return new ResponseEntity<>(responseEmployee,HttpStatus.OK);
	} 
	
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable(name="id") int id) throws ResourceNotFoundException{
		employeeService.deleteEmployee(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
		
}
