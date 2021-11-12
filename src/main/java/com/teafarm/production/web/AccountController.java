package com.teafarm.production.web;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teafarm.production.entity.Account;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.service.AccountService;
import com.teafarm.production.web.dto.AccountDto;
@CrossOrigin("*")
@RestController
@RequestMapping("/accounts/")
public class AccountController {
	@Autowired 
	AccountService accountService;
	@Autowired ModelMapper modelMapper;
	
	@GetMapping
	public List<AccountDto> getAllAccounts(){
		List<Account> accounts= accountService.getAllAccounts();
		List<AccountDto> accountList=modelMapper.map(accounts, new TypeToken<List<AccountDto>>() {}.getType());
		return accountList;
	}
	@GetMapping("{id}")
	public ResponseEntity<Account> getAccountById(@PathVariable(value="id") int id) throws ResourceNotFoundException{
		Account account=accountService.getAccountById(id);
		return new ResponseEntity<>(account, HttpStatus.OK);
	}
	@PostMapping("save/")
	public ResponseEntity<AccountDto> addAccount(@Valid @RequestBody AccountDto accountDto) {
		//convert Dto to Entity
		Date date=new Date();
		accountDto.setRegDate(date);
		Account accountRequest=modelMapper.map(accountDto,Account.class);
		Account account=accountService.createAccount(accountRequest);
		//convert Entity to Dto
		AccountDto accountResponse=modelMapper.map(account, AccountDto.class);
		
		return new ResponseEntity<AccountDto>(accountResponse,HttpStatus.CREATED);
	}
	@PutMapping("update/{id}")
	public ResponseEntity<AccountDto> updateAccount(@Valid @RequestBody AccountDto accountDto,@PathVariable(name="id") int id)
			throws ResourceNotFoundException{
		Account accountRequest=modelMapper.map(accountDto,Account.class);
		Account account=accountService.updateAccount(id, accountRequest);
		AccountDto accountResponse=modelMapper.map(account, AccountDto.class);
		return new ResponseEntity<>(accountResponse, HttpStatus.OK);
		
	}
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Account> deletePost(@PathVariable(name = "id") int id) throws ResourceNotFoundException {
		
		accountService.deleteAccount(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
