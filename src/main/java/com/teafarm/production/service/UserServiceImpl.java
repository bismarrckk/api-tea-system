package com.teafarm.production.service;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.teafarm.production.entity.Account;
import com.teafarm.production.entity.Role;
import com.teafarm.production.entity.User;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.repository.RoleRepo;
import com.teafarm.production.repository.UserRepo;
import com.teafarm.production.web.dto.UserDto;
@Service
public class UserServiceImpl implements UserService {
	@Autowired 
	UserRepo userRepo;
	@Autowired
	RoleRepo roleRepo;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@Override
	public List<User> getAllUsers() {
		
		return userRepo.findAll(); 
	}

	@Override
	public User addUser(UserDto userDto) {
		userDto.setEnabled(true);
		Role role=roleRepo.findByName(userDto.getRole());
		userDto.setRoles(Arrays.asList(role));
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		User user=modelMapper.map(userDto, User.class);
		
		return userRepo.save(user);
	}

	@Override
	public User updateUser(UserDto userDto) {
		Role role=roleRepo.findByName(userDto.getRole());
		userDto.setRoles(Arrays.asList(role));
		User user=modelMapper.map(userDto, User.class);
		return userRepo.save(user);
				
	}

	@Override
	public void deleteUser(int id) throws ResourceNotFoundException {
		User user=getUserById(id);
		userRepo.delete(user);

		
	}
 
	@Override
	public User getUserById(int id) throws ResourceNotFoundException {
		User user=userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found!!"));
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=userRepo.findByEmail(username);
		if(user==null) {
			throw new UsernameNotFoundException("Email not found!!");
		}
		return new UserDetailsImpl(user);
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		User user=userRepo.findByEmail(email);
		return user;
	}

	@Override
	public List<User> getUserByAccount(Account account) {
		// TODO Auto-generated method stub
		List<User> users= userRepo.findByAccount(account);
		return users;
	}

}
