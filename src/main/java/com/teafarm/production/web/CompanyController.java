package com.teafarm.production.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import com.teafarm.production.entity.Company;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.service.CompanyService;
import com.teafarm.production.service.UserService;
import com.teafarm.production.web.dto.CompanyDto;
import com.teafarm.production.web.dto.DailySummary;


@RestController
@RequestMapping("/api/v1/companies/")
@CrossOrigin("http://localhost:8080/")
public class CompanyController {
	@Autowired
	CompanyService companyService;
	@Autowired 
	UserService userService;
	@Autowired 
	ModelMapper modelMapper;
	
	@GetMapping
	public List<CompanyDto> getAllCompanies(){
		List<Company> requestCompany=companyService.getAllCompanies();
		List<CompanyDto> responseCompany=modelMapper.map(requestCompany,new TypeToken<List<CompanyDto>>() {}.getType());
		return responseCompany;
	}
	
	@GetMapping("{id}")
	public Company getCompanyById(@PathVariable(value="id") int id) throws ResourceNotFoundException{
		Company company=companyService.getCompanyById(id);
		return company;
	}
	@GetMapping("account/{accId}")
	public List<CompanyDto> getCompanyByAcc(@PathVariable(value="accId") int id){
		
		List<Company> companies=companyService.getCompanyByAcc(id);
		List<CompanyDto> listCompanies=modelMapper.map(companies,new TypeToken<List<CompanyDto>>() {}.getType());
		return listCompanies;
	}
	@GetMapping("summary/{id}")
	public List<DailySummary> getSummary(@PathVariable("id") int id){
		List<DailySummary> dailySummary=companyService.getDailySummary(id);
		return dailySummary;
	}
	
	@PostMapping
	public CompanyDto addCompany(@Valid @RequestBody CompanyDto companyDto,HttpServletRequest request) {
		//convert Dto to Entity
		Company companyRequest=modelMapper.map(companyDto,Company.class);
		Company company=companyService.addCompany(companyRequest);
		//convert Entity to Dto
		CompanyDto companyResponse=modelMapper.map(company, CompanyDto.class);
		
		return companyResponse;
	}
	@PutMapping("{id}")
	public CompanyDto updateCompany(@Valid @RequestBody CompanyDto companyDto,@PathVariable(name="id") int id)
			throws ResourceNotFoundException{
		Company companyRequest=modelMapper.map(companyDto,Company.class);
		Company company=companyService.updateCompany(id, companyRequest);
		CompanyDto companyResponse=modelMapper.map(company, CompanyDto.class);
		return companyResponse;
		
	}
	@DeleteMapping("{id}")
	public void deletePost(@PathVariable(name = "id") int id) throws ResourceNotFoundException {
		
		companyService.deleteCompany(id);
	
	}
}
