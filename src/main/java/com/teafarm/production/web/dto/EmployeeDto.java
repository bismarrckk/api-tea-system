package com.teafarm.production.web.dto;

import java.util.Date;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import com.teafarm.production.entity.User;


public class EmployeeDto {


	@NotEmpty
	@Size(min = 4, message = "Employee name should have at least 4 characters")
	private String fullname;
	private String role;
	private boolean status;
	private Date regDate;
	private User user;
		
	public EmployeeDto() {
		super();
	}

	public EmployeeDto(String fullname, String role, boolean status, Date regDate, User user) {
		super();
		
		this.fullname = fullname;
		this.role = role;
		this.status = status;
		this.regDate = regDate;
		this.user = user;
	
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

	
}
