package com.teafarm.production.service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.teafarm.production.entity.Account;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.repository.AccountRepo;
import com.teafarm.production.util.VerificationCodeUtil;
import com.teafarm.production.web.dto.AccountDto;
import com.teafarm.production.web.dto.VerificationRequest;



@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	AccountRepo accountRepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	JavaMailSender javaMailSender;

	@Override
	public Account addAccount(AccountDto accDto,String siteURL) {
		String verificationCode = VerificationCodeUtil.generateCode(6);
		accDto.setVerificationCode(verificationCode);
		
		Account acc=modelMapper.map(accDto, Account.class);	
		acc.setVerificationExpiry(LocalDateTime.now().plusMinutes(20));
		acc.setCreatedBy(accDto.getEmail());
		acc.setActive(false);
		try {
			sendVerificationEmail(accDto);
		} catch (UnsupportedEncodingException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accountRepo.save(acc);
	}
	
	@Override
	public Account getAccountByName(String name) throws ResourceNotFoundException{
		// TODO Auto-generated method stub
		return accountRepo.findByAlias(name);
	}

	@Override
	public void deleteAccount(int id) throws ResourceNotFoundException{
		// TODO Auto-generated method stub
		Account account=getAccountById(id);
		accountRepo.delete(account);
		
	}

	@Override
	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return accountRepo.findAll();
	}

	@Override
	public Account getAccountById(int id) throws ResourceNotFoundException{
		// TODO Auto-generated method stub
		Account account=accountRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Account"+id+" not available!!"));
		return account;
	}


	@Override
	public void sendVerificationEmail(AccountDto accDto) throws UnsupportedEncodingException, MessagingException {
		    String toAddress = "bismarckmetet@gmail.com";
		    String fromAddress = "bismarckmetet@gmail.com";
		    String senderName = "Tea Farm Managememt System";
		    String subject = "Account verification";
		    String content = "Dear [[name]],<br><br>"
		            + "Thanks for signing up! Enter the code below to verify your account and get started. Please note that the code expires in 20 minutes<br>"
		            + "<h3>[[code]]</h3>"
		            + "Thank you,<br>"
		            + "Tea Farm Managememt System";
		    
		   
		    MimeMessage message = javaMailSender.createMimeMessage();
		    MimeMessageHelper helper = null;
			try {
				helper = new MimeMessageHelper(message);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     
		    helper.setFrom(fromAddress, senderName);
		    helper.setTo(toAddress);
		    helper.setSubject(subject);
		     
		    content = content.replace("[[name]]", accDto.getFirstName());
		    String code = accDto.getVerificationCode();
		     
		    content = content.replace("[[code]]", code);
		     
		    helper.setText(content, true);
		     
		    javaMailSender.send(message);
		    
		    
		    
		
	}

	@Override
	public ResponseEntity<?> verify(VerificationRequest request) {
		Account acc=accountRepo.findByVerificationCode(request.getCode());
		if (acc == null || acc.isActive() ||  acc.getVerificationExpiry().isBefore(LocalDateTime.now())) {
			 return ResponseEntity.badRequest().body("Verification code expired");
	    } 
		 if (!request.getCode().equals(acc.getVerificationCode())) {
		        return ResponseEntity.badRequest().body("Invalid verification code");
		 }
		
	        acc.setVerificationCode(null);
	        acc.setActive(true);
	        acc.setVerificationExpiry(null);
	        
	        accountRepo.save(acc);
	         
	        return ResponseEntity.ok("Account verified successfully");
	   
	}

}
