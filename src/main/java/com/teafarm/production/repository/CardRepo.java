package com.teafarm.production.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.teafarm.production.entity.Card;
import com.teafarm.production.web.dto.GrowersSummary;
import com.teafarm.production.web.dto.Salary;


@Repository
public interface CardRepo extends JpaRepository<Card,Integer> {
	@Query("SELECT c from Card c where c.company.user.account.id=:id order by c.forDate DESC")
	List<Card> findCardByAcc(@Param("id") Integer id);
	
	@Query("SELECT c.company.user.firstName as firstName,c.company.user.lastName as lastName,c.company.name as companyName,c.company.regNumber as regNumber,sum(c.quantity) as receiptTotal "
			+ " from Card c where c.company.user.account.id=:id AND c.forDate BETWEEN :start AND :end "
			+ " GROUP BY c.company.id ")
	List<GrowersSummary> findGrowersSummary(@Param("id") int id,@Param("start") Date start,@Param("end") Date end);
	

}
