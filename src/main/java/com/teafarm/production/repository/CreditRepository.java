package com.teafarm.production.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.teafarm.production.entity.Credit;
import com.teafarm.production.web.dto.CreditCommodityTotals;
import com.teafarm.production.web.dto.CreditReport;


@Repository
public interface CreditRepository extends JpaRepository<Credit,Integer>{
	@Query("SELECT c from Credit c where c.employee.account.id=:id")
	List<Credit> findCreditByAcc(@Param("id") Integer id);
	
	@Query("SELECT c.employee.firstName as firstName,c.employee.lastName as lastName,sum(c.price) as amount from Credit c "
			+ " where c.forDate BETWEEN :start AND :end AND  c.employee.account.id=:id "
			+ " group by c.employee.id ")
	List<CreditReport> findCreditReport(@Param("start") Date start,@Param("end") Date end,@Param("id") int id);
	
	@Query("SELECT new com.teafarm.production.web.dto.CreditCommodityTotals(c.commodity,sum(c.price)) from Credit c "
			+ " where c.forDate BETWEEN :start AND :end AND  c.employee.account.id=:id "
			+ " group by c.commodity ")
	List<CreditCommodityTotals> findAmountByCommodity(@Param("start") Date start,@Param("end") Date end,@Param("id") int id);
}
