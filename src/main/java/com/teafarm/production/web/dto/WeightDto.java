package com.teafarm.production.web.dto;



import java.util.Date;

import com.teafarm.production.entity.Company;
import com.teafarm.production.entity.Employee;

public class WeightDto {
	private int id;
	
	private double quantity;
	
	private Date forDate;
	
	private Company company;
	
	private Employee employee;
	
	
	public WeightDto() {
		super();
	}
		

	public WeightDto(int id, double quantity, Date forDate, Company company, Employee employee) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.forDate = forDate;
		this.company = company;
		this.employee = employee;
	
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	
	
}
