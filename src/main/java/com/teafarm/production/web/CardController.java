package com.teafarm.production.web;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teafarm.production.entity.Card;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.service.CardService;
import com.teafarm.production.web.dto.CardDto;

@RestController
@RequestMapping("/api/v1/cards/")
@CrossOrigin("http://localhost:8080/")
public class CardController {
	@Autowired
	CardService cardService;
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping
	public List<CardDto> getAllCards(){
		List<Card> card= cardService.getAllCards();
		List<CardDto> cardList=modelMapper.map(card, new TypeToken<List<CardDto>>() {}.getType());
		return cardList;
	}
	@GetMapping("account/{accId}")
	public List<CardDto> getCardByAcc(@PathVariable(name="accId") int accId){
		List<Card> card= cardService.getCardByAcc(accId);
		List<CardDto> cardList=modelMapper.map(card, new TypeToken<List<CardDto>>() {}.getType());
		return cardList;
	}
	@GetMapping("{id}")
	public Card getCardById(@PathVariable(value="id") int id) throws ResourceNotFoundException{
		Card card=cardService.getCardById(id);
		return card;
	}
	@PostMapping
	public CardDto addCard(@RequestBody CardDto cardDto) {
		//convert Dto to Entity
		Card cardRequest=modelMapper.map(cardDto,Card.class);
		Card card=cardService.addCard(cardRequest);
		//convert Entity to Dto
		CardDto cardResponse=modelMapper.map(card, CardDto.class);
		return cardResponse;
	}
	@PutMapping("{id}")
	public CardDto updateCard(@Valid @RequestBody CardDto cardDto,@PathVariable(name="id") int id)
			throws ResourceNotFoundException{
		Card cardRequest=modelMapper.map(cardDto,Card.class);
		Card card=cardService.updateCard(id, cardRequest);
		CardDto cardResponse=modelMapper.map(card, CardDto.class);
		return cardResponse;
		
	}
	@DeleteMapping("{id}")
	public void deletePost(@PathVariable(name = "id") int id) throws ResourceNotFoundException {
		
		cardService.deleteCard(id);

	}
}
