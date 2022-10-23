package com.teafarm.production.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.teafarm.production.entity.Account;
import com.teafarm.production.entity.User;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.web.dto.UserDto;

public interface UserService extends UserDetailsService {
	List<User> getAllUsers();
	User addUser(UserDto userDto);
	User getUserByEmail(String email);
	User updateUser(UserDto userDto);
	void deleteUser(int id) throws ResourceNotFoundException;
	User getUserById(int id) throws ResourceNotFoundException;
	List<User> getUserByAccount(Account account);
}
