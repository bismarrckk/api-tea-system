package com.teafarm.production.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
	private String name;
	private boolean status;
	@Column(name="reg_date")
	private Date regDate;
	@Column(name="updated_at")
	private Date updatedAt;
	@Column(name="contact_person")
	private String contactPerson;
	@Column(name="contact_phone")
	private int contactPhone;
	@OneToMany(mappedBy = "account", cascade = {
	        CascadeType.ALL
	    })
	private List<User> users;
	@OneToMany(mappedBy = "account", cascade = {
	        CascadeType.ALL
	    })
	private List<Employee> employees;
		
	public Account() {
		super();
	}
	
	
	
	
	public Account(int id, String name, boolean status, Date regDate, Date updatedAt, String contactPerson,
			int contactPhone) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.regDate = regDate;
		this.updatedAt = updatedAt;
		this.contactPerson = contactPerson;
		this.contactPhone = contactPhone;
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public int getContactPhone() {
		return contactPhone;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setContactPhone(int contactPhone) {
		this.contactPhone = contactPhone;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	
}
