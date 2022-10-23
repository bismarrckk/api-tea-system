package com.teafarm.production.service;

import java.util.List;

import com.teafarm.production.entity.Credit;
import com.teafarm.production.exception.ResourceNotFoundException;

public interface CreditService {
	Credit addCredit(Credit credit);
	Credit updateCredit(int id,Credit credit) throws ResourceNotFoundException;
	List<Credit> getCreditsByAcc(int id);
	Credit getCreditById(int id) throws ResourceNotFoundException;
	void deleteCredit(int id) throws ResourceNotFoundException;
}
