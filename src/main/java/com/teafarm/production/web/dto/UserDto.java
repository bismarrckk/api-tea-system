package com.teafarm.production.web.dto;

import java.sql.Date;
import java.util.Collection;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.teafarm.production.entity.Role;

public class UserDto {

	private int id;
	@NotEmpty(message = "Firstname should not be null")
	@Size(min = 4, message = "Fullname should have at least 4 characters")
	private String firstName;
	private String lastName;
	private String phone;
	@Email
	private String email;
	private Date userLog;
	private boolean enabled;
//	@NotEmpty(message = "Password should not be null")
//	@Size(min = 8, message = "Password should have at least 8 characters")
	private String password;
	
	private String accountName;
	private int accId;

	private int roleId;
	private Collection<Role> roles;
	
	public UserDto() {
		super();
	}

	
	

	


	public UserDto(int id,
			@NotEmpty(message = "Fullname should not be null") @Size(min = 4, message = "Fullname should have at least 4 characters") String firstName,
			String lastName, String phone, @Email String email, Date userLog, boolean enabled, String password,
			String accountName, int accId, int roleId, Collection<Role> roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.userLog = userLog;
		this.enabled = enabled;
		this.password = password;
		this.accountName = accountName;
		this.accId = accId;
		this.roleId = roleId;
		this.roles = roles;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	
	public String getAccountName() {
		return accountName;
	}


	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}





	public int getAccId() {
		return accId;
	}


	public void setAccId(int accId) {
		this.accId = accId;
	}




	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}







	public Collection<Role> getRoles() {
		return roles;
	}







	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}


		
	
}
