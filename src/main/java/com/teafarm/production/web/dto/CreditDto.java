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
	private Employee employee;
	
	public CreditDto() {
		super();
	}
	public CreditDto(int id, @NotEmpty(message = "Commodity should not be null") String commodity, double price,
			Date forDate, Employee employee) {
		super();
		this.id = id;
		this.commodity = commodity;
		this.price = price;
		this.forDate = forDate;
		this.employee = employee;
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
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
	
}
