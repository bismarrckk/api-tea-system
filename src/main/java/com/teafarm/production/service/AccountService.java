package com.teafarm.production.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.http.ResponseEntity;

import com.teafarm.production.entity.Account;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.web.dto.AccountDto;
import com.teafarm.production.web.dto.VerificationRequest;

public interface AccountService {
	Account addAccount(AccountDto accDto,String siteUrl);
	Account getAccountByName(String name) throws ResourceNotFoundException;
	void deleteAccount(int id) throws ResourceNotFoundException;
	List<Account> getAllAccounts();
	Account getAccountById(int id) throws ResourceNotFoundException;
	
	void sendVerificationEmail(AccountDto accDto) throws UnsupportedEncodingException, MessagingException;
	ResponseEntity<?> verify(VerificationRequest request);
}
