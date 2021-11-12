package com.teafarm.production.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teafarm.production.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer>{

}
