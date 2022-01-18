package com.teafarm.production.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.teafarm.production.entity.User;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.repository.UserRepo;
@Service
public class UserServiceImpl implements UserService {
	@Autowired 
	UserRepo userRepo;
	
	@Override
	public List<User> getAllUsers() {
	
		return userRepo.findAll();
	}

	@Override
	public User addUser(User user) {
		
		return userRepo.save(user);
	}

	@Override
	public User updateUser(int id, User user) throws ResourceNotFoundException {
		User selectedUser=getUserById(id);
		selectedUser.setFullname(user.getFullname());
		selectedUser.setPhone(user.getPhone());
		selectedUser.setEnabled(user.isEnabled());
		
		return userRepo.save(selectedUser);
				
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

}
