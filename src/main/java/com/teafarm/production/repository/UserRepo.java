package com.teafarm.production.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teafarm.production.entity.User;
@Repository
public interface UserRepo extends JpaRepository<User,Integer>{

	User findByEmail(String email);
}
