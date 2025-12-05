package com.teafarm.production.web.dto;



import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.teafarm.production.entity.Card;
import com.teafarm.production.entity.User;
import com.teafarm.production.entity.Weight;

public class CompanyDto {
	private int id;
	@NotEmpty(message = "Account name should not be null")
	@Size(min = 3, message = "Company name should have at least 3 characters")
	private String name;
	@NotEmpty(message = "Registration number should not be null")
	private String regNumber;
	private double rate;
	private int userId;
	private String firstName;
	private String lastName;
	private List<Weight> weight;
	private List<Card> card;
	
	public CompanyDto() {
		super();
	}
	

	public CompanyDto(int id,
			@NotEmpty(message = "Account name should not be null") @Size(min = 3, message = "Company name should have at least 3 characters") String name,
			@NotEmpty(message = "Registration number should not be null") String regNumber, double rate, int userId,
			String firstName, String lastName, List<Weight> weight, List<Card> card) {
		super();
		this.id = id;
		this.name = name;
		this.regNumber = regNumber;
		this.rate = rate;
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.weight = weight;
		this.card = card;
	}









	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
