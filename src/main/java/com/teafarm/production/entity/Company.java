package com.teafarm.production.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="companies")
public class Company {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(name="reg_number")
	private String regNumber;
	private double rate;
	@JsonBackReference(value="user_companies")
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	@JsonBackReference(value="account_companies")
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="account_id")
	private Account account;
	@JsonManagedReference(value="weight_company")
	@OneToMany(mappedBy="company",cascade= {
			CascadeType.ALL
	})
	private List<Weight> weight;
	@JsonManagedReference(value="company_cards")
	@OneToMany(mappedBy="company",cascade= {
			CascadeType.ALL
	})
	private List<Card> cards;
	
	public Company() {
		super();
	}

	public Company(int id, String name, String regNumber, double rate, User user, Account account) {
		super();
		this.id = id;
		this.name = name;
		this.regNumber = regNumber;
		this.rate = rate;
		this.user = user;
		this.account = account;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<Weight> getWeight() {
		return weight;
	}

	public void setWeight(List<Weight> weight) {
		this.weight = weight;
	}
	
	
	
}
