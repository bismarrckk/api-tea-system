package com.teafarm.production.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="accounts")
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(unique=true)
	private String alias;
	private boolean active;
	@Column(name="reg_date")
	private Date regDate;
	@OneToMany(mappedBy="account")
	private List<Employee> employees;
	@OneToMany(mappedBy="account")
	private List<User> users;
	private String verificationCode;
	
	
	public Account() {
		super();
	}

	public Account(int id, String alias, boolean active, Date regDate,String verificationCode) {
		super();
		this.id = id;
		this.alias = alias;
		this.active = active;
		this.regDate = regDate;
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

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
	
	
}
