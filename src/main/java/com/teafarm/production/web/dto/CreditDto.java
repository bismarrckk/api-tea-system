package com.teafarm.production.web.dto;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import com.teafarm.production.entity.Employee;

public class CreditDto {
	private int id;
	@NotEmpty(message = "Commodity should not be null")
	private String commodity;
	private double price;
	private Date forDate;
	private String firstName;
	private String lastName;
	private int employeeId;
	
	public CreditDto() {
		super();
	}
	
	

	public CreditDto(int id, @NotEmpty(message = "Commodity should not be null") String commodity, double price,
			Date forDate, String firstName, String lastName, int employeeId) {
		super();
		this.id = id;
		this.commodity = commodity;
		this.price = price;
		this.forDate = forDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.employeeId = employeeId;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCommodity() {
		return commodity;
	}
	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getForDate() {
		return forDate;
	}
	public void setForDate(Date forDate) {
		this.forDate = forDate;
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



	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	
	
	
}
