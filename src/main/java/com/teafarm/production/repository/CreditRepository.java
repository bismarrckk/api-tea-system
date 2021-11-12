package com.teafarm.production.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teafarm.production.entity.Credit;

@Repository
public interface CreditRepository extends JpaRepository<Credit,Integer>{

}
