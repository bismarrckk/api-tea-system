package com.teafarm.production.web;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

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

import com.teafarm.production.entity.Employee;
import com.teafarm.production.entity.Salary;

import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.service.EmployeeService;
import com.teafarm.production.service.SalaryService;
import com.teafarm.production.web.dto.OtherSalariesDto;



@RestController
@RequestMapping("/salaries")
@CrossOrigin("*")
public class SalaryController {
	@Autowired 
	SalaryService salaryService;
	@Autowired
	EmployeeService empService;
	@Autowired
	ModelMapper mapper;
	@PostMapping
	public OtherSalariesDto addSalary(@RequestBody OtherSalariesDto salaryDto) throws ResourceNotFoundException {
		Employee emp=empService.getEmployeeById(salaryDto.getEmployeeId());
		Salary salary=mapper.map(salaryDto,Salary.class);
		salary.setEmployee(emp);
		Salary salaryResponse= salaryService.addSalary(salary);
		OtherSalariesDto otherSalaries=mapper.map(salaryResponse, OtherSalariesDto.class);
		return otherSalaries;
	}
	@GetMapping("{id}")
	public OtherSalariesDto getById(@PathVariable int id) throws ResourceNotFoundException{
		Salary salary= salaryService.getById(id);
		OtherSalariesDto dto=mapper.map(salary,OtherSalariesDto.class);
		return dto;
	}
	
	@GetMapping("account/{id}")
	public List<OtherSalariesDto> getAll(@PathVariable int id){
		List<Salary> salary=salaryService.getAllById(id);	
		List<OtherSalariesDto> salaryDto = salary.stream()
			    .map(s -> {
			    	OtherSalariesDto dto= mapper.map(s, OtherSalariesDto.class);
			       
			        dto.setFirstName(s.getEmployee().getFirstName());
			        dto.setLastName(s.getEmployee().getLastName());
			       
			        return dto;
			    })
			    .collect(Collectors.toList());
		return salaryDto;
	}
	@DeleteMapping("{id}")
	public void deleteSalary(@PathVariable int id) throws ResourceNotFoundException {
		salaryService.delete(id);
	}
	@PutMapping("{id}")
	public OtherSalariesDto update(@RequestBody OtherSalariesDto salaryDto, @PathVariable int id) throws ResourceNotFoundException {

		Salary salaryRequest=mapper.map(salaryDto, Salary.class);
		Salary salaryResponse=salaryService.updateSalary(id, salaryRequest);
		OtherSalariesDto otherSalaries=mapper.map(salaryResponse, OtherSalariesDto.class);
		return otherSalaries;
		
	}
	
	@GetMapping("{from}/{to}/{id}")
	public List<OtherSalariesDto> getByDate(@PathVariable Date from,@PathVariable Date to,@PathVariable int id){
		
		List<Salary> salary= salaryService.getSalaryByDate(from,to,id);
		List<OtherSalariesDto> dto=mapper.map(salary, new TypeToken<List<OtherSalariesDto>>() {}.getType());
		return dto;
	}
}
