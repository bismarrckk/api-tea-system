package com.teafarm.production.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teafarm.production.entity.Card;

@Repository
public interface CardRepo extends JpaRepository<Card,Integer> {

}
