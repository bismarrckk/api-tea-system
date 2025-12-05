package com.teafarm.production.web;


import java.util.List;
import java.util.stream.Collectors;

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
import com.teafarm.production.service.CompanyService;
import com.teafarm.production.service.EmployeeService;
import com.teafarm.production.service.WeightService;
import com.teafarm.production.web.dto.WeightDto;

@RestController
@RequestMapping("/weight")
@CrossOrigin("*")
public class WeightController {
	@Autowired
	WeightService weightService;
	@Autowired
	EmployeeService empService;
	@Autowired
	CompanyService companyService;
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping
	public List<WeightDto> getAllWeights(){
		List<Weight> weight= weightService.getAllWeight();
		List<WeightDto> weightDto=modelMapper.map(weight, new TypeToken<List<WeightDto>>() {}.getType());
		for (int i = 0; i < weight.size(); i++) {
            Weight wh = weight.get(i);
            WeightDto dto = weightDto.get(i);
          
                dto.setCompanyName(wh.getCompany().getName());
                dto.setFirstName(wh.getEmployee().getFirstName());
		        dto.setLastName(wh.getEmployee().getLastName());
                dto.setRegNumber(wh.getCompany().getRegNumber());   
        }
		return weightDto;
	}
	@GetMapping("account/{accId}")
	public List<WeightDto> getWeightByAcc(@PathVariable int accId){
		List<Weight> weight=weightService.getWeightByAccId(accId);
		List<WeightDto> weightDtoList = weight.stream()
			    .map(wh -> {
			        WeightDto dto = modelMapper.map(wh, WeightDto.class);
			        dto.setCompanyName(wh.getCompany().getName());
			        dto.setFirstName(wh.getEmployee().getFirstName());
			        dto.setLastName(wh.getEmployee().getLastName());
			        dto.setRegNumber(wh.getCompany().getRegNumber());
			        return dto;
			    })
			    .collect(Collectors.toList());
		return weightDtoList;
	}
	
	@GetMapping("{id}")
	public WeightDto getWeightById(@PathVariable int id) throws ResourceNotFoundException{
		Weight weight=weightService.getWeightById(id);
		WeightDto dto=modelMapper.map(weight, WeightDto.class);
		 dto.setCompanyName(weight.getCompany().getName());
		 dto.setFirstName(weight.getEmployee().getFirstName());
	        dto.setLastName(weight.getEmployee().getLastName());
         dto.setRegNumber(weight.getCompany().getRegNumber());
         dto.setRegNumber(weight.getCompany().getRegNumber());
         dto.setEmployeeId(weight.getEmployee().getId());
         dto.setCompanyId(weight.getCompany().getId());
		return dto;
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
	public Weight updateWeight(@Valid @RequestBody WeightDto weightDto,@PathVariable int id)
			throws ResourceNotFoundException{
		Weight weightRequest=modelMapper.map(weightDto,Weight.class);
		weightRequest.setEmployee(empService.getEmployeeById(weightDto.getEmployeeId()));
		weightRequest.setCompany(companyService.getCompanyById(weightDto.getCompanyId()));
		Weight weight=weightService.updateWeight(weightRequest);
		
		return weight;
		
	}
	@DeleteMapping("{id}")
	public void deletePost(@PathVariable int id) throws ResourceNotFoundException {
		
		weightService.deleteWeight(id);
		
	}
	
	
}
