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

import com.teafarm.production.entity.Company;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.service.CompanyService;
import com.teafarm.production.web.dto.CompanyDto;
import com.teafarm.production.service.UserService;


@RestController
@RequestMapping("/api/v1/companies/")
public class CompanyController {
	@Autowired
	CompanyService companyService;
	@Autowired 
	ModelMapper modelMapper;
	@Autowired 
	UserService userService;
	@GetMapping
	public List<CompanyDto> getAllCompanies(){
		List<Company> requestCompany=companyService.getAllCompanies();
		List<CompanyDto> responseCompany=modelMapper.map(requestCompany,new TypeToken<List<CompanyDto>>() {}.getType());
		return responseCompany;
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable(value="id") int id) throws ResourceNotFoundException{
		Company company=companyService.getCompanyById(id);
		return new ResponseEntity<>(company, HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<CompanyDto> addCompany(@Valid @RequestBody CompanyDto companyDto,HttpServletRequest request) {
		Principal principal=request.getUserPrincipal();
		String email=principal.getName();		
		User user=userService.getUserByEmail(email);
		companyDto.setUser(user);
		//convert Dto to Entity
		Company companyRequest=modelMapper.map(companyDto,Company.class);
		Company company=companyService.addCompany(companyRequest);
		//convert Entity to Dto
		CompanyDto companyResponse=modelMapper.map(company, CompanyDto.class);
		
		return new ResponseEntity<CompanyDto>(companyResponse,HttpStatus.CREATED);
	}
	@PutMapping("{id}")
	public ResponseEntity<CompanyDto> updateCompany(@Valid @RequestBody CompanyDto companyDto,@PathVariable(name="id") int id)
			throws ResourceNotFoundException{
		Company companyRequest=modelMapper.map(companyDto,Company.class);
		Company company=companyService.updateCompany(id, companyRequest);
		CompanyDto companyResponse=modelMapper.map(company, CompanyDto.class);
		return new ResponseEntity<>(companyResponse, HttpStatus.OK);
		
	}
	@DeleteMapping("{id}")
	public ResponseEntity<Company> deletePost(@PathVariable(name = "id") int id) throws ResourceNotFoundException {
		
		companyService.deleteCompany(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
