package com.teafarm.production.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teafarm.production.entity.Role;
import com.teafarm.production.service.RoleService;

@RestController
@RequestMapping("/roles")
@CrossOrigin("*")
public class RolesController {
	
	@Autowired 
	RoleService service;
	
	@GetMapping
	public List<Role> getAll(){
		return service.getRoles();
	}
}
