package com.teafarm.production.web.dto;

import java.util.Collection;
import java.sql.Date;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.teafarm.production.entity.Account;
import com.teafarm.production.entity.Role;

public class UserDto {

	private int id;
	@NotEmpty(message = "Fullname should not be null")
	@Size(min = 4, message = "Fullname should have at least 4 characters")
	private String fullname;
	private long phone;
	@Email
	private String email;
	private Date userLog;
	private boolean enabled;
	@NotEmpty(message = "Password should not be null")
	@Size(min = 8, message = "Password should have at least 8 characters")
	private String password;
	private Account account;
	private String accountName;
	private int accId;
	private Collection<Role> roles;
	private String role;

	
	public UserDto() {
		super();
	}

	
	

	public UserDto(int id,
			@NotEmpty(message = "Fullname should not be null") @Size(min = 4, message = "Fullname should have at least 4 characters") String fullname,
			long phone, @Email String email, Date userLog,
			boolean enabled,
			@NotEmpty(message = "Password should not be null") @Size(min = 8, message = "Password should have at least 8 characters") String password,
			Account account, String accountName, int accId, Collection<Role> roles, String role) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.phone = phone;
		this.email = email;
		this.userLog = userLog;
		this.enabled = enabled;
		this.password = password;
		this.account = account;
		this.accountName = accountName;
		this.accId = accId;
		this.roles = roles;
		this.role = role;
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


	public long getPhone() {
		return phone;
	}


	public void setPhone(long phone) {
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

	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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


	public String getAccountName() {
		return accountName;
	}


	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}


	public Collection<Role> getRoles() {
		return roles;
	}


	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}


	public int getAccId() {
		return accId;
	}


	public void setAccId(int accId) {
		this.accId = accId;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}
	
	
		
	
}
