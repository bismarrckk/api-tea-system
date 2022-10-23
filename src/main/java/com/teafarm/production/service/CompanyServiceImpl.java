package com.teafarm.production.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teafarm.production.entity.Company;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.repository.CompanyRepo;
import com.teafarm.production.web.dto.DailySummary;


@Service
public class CompanyServiceImpl  implements CompanyService{
	@Autowired 
	CompanyRepo companyRepo;
	
	@Override
	public List<Company> getAllCompanies() {
		// TODO Auto-generated method stub
		return companyRepo.findAll();
	}

	@Override
	public Company addCompany(Company company) {
		// TODO Auto-generated method stub
		return companyRepo.save(company);
	}

	@Override
	public Company updateCompany(int id, Company company) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Company selectedCompany=getCompanyById(id);
		selectedCompany.setName(company.getName());
		selectedCompany.setRegNumber(company.getRegNumber());
		selectedCompany.setUser(company.getUser());
		selectedCompany.setRate(company.getRate());
		return companyRepo.save(selectedCompany);
	}

	@Override
	public void deleteCompany(int id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Company company=getCompanyById(id);
		companyRepo.delete(company);
		
	}

	@Override
	public Company getCompanyById(int id) throws ResourceNotFoundException {
		Company company=companyRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Company not found!!"));
		return company;
	}

	@Override
	public List<Company> getCompanyByAcc(int id) {
		// TODO Auto-generated method stub
		return companyRepo.findCompanyByAcc(id);
	}

	@Override
	public List<DailySummary> getDailySummary(int id) {
		// TODO Auto-generated method stub
		return companyRepo.findDailySummary(id);
	}

}
