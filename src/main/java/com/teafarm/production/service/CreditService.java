package com.teafarm.production.service;

import java.sql.Date;
import java.util.List;

import com.teafarm.production.entity.Credit;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.web.dto.CreditCommodityTotals;
import com.teafarm.production.web.dto.CreditReport;

public interface CreditService {
	Credit addCredit(Credit credit);
	Credit updateCredit(int id,Credit credit) throws ResourceNotFoundException;
	List<Credit> getCreditsByAcc(int id);
	List<Credit> getAllCredit();
	Credit getCreditById(int id) throws ResourceNotFoundException;
	void deleteCredit(int id) throws ResourceNotFoundException;
	List<CreditReport> getReport(Date start,Date end,int id);
	List<CreditCommodityTotals> getCommodityTotals(Date start,Date end,int id);
}
