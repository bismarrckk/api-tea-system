package com.teafarm.production.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="employees")
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String fullname;
	private String role;
	private boolean status;
	@Column(name="reg_date")
	private Date regDate;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	@OneToMany(mappedBy="employee",cascade= {
			CascadeType.ALL
	})
	private List<Weight> weight;
	@OneToMany(mappedBy="employee",cascade= {
			CascadeType.ALL
	})
	private List<Credit> credits;
	
	public Employee(int id, String fullname, String role, boolean status, Date regDate, User user) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.role = role;
		this.status = status;
		this.regDate = regDate;
		this.user = user;
	}

	public Employee() {
		super();
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
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Weight> getWeight() {
		return weight;
	}
	public void setWeight(List<Weight> weight) {
		this.weight = weight;
	}
	public List<Credit> getCredits() {
		return credits;
	}
	public void setCredits(List<Credit> credits) {
		this.credits = credits;
	}
	
	
	

}
