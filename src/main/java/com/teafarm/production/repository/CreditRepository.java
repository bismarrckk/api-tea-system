package com.teafarm.production.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.teafarm.production.entity.Credit;


@Repository
public interface CreditRepository extends JpaRepository<Credit,Integer>{
	@Query("SELECT c from Credit c where c.employee.account.id=:id order by c.forDate desc")
	List<Credit> findCreditByAcc(@Param("id") Integer id);
}
