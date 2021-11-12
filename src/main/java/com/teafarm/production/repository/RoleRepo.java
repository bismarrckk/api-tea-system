package com.teafarm.production.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teafarm.production.entity.Role;
@Repository
public interface RoleRepo extends JpaRepository<Role,Integer>{
	
}
