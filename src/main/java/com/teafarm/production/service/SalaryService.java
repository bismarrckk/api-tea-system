package com.teafarm.production.service;

import java.sql.Date;
import java.util.List;

import com.teafarm.production.entity.Salary;
import com.teafarm.production.exception.ResourceNotFoundException;


public interface SalaryService {
	Salary addSalary(Salary salary);
	List<Salary> getAllById(int id);
	void delete(int id) throws ResourceNotFoundException;
	Salary updateSalary(int id,Salary salaryRequest) throws ResourceNotFoundException;
	List<Salary> getSalaryByDate(Date  from,Date to, int id);
	Salary getById(int id) throws ResourceNotFoundException;
}
