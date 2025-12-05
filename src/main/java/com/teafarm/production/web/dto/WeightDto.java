package com.teafarm.production.web.dto;

import java.sql.Date;
import com.teafarm.production.entity.Company;
import com.teafarm.production.entity.Employee;

public class WeightDto {
	private int id;
	private double quantity;
	private Date forDate;
	private String companyName;
	private String regNumber;
	private String firstName;
	private String lastName;
	private int companyId;
	private int employeeId;
	
	
	public WeightDto() {
		super();
	}
		
	
	public WeightDto(int id, double quantity, Date forDate, String companyName, String regNumber, String firstName,
			String lastName, int companyId, int employeeId) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.forDate = forDate;
		this.companyName = companyName;
		this.regNumber = regNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.companyId = companyId;
		this.employeeId = employeeId;
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


	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	
	
}
