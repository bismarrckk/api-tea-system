package com.teafarm.production.service;

import java.sql.Date;
import java.util.List;

import com.teafarm.production.entity.Weight;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.web.dto.DailySummary;
import com.teafarm.production.web.dto.Salary;
import com.teafarm.production.web.dto.SalaryDue;

public interface WeightService {
	List<Weight> getAllWeight();
	Weight addWeight(Weight weight);
	Weight updateWeight(Weight weight) throws ResourceNotFoundException;
	void deleteWeight(int id) throws ResourceNotFoundException;
	Weight getWeightById(int id) throws ResourceNotFoundException;
	List<Weight> getWeightByAccId(int id);
	List<Salary> getSalary(Date start,Date end,int id);
	List<SalaryDue> getSalaryDue(Date start,Date end,int id);
	List<DailySummary> getDailySummary(int id);
}
