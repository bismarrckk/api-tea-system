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
import com.teafarm.production.entity.User;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.service.CompanyService;
import com.teafarm.production.service.UserService;
import com.teafarm.production.web.dto.CompanyDto;
import com.teafarm.production.web.dto.DailySummary;


@RestController
@RequestMapping("/companies")
@CrossOrigin("*")
public class CompanyController {
	@Autowired
	CompanyService companyService;
	@Autowired 
	UserService userService;
	@Autowired 
	ModelMapper modelMapper;
	
	@GetMapping
	public List<CompanyDto> getAllCompanies(){
		List<Company> companies=companyService.getAllCompanies();
		List<CompanyDto> companyDto=modelMapper.map(companies,new TypeToken<List<CompanyDto>>() {}.getType());
		
		for (int i = 0; i < companies.size(); i++) {
            Company co = companies.get(i);
            CompanyDto dto = companyDto.get(i);
            if (co.getUser() != null) {
                dto.setUserId(co.getUser().getId());
                dto.setFirstName(co.getUser().getFirstName());
                dto.setLastName(co.getUser().getLastName());
            }
        }
		return companyDto;
	}
	
	@GetMapping("{id}")
	public CompanyDto getCompanyById(@PathVariable int id) throws ResourceNotFoundException{
		Company company=companyService.getCompanyById(id);
		CompanyDto companyDto=modelMapper.map(company, CompanyDto.class);
		companyDto.setFirstName(company.getUser().getFirstName());
		companyDto.setUserId(company.getUser().getId());
		
		return companyDto;
	}
	@GetMapping("account/{id}")
	public List<CompanyDto> getCompanyByAcc(@PathVariable int id){
		
		List<Company> companies=companyService.getCompanyByAcc(id);
		List<CompanyDto> companyDto=modelMapper.map(companies,new TypeToken<List<CompanyDto>>() {}.getType());
		for (int i = 0; i < companies.size(); i++) {
            Company co = companies.get(i);
            CompanyDto dto = companyDto.get(i);
            if (co.getUser() != null) {
                dto.setUserId(co.getUser().getId());
                dto.setFirstName(co.getUser().getFirstName());
                dto.setLastName(co.getUser().getLastName());
            }
        }
		return companyDto;

	}
	
	
	@PostMapping
	public CompanyDto addCompany(@Valid @RequestBody CompanyDto companyDto,HttpServletRequest request) throws ResourceNotFoundException {
		//convert Dto to Entity
		Company companyRequest=modelMapper.map(companyDto,Company.class);
		User user=userService.getUserById(companyDto.getUserId());
		companyRequest.setUser(user);
		Company company=companyService.addCompany(companyRequest);
		
		//convert Entity to Dto
		CompanyDto companyResponse=modelMapper.map(company, CompanyDto.class);
		
		return companyResponse;
	}
	@PutMapping("{id}")
	public CompanyDto updateCompany(@Valid @RequestBody CompanyDto companyDto,@PathVariable(name="id") int id)
			throws ResourceNotFoundException{
		Company companyRequest=modelMapper.map(companyDto,Company.class);
		User user=userService.getUserById(companyDto.getUserId());
		companyRequest.setUser(user);
		Company company=companyService.updateCompany(id, companyRequest);
		CompanyDto companyResponse=modelMapper.map(company, CompanyDto.class);
		return companyResponse;
		
	}
	@DeleteMapping("{id}")
	public void deletePost(@PathVariable(name = "id") int id) throws ResourceNotFoundException {
		
		companyService.deleteCompany(id);
	
	}
}
