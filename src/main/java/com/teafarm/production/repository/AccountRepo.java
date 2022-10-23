package com.teafarm.production.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teafarm.production.entity.Account;
@Repository
public interface AccountRepo extends JpaRepository<Account,Integer>{
	Account findByAlias(String alias);
	Account findByVerificationCode(String code);

}
