package com.teafarm.production.web;



import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
import com.teafarm.production.entity.User;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.service.AccountService;
import com.teafarm.production.service.UserService;
import com.teafarm.production.web.dto.UserDto;

@RestController    
@RequestMapping("/api/v1/users/")
@CrossOrigin("http://localhost:8080/")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	AccountService accService;

	
	@GetMapping
	public List<UserDto> getAllUsers(){
		List<User> users=userService.getAllUsers();
		List<UserDto> usersList=modelMapper.map(users,new TypeToken<List<UserDto>>() {}.getType());
		return usersList;
	}
	@GetMapping("auth")
	public Object getAuthUser(Authentication auth){
		if(auth != null) {
		return auth.getPrincipal();
		}
		return "auth is null";
	}
	@GetMapping("{id}")
	public UserDto getUserById(@PathVariable(name="id") int id) throws ResourceNotFoundException{
		User user=userService.getUserById(id);
		UserDto userResponse=modelMapper.map(user, UserDto.class);
		return userResponse;
	}
	@GetMapping("email/{email}")
	public boolean getUserByEmail(@PathVariable(name="email") String email){
		User user=userService.getUserByEmail(email);
		if(user!=null) {
			return false;
		}
		return true;
	}
	@GetMapping("account/{accountId}")
	public List<User> getUsersByAccount(@PathVariable(name="accountId") int accountId)
			throws ResourceNotFoundException{
		Account acc=accService.getAccountById(accountId);
		List<User> users=userService.getUserByAccount(acc);
		return users;
		
	}
	//user account
	@PostMapping
	public UserDto addUserAccount(@Valid @RequestBody UserDto userDto)
			throws ResourceNotFoundException{
		User user=userService.getUserByEmail(userDto.getEmail());
		if(user!=null) {
			return  new UserDto();
		}
		Account account=accService.getAccountByName(userDto.getAccountName());
		userDto.setAccount(account);
		User userr=userService.addUser(userDto);
		UserDto userResponse=modelMapper.map(userr,UserDto.class);
		return userResponse;
	}
	//user
	@PostMapping("user")
	public UserDto addUser(@Valid @RequestBody UserDto userDto)
			throws ResourceNotFoundException{
		User user=userService.getUserByEmail(userDto.getEmail());
		if(user!=null) {
			return new UserDto();
		}
		Account account=accService.getAccountById(userDto.getAccId());
		userDto.setAccount(account);
		User userr=userService.addUser(userDto);
		UserDto userResponse=modelMapper.map(userr, UserDto.class);
		return userResponse;
	}
	
	@PutMapping("{id}")
	public UserDto updateUser(@PathVariable(name="id") int id,@Valid @RequestBody UserDto userDto){
	
		User user=userService.updateUser(userDto);
		UserDto userResponse=modelMapper.map(user, UserDto.class);
		return userResponse;
		
	}
	@DeleteMapping("{id}")
	public void deleteUser(@PathVariable(name="id") int id) throws ResourceNotFoundException{
		userService.deleteUser(id);
	
		
	}
	
}
