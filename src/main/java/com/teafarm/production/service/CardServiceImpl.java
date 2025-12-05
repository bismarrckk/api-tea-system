package com.teafarm.production.service;


import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teafarm.production.entity.Card;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.repository.CardRepo;
import com.teafarm.production.web.dto.GrowersSummary;

@Service
public class CardServiceImpl implements CardService {
	@Autowired
	CardRepo cardRepo;
	@Override
	public List<Card> getAllCards() {
		// TODO Auto-generated method stub
		return cardRepo.findAll();
	}

	@Override
	public Card addCard(Card card) {
		// TODO Auto-generated method stub
		return cardRepo.save(card);
	}

	@Override
	public Card updateCard(int id, Card card) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Card selectedCard=getCardById(id);
		selectedCard.setQuantity(card.getQuantity());
		selectedCard.setForDate(card.getForDate());
		selectedCard.setCompany(card.getCompany());
		return cardRepo.save(selectedCard);
	}

	@Override
	public void deleteCard(int id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Card card=getCardById(id);
		cardRepo.delete(card);
		
	}

	@Override
	public Card getCardById(int id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Card card = cardRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Card details not found!!"));
		return card;
	}

	@Override
	public List<Card> getCardByAcc(int id) {
		// TODO Auto-generated method stub
		return cardRepo.findCardByAcc(id);
	}

	@Override
	public List<GrowersSummary> getGrowersSummary(int id, Date start, Date end) {
		// TODO Auto-generated method stub
		return cardRepo.findGrowersSummary(id,start,end);
	}

	

}
