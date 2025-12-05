package com.teafarm.production.web;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teafarm.production.entity.Account;
import com.teafarm.production.entity.Role;
import com.teafarm.production.entity.User;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.repository.RoleRepo;
import com.teafarm.production.service.AccountService;
import com.teafarm.production.service.UserService;
import com.teafarm.production.web.dto.UserDto;

@RestController    
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	AccountService accService;
	@Autowired
	RoleRepo roleRepo;

	
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
	public UserDto getUserById(@PathVariable int id) throws ResourceNotFoundException{
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
	public List<UserDto> getUsersByAccount(@PathVariable int accountId)
			throws ResourceNotFoundException{
		Account acc=accService.getAccountById(accountId);
		List<User> users=userService.getUserByAccount(acc);
		List<UserDto> dto=modelMapper.map(users, new TypeToken<List<UserDto>>() {}.getType());
//		for(int i=0;i<users.size();i++) {
//			
//		}
		return dto;
		
	}
	//user account
//	@PostMapping
//	public UserDto addUserAccount(@Valid @RequestBody UserDto userDto)
//			throws ResourceNotFoundException{
//		User user=userService.getUserByEmail(userDto.getEmail());
//		if(user!=null) {
//			return  new UserDto();
//		}
//		Account account=accService.getAccountByName(userDto.getAccountName());
//		userDto.setAccount(account);
//		User userr=userService.addUser(userDto);
//		UserDto userResponse=modelMapper.map(userr,UserDto.class);
//		return userResponse;
//	}
//	//user
	@PostMapping("user")
	public UserDto addUser(@Valid @RequestBody UserDto userDto)
			throws ResourceNotFoundException{
		User user=userService.getUserByEmail(userDto.getEmail());
		if(user!=null) {
			return new UserDto();
		}
		Account account=accService.getAccountById(userDto.getAccId());
	
		User userr=modelMapper.map(userDto,User.class);
		userr.setAccount(account);
		
		Role role=roleRepo.findById(userDto.getRoleId()).get();
		Collection<Role> roles=new ArrayList<>();
		roles.add(role);
		userr.setRoles(roles);
		
		userService.addUser(userr);
		
		UserDto userResponse=modelMapper.map(userr, UserDto.class);
		return userResponse;
	}
	
	@PatchMapping("{id}")
	public UserDto updateUser(@PathVariable int id,@Valid @RequestBody UserDto userDto) throws ResourceNotFoundException{
	
		User user=userService.updateUser(id,userDto);
		UserDto userResponse=modelMapper.map(user, UserDto.class);
		return userResponse;
		
	}
	@DeleteMapping("{id}")
	public void deleteUser(@PathVariable  int id) throws ResourceNotFoundException{
		userService.deleteUser(id);
	
		
	}
	
}
