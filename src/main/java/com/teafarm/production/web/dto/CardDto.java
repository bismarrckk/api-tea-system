package com.teafarm.production.web.dto;

import java.sql.Date;

import javax.validation.constraints.Min;

import com.teafarm.production.entity.Company;

public class CardDto {
	private int id;
	@Min(value=1,message="Quantity should be greater than 1")
	private double quantity;
	private Date forDate;
	private String companyName;
	private String regNumber;
	private int companyId;
		
	public CardDto() {
		super();
	}
	
	public CardDto(int id, @Min(value = 1, message = "Quantity should be greater than 1") double quantity, Date forDate,
			String companyName, String regNumber, int companyId) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.forDate = forDate;
		this.companyName = companyName;
		this.regNumber = regNumber;
		this.companyId = companyId;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}




	
	
	
}
