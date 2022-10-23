package com.teafarm.production.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.teafarm.production.entity.Account;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.repository.AccountRepo;
import com.teafarm.production.web.dto.AccountDto;

import net.bytebuddy.utility.RandomString;

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
		String randomCode=RandomString.make(64);
		accDto.setVerificationCode(randomCode);
		Account acc=modelMapper.map(accDto, Account.class);	
		
		try {
			sendVerificationEmail(accDto,siteURL);
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
	public Boolean verifyCode(String code) {
		Account acc=accountRepo.findByVerificationCode(code);
		if (acc == null || acc.isActive()) {
	        return false;
	    } else {
	        acc.setVerificationCode(null);
	        acc.setActive(true);
	        accountRepo.save(acc);
	         
	        return true;
	    }
	}

	@Override
	public void sendVerificationEmail(AccountDto accDto, String siteURL) throws UnsupportedEncodingException, MessagingException {
		    String toAddress = accDto.getEmail();
		    String fromAddress = "b77kibet@gmail.com";
		    String senderName = "TeaFarm";
		    String subject = "Please verify your account";
		    String content = "Dear [[name]],<br>"
		            + "Thankyou for registering with us,Please click the link below to verify your account:<br>"
		            + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
		            + "Thank you,<br>"
		            + "TeaFarm";
		    
		   
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
		     
		    content = content.replace("[[name]]", accDto.getAlias());
		    String verifyURL = siteURL + "/accounts/verify?code=" + accDto.getVerificationCode();
		     
		    content = content.replace("[[URL]]", verifyURL);
		     
		    helper.setText(content, true);
		     
		    javaMailSender.send(message);
		    
		    
		    
		
	}

}
