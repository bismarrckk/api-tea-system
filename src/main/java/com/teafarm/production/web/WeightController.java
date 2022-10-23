package com.teafarm.production.web;

import java.sql.Date;
import java.util.List;

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

import com.teafarm.production.entity.Weight;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.service.WeightService;
import com.teafarm.production.web.dto.Salary;
import com.teafarm.production.web.dto.WeightDto;

@RestController
@RequestMapping("/api/v1/weight/")
@CrossOrigin("http://localhost:8080/")
public class WeightController {
	@Autowired
	WeightService weightService;
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping
	public List<WeightDto> getAllWeights(){
		List<Weight> weight= weightService.getAllWeight();
		List<WeightDto> weightList=modelMapper.map(weight, new TypeToken<List<WeightDto>>() {}.getType());
		return weightList;
	}
	@GetMapping("account/{accId}")
	public List<WeightDto> getWeightByAcc(@PathVariable(name="accId") int accId){
		List<Weight> weights=weightService.getWeightByAccId(accId);
		List<WeightDto> weightList=modelMapper.map(weights, new TypeToken<List<WeightDto>>() {}.getType());
		return weightList;
	}
	
	@GetMapping("{id}")
	public Weight getWeightById(@PathVariable(value="id") int id) throws ResourceNotFoundException{
		Weight weight=weightService.getWeightById(id);
		return weight;
	}
	@GetMapping("salary/{id}/{start}/{end}")
	public List<Salary> getSalary(@PathVariable(value="id") int id,@PathVariable("start") Date start,@PathVariable ("end") Date end){
		List<Salary> salary=weightService.getSalary(start, end,id);
		return salary;
	}
	@PostMapping
	public WeightDto addWeight(@RequestBody WeightDto weightDto) {
		//convert Dto to Entity
		Weight weightRequest=modelMapper.map(weightDto,Weight.class);
		Weight weight=weightService.addWeight(weightRequest);
		//convert Entity to Dto
		WeightDto weightResponse=modelMapper.map(weight, WeightDto.class);
		
		return weightResponse;
	}
	@PutMapping("{id}")
	public WeightDto updateWeight(@Valid @RequestBody WeightDto weightDto,@PathVariable(name="id") int id)
			throws ResourceNotFoundException{
		Weight weightRequest=modelMapper.map(weightDto,Weight.class);
		Weight weight=weightService.updateWeight(id, weightRequest);
		WeightDto weightResponse=modelMapper.map(weight, WeightDto.class);
		return weightResponse;
		
	}
	@DeleteMapping("{id}")
	public void deletePost(@PathVariable(name = "id") int id) throws ResourceNotFoundException {
		
		weightService.deleteWeight(id);
		
	}
	
	
}
