package com.teafarm.production.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.teafarm.production.entity.Weight;
import com.teafarm.production.web.dto.DailySummary;
import com.teafarm.production.web.dto.Salary;
import com.teafarm.production.web.dto.SalaryDue;
 
@Repository
public interface WeightRepo extends JpaRepository<Weight,Integer> {
	@Query("SELECT w from Weight w where w.employee.account.id=:id order by w.forDate desc")
	List<Weight> findWeightByAcc(@Param("id") Integer id);
	
	@Query(value="SELECT e.first_name as firstName,e.last_name as lastName,quantityTotal,quantityTotal*co.rate as grossSalary,creditTotal FROM teasystem.employees e "
			+ "JOIN (select sum(quantity) as quantityTotal,for_date as forDate,company_id,employee_id from teasystem.weight w WHERE for_date BETWEEN :start AND :end group by w.employee_id) as w on e.id=w.employee_id "
			+ "left JOIN (select sum(price) as creditTotal,for_date as crDate,employee_id from teasystem.credits cr WHERE for_date BETWEEN :start AND :end group by cr.employee_id) as cr on e.id=cr.employee_id "
			+ "JOIN teasystem.accounts a on e.account_id=a.id "
			+ "JOIN teasystem.companies co on co.id=w.company_id "
			+ "WHERE a.id=:id ",nativeQuery=true)
	List<Salary> findSalary(@Param("start") Date start,@Param("end") Date end,@Param("id") int id);
	
	@Query(value="SELECT u.first_name as firstName,u.last_name as lastName,sum(w.quantity) as quantityTotal,sum(w.quantity * co.rate) as totalAmountDue "
			+ " FROM users u,weight w,companies co "
			+ " where u.id=co.user_id AND w.company_id=co.id AND w.for_date BETWEEN :start AND :end AND u.account_id=:id  "
			+ "group by u.id  ",nativeQuery=true)
	
	List<SalaryDue> findPayPerUser(@Param("start") Date start,@Param("end") Date end,@Param("id") int id);
	
	@Query(value="SELECT c.name,c.reg_number as regNumber,field,receipt,forDate FROM companies c"
			+ "	LEFT JOIN (SELECT SUM(quantity) as field,company_id as w_co_id,for_date as forDate FROM weight w group by w_co_id,forDate ) as w on w_co_id=c.id"
			+ "	LEFT JOIN (SELECT SUM(quantity) as receipt,company_id as cr_co_id,for_date as cr_for_date FROM card cr group by cr_co_id,cr_for_date) as cr on cr_co_id=c.id"
			+ "	LEFT JOIN users u on u.id=c.user_id"
			+ "	where cr_for_date=forDate and u.account_id=:accId"
			+ "	order by forDate desc ",nativeQuery =true)
	List<DailySummary> findDailySummary(@Param("accId") int accId);
	
//	@Query("SELECT sum(w.quantity * c.rate) AS quantityTotal,c.company.user.fullname as fullname FROM Weight w "
//			+ " JOIN  Company c ON w.company.id=c.id")
//	List<Salary> findPayPerUser(@Param("start") Date start,@Param("end") Date end,@Param("id") int id);
}
 