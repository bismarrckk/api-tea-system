package com.teafarm.production.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teafarm.production.entity.Company;

@Repository
public interface CompanyRepo extends JpaRepository<Company,Integer>{

}
