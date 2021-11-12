package com.teafarm.production.web.dto;

import java.util.Date;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import com.teafarm.production.entity.Account;

public class UserDto {

	private int id;
	@NotEmpty(message = "Fullname should not be null")
	@Size(min = 4, message = "Fullname should have at least 4 characters")
	private String fullname;
	private int phone;
	@NotEmpty(message = "Email should not be null")
	@Email
	private String email;
	private Date userLog;
	private boolean enabled;
	@NotEmpty(message = "Password should not be null")
	@Size(min = 8, message = "Password should have at least 8 characters")
	private String password;
	private Account account;

	
	public UserDto() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFullname() {
		return fullname;
	}


	public void setFullname(String fullname) {
		this.fullname = fullname;
	}


	public int getPhone() {
		return phone;
	}


	public void setPhone(int phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getUserLog() {
		return userLog;
	}


	public void setUserLog(Date userLog) {
		this.userLog = userLog;
	}



	public boolean isEenabled() {
		return enabled;
	}


	public void setEenabled(boolean eenabled) {
		enabled = eenabled;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Account getAccount() {
		return account;
	}


	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	
}
