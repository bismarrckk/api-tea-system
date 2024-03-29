package com.teafarm.production.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teafarm.production.entity.Account;
import com.teafarm.production.entity.User;
@Repository
public interface UserRepo extends JpaRepository<User,Integer>{

	User findByEmail(String email);
	List<User> findByAccount(Account account);
}
