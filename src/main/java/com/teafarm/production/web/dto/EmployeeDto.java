package com.teafarm.production.web.dto;

import java.util.Date;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teafarm.production.entity.Account;


public class EmployeeDto {


	@NotEmpty
	@Size(min = 4, message = "Employee name should have at least 4 characters")
	private String fullname;
	private String role;
	private boolean status;
	private Date regDate;
	private Account account;
		
	public EmployeeDto() {
		super();
	}

	public EmployeeDto(String fullname, String role, boolean status, Date regDate, Account account) {
		super();
		
		this.fullname = fullname;
		this.role = role;
		this.status = status;
		this.regDate = regDate;
		this.account = account;
	
	}

	
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	
}
