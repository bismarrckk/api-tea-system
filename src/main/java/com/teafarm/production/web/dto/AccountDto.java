package com.teafarm.production.web.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.teafarm.production.entity.Employee;
import com.teafarm.production.entity.User;



public class AccountDto {
	
	private int id;
	@NotEmpty(message = "Account name should not be null")
	@Size(min = 4, message = "Account name should have at least 4 characters")
	private String name;
	private boolean status;
	private Date regDate;
	@NotEmpty(message = "Contact phone number should not be null")
	@Size(min = 4, message = "Contact person's name should have at least 6 characters")
	private String contactPerson;
	@NotNull(message = "Contact phone number shouldnot be null")
	private int contactPhone;
	private Date updatedAt;
	private List<Employee> employees;
	private List<User> users;
			
	public AccountDto() {
		super();
	}
	
	
	public AccountDto(int id,String name,boolean status, Date regDate, String contactPerson, int contactPhone,Date updatedAt) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.regDate = regDate;
		this.contactPerson = contactPerson;
		this.contactPhone = contactPhone;
		this.updatedAt=updatedAt;
		
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
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}



	public String getContactPerson() {
		return contactPerson;
	}



	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}



	public int getContactPhone() {
		return contactPhone;
	}



	public void setContactPhone(int contactPhone) {
		this.contactPhone = contactPhone;
	}

	

	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


	public List<Employee> getEmployees() {
		return employees;
	}


	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
	

	

}
