package com.teafarm.production.web;

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

import com.teafarm.production.entity.Weight;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.service.WeightService;
import com.teafarm.production.web.dto.WeightDto;

@RestController
@RequestMapping("api/v1/weight/")
public class WeightController {
	@Autowired
	WeightService weightService;
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping
	public List<WeightDto> getAllWeights(){
		List<Weight> weight= weightService.getAllWeight();
		List<WeightDto> WeightList=modelMapper.map(weight, new TypeToken<List<WeightDto>>() {}.getType());
		return WeightList;
	}
	@GetMapping("{id}")
	public ResponseEntity<Weight> getWeightById(@PathVariable(value="id") int id) throws ResourceNotFoundException{
		Weight weight=weightService.getWeightById(id);
		return new ResponseEntity<>(weight, HttpStatus.OK);
	}
	@PostMapping("save")
	public ResponseEntity<WeightDto> addWeight(@RequestBody WeightDto weightDto) {
		//convert Dto to Entity
		Weight weightRequest=modelMapper.map(weightDto,Weight.class);
		Weight weight=weightService.addWeight(weightRequest);
		//convert Entity to Dto
		WeightDto weightResponse=modelMapper.map(weight, WeightDto.class);
		
		return new ResponseEntity<WeightDto>(weightResponse,HttpStatus.CREATED);
	}
	@PutMapping("update/{id}")
	public ResponseEntity<WeightDto> updateWeight(@Valid @RequestBody WeightDto weightDto,@PathVariable(name="id") int id)
			throws ResourceNotFoundException{
		Weight weightRequest=modelMapper.map(weightDto,Weight.class);
		Weight weight=weightService.updateWeight(id, weightRequest);
		WeightDto weightResponse=modelMapper.map(weight, WeightDto.class);
		return new ResponseEntity<>(weightResponse, HttpStatus.OK);
		
	}
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Weight> deletePost(@PathVariable(name = "id") int id) throws ResourceNotFoundException {
		
		weightService.deleteWeight(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
}
