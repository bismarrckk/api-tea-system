package com.teafarm.production.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teafarm.production.entity.Account;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.repository.AccountRepo;
@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	private AccountRepo accountRepo;
	Date date=new Date();
	@Override
	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return accountRepo.findAll();
	}

	@Override
	public Account createAccount(Account account) {
		account.setRegDate(date);
		return accountRepo.save(account);
	}
	@Override
	public Account updateAccount(int id, Account account) throws ResourceNotFoundException {
		Account chosenAccount=getAccountById(id);
		chosenAccount.setName(account.getName());
		chosenAccount.setStatus(account.isStatus());
		chosenAccount.setContactPerson(account.getContactPerson());
		chosenAccount.setContactPhone(account.getContactPhone());
		chosenAccount.setUpdatedAt(date);
		return accountRepo.save(chosenAccount);
	}

	@Override
	public void deleteAccount(int id) throws ResourceNotFoundException {
		Account account=getAccountById(id);
		accountRepo.delete(account);
		
	}

	@Override
	public Account getAccountById(int id) throws ResourceNotFoundException {
		Account account = accountRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found!!"));
		return account;

	}

}
