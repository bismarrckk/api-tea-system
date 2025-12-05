package com.teafarm.production.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teafarm.production.entity.Role;
import com.teafarm.production.repository.RoleRepo;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleRepo repo;
	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}
