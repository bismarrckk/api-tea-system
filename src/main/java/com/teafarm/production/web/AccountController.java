package com.teafarm.production.web;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teafarm.production.entity.Account;
import com.teafarm.production.entity.User;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.service.AccountService;
import com.teafarm.production.service.UserService;
import com.teafarm.production.util.BaseUrlUtil;
import com.teafarm.production.web.dto.AccountDto;
import com.teafarm.production.web.dto.VerificationRequest;


@RestController
@RequestMapping("/accounts")
@CrossOrigin("*")
public class AccountController {
	@Autowired
	AccountService accService;
	@Autowired
	UserService userService;
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping
	public List<AccountDto> getAccounts(){
		List<Account> accounts= accService.getAllAccounts();
		List<AccountDto> accDto=modelMapper.map(accounts, new TypeToken<List<AccountDto>> () {}.getType());
		return accDto;
	}
	@GetMapping("{accountName}")
	public boolean getAccountByName(@PathVariable String accountName)
			throws ResourceNotFoundException{
		Account account=accService.getAccountByName(accountName);
		if(account !=null) {
			return false;
		}
		return true;
	}
	@PostMapping
	public AccountDto createAccount(@Valid @RequestBody AccountDto accDto,HttpServletRequest request) throws ResourceNotFoundException{
//		User user=userService.getUserByEmail(accDto.getEmail());
//		if(user!=null) {
//			return  new AccountDto();
//		}
//		
		String url = BaseUrlUtil.getSiteURL(request);
		Account accRequest=accService.addAccount(accDto,url);
		AccountDto accResponse=modelMapper.map(accRequest, AccountDto.class);
		Account account=accService.getAccountById(accResponse.getId());
		
		User newUser=modelMapper.map(accDto,User.class);
		newUser.setAccount(account);
		
		userService.addUser(newUser);
		
		
		return accResponse;
		
	}
	@PostMapping("/verify")
	public ResponseEntity<?> verifyAcc(@RequestBody VerificationRequest request) {
		return accService.verify(request);
	}
	
	@DeleteMapping("{id}")
	public void deleteAccount(@PathVariable int id)
			throws ResourceNotFoundException {
		accService.deleteAccount(id);
	}
}
