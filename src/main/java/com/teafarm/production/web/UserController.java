package com.teafarm.production.web;


import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teafarm.production.entity.User;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.service.UserService;
import com.teafarm.production.web.dto.UserDto;

@RestController
@RequestMapping("/api/v1/users/")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping
	public List<UserDto> getAllUsers(){
		List<User> users=userService.getAllUsers();
		List<UserDto> usersList=modelMapper.map(users,new TypeToken<List<UserDto>>() {}.getType());
		return usersList;
	}
	@GetMapping("{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable(name="id") int id) throws ResourceNotFoundException{
		User user=userService.getUserById(id);
		UserDto userResponse=modelMapper.map(user, UserDto.class);
		return new ResponseEntity<>(userResponse,HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto)
			throws ResourceNotFoundException{
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		User userRequest=modelMapper.map(userDto, User.class);
		User user=userService.addUser(userRequest);
		UserDto userResponse=modelMapper.map(user, UserDto.class);
		return new ResponseEntity<>(userResponse,HttpStatus.CREATED);
	}
	@PutMapping("{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable(name="id") int id)
			throws ResourceNotFoundException{
		User userRequest=modelMapper.map(userDto,User.class);
		User user=userService.updateUser(id, userRequest);
		UserDto userResponse=modelMapper.map(user, UserDto.class);
		return new ResponseEntity<>(userResponse,HttpStatus.OK);
		
	}
	@DeleteMapping("{id}")
	public ResponseEntity<User> deleteUser(@PathVariable(name="id") int id) throws ResourceNotFoundException{
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
}
