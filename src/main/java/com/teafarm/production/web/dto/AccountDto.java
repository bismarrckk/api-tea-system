package com.teafarm.production.web.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AccountDto {
	private int id;
	@NotEmpty(message = "Alias should not be null")
	@Size(min = 4, message = "Alias should have at least 4 characters")
	private String alias;
	private boolean active;
	private String email;
	private String verificationCode;
	
	private String firstName;
	private String lastName;
	private String phone;
	private String password;

	public AccountDto() {
		super();
	}
	
	public AccountDto(int id,
			@NotEmpty(message = "Alias should not be null") @Size(min = 4, message = "Alias should have at least 4 characters") String alias,
			boolean active, String email, String verificationCode, String firstName, String lastName, String phone,
			String password) {
		super();
		this.id = id;
		this.alias = alias;
		this.active = active;
		this.email = email;
		this.verificationCode = verificationCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.password = password;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
