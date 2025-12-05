package com.teafarm.production.service;

import java.sql.Date;
import java.util.List;

import com.teafarm.production.entity.Card;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.web.dto.GrowersSummary;

public interface CardService {
	List<Card> getAllCards();
	Card addCard(Card card);
	Card updateCard(int id,Card card) throws ResourceNotFoundException;
	void deleteCard(int id) throws ResourceNotFoundException;
	Card getCardById(int id) throws ResourceNotFoundException;
	List<Card> getCardByAcc(int id);
	List<GrowersSummary> getGrowersSummary(int id,Date start,Date end);
}
