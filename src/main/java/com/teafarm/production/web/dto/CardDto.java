package com.teafarm.production.web.dto;

import java.sql.Date;

import javax.validation.constraints.Min;

import com.teafarm.production.entity.Company;

public class CardDto {
	@Min(value=1,message="Quantity should be greater than 1")
	private double quantity;
	private Date forDate;
	private Company company;
		
	public CardDto() {
		super();
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


	
	
	
}
