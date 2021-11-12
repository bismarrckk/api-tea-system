package com.teafarm.production.service;

import java.util.List;

import com.teafarm.production.entity.Weight;
import com.teafarm.production.exception.ResourceNotFoundException;

public interface WeightService {
	List<Weight> getAllWeight();
	Weight addWeight(Weight weight);
	Weight updateWeight(int id,Weight weight) throws ResourceNotFoundException;
	void deleteWeight(int id) throws ResourceNotFoundException;
	Weight getWeightById(int id) throws ResourceNotFoundException;

}
