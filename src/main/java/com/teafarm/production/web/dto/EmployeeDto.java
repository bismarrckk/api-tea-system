package com.teafarm.production.web.dto;

public class EmployeeDto {


	
	private int id;
	private String firstName;
	private String lastName;
	private String role;
	private boolean status;
	private String phone;
	private long idNo;
	private int accId;
	public EmployeeDto() {
		super();
	}


	public EmployeeDto(int id, String firstName, String lastName, String role, boolean status, String phone, long idNo,
			int accId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.status = status;
		this.phone = phone;
		this.idNo = idNo;
		this.accId = accId;
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


	public String getRole() {
		return role;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
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


	public long getIdNo() {
		return idNo;
	}


	public void setIdNo(long idNo) {
		this.idNo = idNo;
	}


	public int getAccId() {
		return accId;
	}


	public void setAccId(int accId) {
		this.accId = accId;
	}

	
	
}
