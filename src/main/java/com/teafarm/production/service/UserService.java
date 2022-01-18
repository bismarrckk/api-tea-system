package com.teafarm.production.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.teafarm.production.entity.User;
import com.teafarm.production.exception.ResourceNotFoundException;

public interface UserService extends UserDetailsService {
	List<User> getAllUsers();
	User addUser(User user);
	User getUserByEmail(String email);
	User updateUser(int id,User user) throws ResourceNotFoundException;
	void deleteUser(int id) throws ResourceNotFoundException;
	User getUserById(int id) throws ResourceNotFoundException;
}
