package com.teafarm.production.web.dto;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AccountDto {
	private int id;
	@NotEmpty(message = "Alias should not be null")
	@Size(min = 4, message = "Alias should have at least 4 characters")
	private String alias;
	private boolean active;
	private Date regDate;
	private String email;
	private String verificationCode;
	public AccountDto() {
		super();
	}
	public AccountDto(int id,
			@NotEmpty(message = "Alias should not be null") @Size(min = 4, message = "Alias should have at least 4 characters") String alias,
			boolean active, Date regDate,String email,String verificationCode) {
		super();
		this.id = id;
		this.alias = alias;
		this.active = active;
		this.regDate = regDate;
		this.email=email;
		this.verificationCode=verificationCode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
	
	
	
}
