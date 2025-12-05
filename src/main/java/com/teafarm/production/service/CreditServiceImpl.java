package com.teafarm.production.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teafarm.production.entity.Credit;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.repository.CreditRepository;
import com.teafarm.production.web.dto.CreditCommodityTotals;
import com.teafarm.production.web.dto.CreditReport;

@Service
public class CreditServiceImpl implements CreditService{
	@Autowired
	CreditRepository creditRepo;
	@Override
	public Credit addCredit(Credit credit) {
		// TODO Auto-generated method stub
		return creditRepo.save(credit);
	}

	@Override
	public Credit updateCredit(int id, Credit credit) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Credit oldCredit=getCreditById(id);
		oldCredit.setEmployee(credit.getEmployee());
		oldCredit.setCommodity(credit.getCommodity());
		oldCredit.setForDate(credit.getForDate());
		oldCredit.setPrice(credit.getPrice());
		
		return creditRepo.save(oldCredit);
	}

	@Override
	public List<Credit> getCreditsByAcc(int id) {
		// TODO Auto-generated method stub
		return creditRepo.findCreditByAcc(id);
	}

	@Override
	public Credit getCreditById(int id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Credit credit=creditRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Credit details not found!!"));
		return credit;
	}

	@Override
	public void deleteCredit(int id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Credit credit=getCreditById(id);
		creditRepo.delete(credit);
	}

	@Override
	public List<Credit> getAllCredit() {
		// TODO Auto-generated method stub
		return creditRepo.findAll();
	}

	@Override
	public List<CreditReport> getReport(Date start, Date end, int id) {
		// TODO Auto-generated method stub
		return creditRepo.findCreditReport(start, end, id);
	}

	@Override
	public List<CreditCommodityTotals> getCommodityTotals(Date start, Date end, int id) {
		// TODO Auto-generated method stub
		return creditRepo.findAmountByCommodity(start, end, id);
	}


}
