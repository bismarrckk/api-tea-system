package com.teafarm.production.service;

import java.util.List;

import com.teafarm.production.entity.Account;
import com.teafarm.production.exception.ResourceNotFoundException;

public interface AccountService {
	List<Account> getAllAccounts();
	Account createAccount(Account account);
	Account updateAccount(int id,Account account) throws ResourceNotFoundException;
	void deleteAccount(int id) throws ResourceNotFoundException;
	Account getAccountById(int id) throws ResourceNotFoundException;
	
}
