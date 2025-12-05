package com.teafarm.production.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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
	public User addUser(User user) {
		Role role=roleRepo.findByName("OWNER");
		List<Role> roles = new ArrayList<>();
		roles.add(role);
		user.setRoles(roles);
		user.setEnabled(true);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
						
		return userRepo.save(user);
	}

	@Override
	public User updateUser(int id,UserDto userDto) throws ResourceNotFoundException {
		
		
		User user=this.getUserById(id);
		user.setEnabled(userDto.isEnabled());
		user.setPhone(userDto.getPhone());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setPhone(userDto.getPhone());
		Role role=roleRepo.findById(userDto.getRoleId()).get();
		List<Role> roles = new ArrayList<>(user.getRoles());
		if (!roles.contains(role)) {
			if (roles.size() > 0) {
			roles.set(0, role);  
			}else {
				roles.add(role);
			}
			user.setRoles(roles);
			
		}
		
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
