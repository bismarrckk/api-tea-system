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
	
	@Query(value="SELECT c.name,c.reg_number as regNumber,field,receipt,forDate FROM companies c"
			+ "	LEFT JOIN (SELECT SUM(quantity) as field,company_id as w_co_id,for_date as forDate FROM weight w group by w_co_id,forDate ) as w on w_co_id=c.id"
			+ "	LEFT JOIN (SELECT SUM(quantity) as receipt,company_id as cr_co_id,for_date as cr_for_date FROM card cr group by cr_co_id,cr_for_date) as cr on cr_co_id=c.id"
			+ "	LEFT JOIN users u on u.id=c.user_id"
			+ "	where cr_for_date=forDate and u.account_id=:accId"
			+ "	order by forDate desc ",nativeQuery =true)
	List<DailySummary> findDailySummary(@Param("accId") int accId);
	/*
	 * SELECT c.name,c.reg_number,w.fieldTotal,cr.cardTotal,w.for_date,cr.for_date FROM teasystem.companies c
		LEFT JOIN (SELECT SUM(quantity) as fieldTotal,company_id,for_date FROM teasystem.weight group by company_id,for_date ) as w on w.company_id=c.id
		LEFT JOIN (SELECT SUM(quantity) as cardTotal,company_id,for_date FROM teasystem.card group by company_id,for_date) as cr on cr.company_id=c.id
		LEFT JOIN teasystem.users u on u.id=c.user_id
		where cr.for_date=w.for_date and u.account_id=1
		order by w.for_date desc
	 */
	/*
	 * 
	 * @Query(value="SELECT new com.teafarm.production.web.dto.DailySummary(c.name,c.reg_number,w.fieldTotal,cr.cardTotal,w.for_date) FROM companies c "
			+ "	LEFT JOIN (SELECT SUM(quantity) as fieldTotal,company_id,for_date FROM weight group by company_id,for_date ) as w on w.company_id=c.id "
			+ "	LEFT JOIN (SELECT SUM(quantity) as cardTotal,company_id,for_date FROM card group by company_id,for_date) as cr on cr.company_id=c.id "
			+ "	LEFT JOIN users u on u.id=c.user_id "
			+ "	where cr.for_date=w.for_date and u.account_id=:id "
			+ "	order by w.for_date desc ",nativeQuery =true)
	 */
	
}
