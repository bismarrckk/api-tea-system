package com.teafarm.production.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teafarm.production.entity.Weight;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.repository.WeightRepo;
import com.teafarm.production.web.dto.Salary;
@Service
public class WeightServiceImpl implements WeightService{
	@Autowired
	WeightRepo weightRepo;
	
	@Override
	public List<Weight> getAllWeight() {
		// TODO Auto-generated method stub
		return weightRepo.findAll();
	}

	@Override
	public Weight addWeight(Weight weight) {
		// TODO Auto-generated method stub
		return weightRepo.save(weight);
				
	}

	@Override
	public Weight updateWeight(int id, Weight weight) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Weight selectedWeight=getWeightById(id);
		selectedWeight.setCompany(weight.getCompany());
		selectedWeight.setEmployee(weight.getEmployee());
		selectedWeight.setForDate(weight.getForDate());
		selectedWeight.setQuantity(weight.getQuantity());
		return weightRepo.save(selectedWeight);
	}

	@Override
	public void deleteWeight(int id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Weight weight=getWeightById(id);
		weightRepo.delete(weight);
		
	}

	@Override
	public Weight getWeightById(int id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Weight weight=weightRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Weight not found!!"));
		
		return weight;
	}

	@Override
	public List<Weight> getWeightByAccId(int id) {
		// TODO Auto-generated method stub
		return weightRepo.findWeightByAcc(id);
	}

	@Override
	public List<Salary> getSalary(Date start, Date end,int id) {
		// TODO Auto-generated method stub
		return weightRepo.findSalary(start, end,id);
	}
	
	

}
