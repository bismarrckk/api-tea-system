package com.teafarm.production.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="card")
public class Card {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private double quantity;
	@Column(name="for_date")
	private Date forDate;
	@JsonBackReference(value="company_cards")
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="company_id")
	private Company company;
	@JsonBackReference(value="account_cards")
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="account_id")
	private Account account;
	
	public Card() {
		super();
	}
	public Card(int id, double quantity, Date forDate, Company company, Account account) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.forDate = forDate;
		this.company = company;
		this.account = account;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public Date getForDate() {
		return forDate;
	}
	public void setForDate(Date forDate) {
		this.forDate = forDate;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	
	
}
