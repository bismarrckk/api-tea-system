package com.teafarm.production.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.teafarm.production.entity.Company;
import com.teafarm.production.web.dto.DailySummary;

@Repository
public interface CompanyRepo extends JpaRepository<Company,Integer>{
	@Query("SELECT c FROM Company c where c.user.account.id= :id")
	List<Company> findCompanyByAcc(@Param("id") Integer id);
	
	
	
	
}
