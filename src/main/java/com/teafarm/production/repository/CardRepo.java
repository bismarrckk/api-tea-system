package com.teafarm.production.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.teafarm.production.entity.Card;


@Repository
public interface CardRepo extends JpaRepository<Card,Integer> {
	@Query("SELECT c from Card c where c.company.user.account.id=:id order by c.forDate desc")
	List<Card> findCardByAcc(@Param("id") Integer id);
}
