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
import com.teafarm.production.entity.Weight;
import com.teafarm.production.exception.ResourceNotFoundException;
import com.teafarm.production.service.CardService;
import com.teafarm.production.web.dto.CardDto;
import com.teafarm.production.web.dto.WeightDto;

@RestController
@RequestMapping("/cards")
@CrossOrigin("*")
public class CardController {
	@Autowired
	CardService cardService;
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping
	public List<CardDto> getAllCards(){
		List<Card> card= cardService.getAllCards();
		List<CardDto> cardDto=modelMapper.map(card, new TypeToken<List<CardDto>>() {}.getType());
		for (int i = 0; i < card.size(); i++) {
            Card cd = card.get(i);
            CardDto dto = cardDto.get(i);
            	
            	dto.setCompanyId(cd.getCompany().getId());
                dto.setCompanyName(cd.getCompany().getName());
                dto.setRegNumber(cd.getCompany().getRegNumber());
                
            
        }
		return cardDto;
	}
	@GetMapping("account/{accId}")
	public List<CardDto> getCardByAcc(@PathVariable int accId){
		List<Card> card= cardService.getCardByAcc(accId);
		List<CardDto> cardList=modelMapper.map(card, new TypeToken<List<CardDto>>() {}.getType());
		for (int i = 0; i < card.size(); i++) {
            Card cd = card.get(i);
            CardDto dto = cardList.get(i);
            	
            	dto.setCompanyId(cd.getCompany().getId());
                dto.setCompanyName(cd.getCompany().getName());
                dto.setRegNumber(cd.getCompany().getRegNumber());
                
            
        }
		return cardList;
	}
	@GetMapping("{id}")
	public CardDto getCardById(@PathVariable int id) throws ResourceNotFoundException{
		Card card=cardService.getCardById(id);
		CardDto cardDto=modelMapper.map(card,CardDto.class);
		cardDto.setCompanyId(card.getCompany().getId());
        cardDto.setCompanyName(card.getCompany().getName());
        cardDto.setRegNumber(card.getCompany().getRegNumber());
		return cardDto;
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
	public CardDto updateCard(@Valid @RequestBody CardDto cardDto,@PathVariable int id)
			throws ResourceNotFoundException{
		Card cardRequest=modelMapper.map(cardDto,Card.class);
		Card card=cardService.updateCard(id, cardRequest);
		CardDto cardResponse=modelMapper.map(card, CardDto.class);
		return cardResponse;
		
	}
	@DeleteMapping("{id}")
	public void deletePost(@PathVariable int id) throws ResourceNotFoundException {
		
		cardService.deleteCard(id);

	}
}
