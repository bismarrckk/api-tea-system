package com.teafarm.production.web;

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

import com.teafarm.production.entity.Credit;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.service.CreditService;
import com.teafarm.production.web.dto.CreditDto;

@RestController
@RequestMapping("/api/v1/credits/")
@CrossOrigin("http://localhost:8080/")
public class CreditController {
	@Autowired
	CreditService creditService;
	@Autowired
	ModelMapper mapper;
	
	@GetMapping("account/{id}")
	public List<CreditDto> getCreditsByAcc(@PathVariable(value="id") int id){
		List<Credit> credit= creditService.getCreditsByAcc(id);
		List<CreditDto> creditList=mapper.map(credit,  new TypeToken<List<CreditDto>>() {}.getType());
		return creditList;
	}
	@GetMapping("{id}")
	public Credit getCreditById(@PathVariable(value="id") int id) throws ResourceNotFoundException{ 
		Credit credit=creditService.getCreditById(id);
		return credit;
	}
	@PostMapping
	public CreditDto addCredit(@Valid @RequestBody CreditDto creditDto){
		Credit creditRequest=mapper.map(creditDto, Credit.class);
		Credit credit=creditService.addCredit(creditRequest);
		CreditDto creditResponse=mapper.map(credit, CreditDto.class);
		
		return creditResponse;
	}
	@PutMapping("{id}")
	public CreditDto updateCredits(@Valid @RequestBody CreditDto creditDto,@PathVariable(value="id") int id) 
			throws ResourceNotFoundException{
		Credit creditRequest=mapper.map(creditDto, Credit.class);
		Credit credit=creditService.updateCredit(id, creditRequest);
		CreditDto creditResponse=mapper.map(credit, CreditDto.class);
		
		return creditResponse;
		
	}
	
	@DeleteMapping("{id}")
	public void deleteCredit(@PathVariable("id") int id) throws ResourceNotFoundException {
		creditService.deleteCredit(id);
		
	}
}
