package com.teafarm.production.web.dto;



import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.teafarm.production.entity.Card;
import com.teafarm.production.entity.User;
import com.teafarm.production.entity.Weight;

public class CompanyDto {
	@NotEmpty(message = "Account name should not be null")
	@Size(min = 4, message = "Company name should have at least 3 characters")
	private String name;
	@NotEmpty(message = "Registration number should not be null")
	private String regNumber;
	@Min(value = 4, message = "Rate should not be less than 3")
	private double rate;
	private User user;
	private List<Weight> weight;
	private List<Card> card;
	
	public CompanyDto() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public List<Weight> getWeight() {
		return weight;
	}

	public void setWeight(List<Weight> weight) {
		this.weight = weight;
	}
	
	public List<Card> getCard() {
		return card;
	}

	public void setCard(List<Card> card) {
		this.card = card;
	}
	
	
}
