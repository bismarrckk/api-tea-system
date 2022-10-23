package com.teafarm.production.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.teafarm.production.entity.Weight;
import com.teafarm.production.web.dto.Salary;
 
@Repository
public interface WeightRepo extends JpaRepository<Weight,Integer> {
	@Query("SELECT w from Weight w where w.employee.account.id=:id order by w.forDate desc")
	List<Weight> findWeightByAcc(@Param("id") Integer id);
	
	@Query(value="SELECT e.fullname as fullName,quantityTotal,quantityTotal*co.rate as grossSalary,creditTotal FROM teasystem.employees e "
			+ "LEFT JOIN (select sum(quantity) as quantityTotal,for_date as forDate,company_id,employee_id from teasystem.weight w WHERE for_date BETWEEN :start AND :end group by w.employee_id) as w on e.id=w.employee_id "
			+ "LEFT JOIN (select sum(price) as creditTotal,for_date as crDate,employee_id from teasystem.credits cr WHERE for_date BETWEEN :start AND :end group by cr.employee_id) as cr on e.id=cr.employee_id "
			+ "LEFT JOIN teasystem.accounts a on e.account_id=a.id "
			+ "LEFT JOIN teasystem.companies co on co.id=w.company_id "
			+ "WHERE a.id=:id "
			+ "ORDER BY quantityTotal DESC ",nativeQuery=true)
	List<Salary> findSalary(@Param("start") Date start,@Param("end") Date end,@Param("id") int id);
	
}
 