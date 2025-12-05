package com.teafarm.production.entity;


import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="accounts")
@EntityListeners(AuditingEntityListener.class)
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(unique=true)
	private String alias;
	private boolean active;
	private boolean expired;
	@OneToMany(mappedBy="account")
	private List<Employee> employees;
	@OneToMany(mappedBy="account")
	private List<User> users;
	private String verificationCode;
	private LocalDateTime verificationExpiry;
	private String createdBy;
   @LastModifiedBy
	 private String updatedBy;
   @CreatedDate
	 private LocalDateTime createdAt;
   @LastModifiedDate
	 private LocalDateTime updatedAt;
     private Date subscriptionDate;
     private Date subscriptionDueDate;
	
	
	public Account() {
		super();
	}

	
	

	public Account(int id, String alias, boolean active, boolean expired, List<Employee> employees, List<User> users,
			String verificationCode, LocalDateTime verificationExpiry, String createdBy, String updatedBy,
			LocalDateTime createdAt, LocalDateTime updatedAt, Date subscriptionDate, Date subscriptionDueDate) {
		super();
		this.id = id;
		this.alias = alias;
		this.active = active;
		this.expired = expired;
		this.employees = employees;
		this.users = users;
		this.verificationCode = verificationCode;
		this.verificationExpiry = verificationExpiry;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.subscriptionDate = subscriptionDate;
		this.subscriptionDueDate = subscriptionDueDate;
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
	
	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}


	public LocalDateTime getVerificationExpiry() {
		return verificationExpiry;
	}


	public void setVerificationExpiry(LocalDateTime verificationExpiry) {
		this.verificationExpiry = verificationExpiry;
	}




	public Date getSubscriptionDate() {
		return subscriptionDate;
	}




	public void setSubscriptionDate(Date subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}




	public Date getSubscriptionDueDate() {
		return subscriptionDueDate;
	}




	public void setSubscriptionDueDate(Date subscriptionDueDate) {
		this.subscriptionDueDate = subscriptionDueDate;
	}
	
	
	
}
