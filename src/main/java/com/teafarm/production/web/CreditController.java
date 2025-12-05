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
@RequestMapping("/credits")
@CrossOrigin("*")
public class CreditController {
	@Autowired
	CreditService creditService;
	@Autowired
	ModelMapper mapper;
	
	@GetMapping("account/{id}")
	public List<CreditDto> getCreditsByAcc(@PathVariable int id){
		List<Credit> credit= creditService.getCreditsByAcc(id);
		List<CreditDto> creditDto=mapper.map(credit,  new TypeToken<List<CreditDto>>() {}.getType());
		for(int i=0;i<credit.size();i++) {
			Credit cr=credit.get(i);
			CreditDto dto=creditDto.get(i);
			
			dto.setFirstName(cr.getEmployee().getFirstName());
			dto.setLastName( cr.getEmployee().getLastName());
			dto.setEmployeeId(cr.getEmployee().getId());
		}
		return creditDto;
	}
	@GetMapping("{id}")
	public CreditDto getCreditById(@PathVariable int id) throws ResourceNotFoundException{ 
		Credit credit=creditService.getCreditById(id);
		CreditDto crDto=mapper.map(credit, CreditDto.class);
		return crDto;
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
