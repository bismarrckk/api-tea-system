package com.teafarm.production.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.teafarm.production.entity.Salary;

@Repository
public interface SalaryRepo extends JpaRepository<Salary,Integer> {
	@Query(value="SELECT s from Salary s where s.forDate between :start AND :end and s.employee.account.id= :id ")
	List<Salary> findSalary(@Param("start") Date start,@Param("end") Date end,@Param("id") int id);
	
	@Query(value="SELECT s from Salary s where s.employee.account.id= :id ")
	List<Salary> findSalaryById(@Param("id") int id);
	
	
}
