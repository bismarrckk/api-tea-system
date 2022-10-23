package com.teafarm.production.web;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teafarm.production.entity.Account;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.service.AccountService;
import com.teafarm.production.util.BaseUrlUtil;
import com.teafarm.production.web.dto.AccountDto;

@RestController
@RequestMapping("/api/v1/accounts/")
@CrossOrigin("http://localhost:8080/")
public class AccountController {
	@Autowired
	AccountService accService;
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping
	public List<AccountDto> getAccounts(){
		List<Account> accounts= accService.getAllAccounts();
		List<AccountDto> accDto=modelMapper.map(accounts, new TypeToken<List<AccountDto>> () {}.getType());
		return accDto;
	}
	@GetMapping("{accountName}")
	public boolean getAccountByName(@PathVariable(name="accountName") String accountName)
			throws ResourceNotFoundException{
		Account account=accService.getAccountByName(accountName);
		if(account !=null) {
			return false;
		}
		return true;
	}
	@PostMapping
	public AccountDto createAccount(@Valid @RequestBody AccountDto accDto,HttpServletRequest request){
		String url = BaseUrlUtil.getSiteURL(request);
		Account accRequest=accService.addAccount(accDto,url);
		AccountDto accResponse=modelMapper.map(accRequest, AccountDto.class);
		return accResponse;
		
	}
	@GetMapping("/verify")
	public void verifyAcc(@Param("code") String code) {
	    accService.verifyCode(code);
	       
	}
	
	@DeleteMapping("{id}")
	public void deleteAccount(@PathVariable(name="id") int id)
			throws ResourceNotFoundException {
		accService.deleteAccount(id);
	}
}
