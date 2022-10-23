package com.teafarm.production.service;

import java.sql.Date;
import java.util.List;

import com.teafarm.production.entity.Weight;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.web.dto.Salary;

public interface WeightService {
	List<Weight> getAllWeight();
	Weight addWeight(Weight weight);
	Weight updateWeight(int id,Weight weight) throws ResourceNotFoundException;
	void deleteWeight(int id) throws ResourceNotFoundException;
	Weight getWeightById(int id) throws ResourceNotFoundException;
	List<Weight> getWeightByAccId(int id);
	List<Salary> getSalary(Date start,Date end,int id);
}
