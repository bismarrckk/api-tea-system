package com.teafarm.production.entity;



import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name="weight")
public class Weight {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private double quantity;
	@Column(name="for_date")
	private Date forDate;
	@JsonBackReference(value="company-weight")
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="company_id")
	private Company company;
	@JsonBackReference(value="employee-weight")
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	
	public Weight() {
		super();
	}
	
	
	public Weight(int id, double quantity, Date forDate, Company company, Employee employee) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.forDate = forDate;
		this.company = company;
		this.employee = employee;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public Date getForDate() {
		return forDate;
	}
	public void setForDate(Date forDate) {
		this.forDate = forDate;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	

}
