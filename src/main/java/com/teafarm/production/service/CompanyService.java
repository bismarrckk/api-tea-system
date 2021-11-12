package com.teafarm.production.service;

import java.util.List;

import com.teafarm.production.entity.Company;
import com.teafarm.production.exception.ResourceNotFoundException;

public interface CompanyService {
	List<Company> getAllCompanies();
	Company addCompany(Company company);
	Company updateCompany(int id,Company company) throws ResourceNotFoundException;
	void deleteCompany(int id) throws ResourceNotFoundException;
	Company getCompanyById(int id) throws ResourceNotFoundException;
}
