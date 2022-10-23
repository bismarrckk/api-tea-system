package com.teafarm.production.web.dto;

import java.sql.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import com.teafarm.production.entity.Account;

public class EmployeeDto {


	
	private int id;
	@NotEmpty
	@Size(min = 4, message = "Employee name should have at least 4 characters")
	private String fullname;
	private String role;
	private boolean status;
	private Date regDate;
	private Account account;
	private long idNo;
	private int accId;
	public EmployeeDto() {
		super();
	}

	public EmployeeDto(int id,String fullname, String role, boolean status, Date regDate,
			Account account,long idNo,int accId) {
		super();
		this.id=id;
		this.fullname = fullname;
		this.role = role;
		this.status = status;
		this.regDate = regDate;
		this.account = account;
		this.idNo=idNo;
		this.accId=accId;
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
