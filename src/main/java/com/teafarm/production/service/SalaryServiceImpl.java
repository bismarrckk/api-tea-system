package com.teafarm.production.service;

import java.sql.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teafarm.production.entity.Salary;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.repository.SalaryRepo;
import com.teafarm.production.web.dto.OtherSalariesDto;

@Service
public class SalaryServiceImpl  implements SalaryService{
	@Autowired
	SalaryRepo salaryRepo;
	@Autowired
	ModelMapper mapper;
	@Override
	public Salary addSalary(Salary salary) {
		// TODO Auto-generated method stub
		return salaryRepo.save(salary);
	}
	@Override
	public List<Salary> getAllById(int id) {
		// TODO Auto-generated method stub
		return salaryRepo.findSalaryById(id);
	}
	@Override
	public void delete(int id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		salaryRepo.deleteById(id);
		
	}
	@Override
	public Salary updateSalary(int id, Salary salary) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		salaryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Salary details not found!!"));
		return salaryRepo.save(salary);
	}
	@Override
	public List<Salary> getSalaryByDate(Date from, Date to, int id) {
		// TODO Auto-generated method stub
		return salaryRepo.findSalary(from, to, id);
	}
	@Override
	public Salary getById(int id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
	  return salaryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Salary details not found!!"));
	}

	
	
}
