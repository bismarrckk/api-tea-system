package com.teafarm.production.entity;

import java.util.Collection;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String fullname;
	private int phone;
	private String email;
	@Column(name="user_log")
	private Date userLog;
	private String password;
	private boolean enabled;
	@JsonBackReference(value="account_users")
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="account_id")
	private Account account;
	@JsonManagedReference(value="user_companies")
	@OneToMany(mappedBy="user", cascade = {
	        CascadeType.ALL
	    })
	private List<Company> companies;
	
	@OneToMany(mappedBy="user", cascade = {
	        CascadeType.ALL
	    })
	private List<Credit> credits;

	@ManyToMany(fetch=FetchType.EAGER )
	@JoinTable(
			name="users_roles",
			joinColumns=@JoinColumn(name="user_id",referencedColumnName="id"),
			inverseJoinColumns=@JoinColumn(name="role_id",referencedColumnName="id")
			)
	
	private Collection<Role> roles;
	
	public User() {
		super();
	}
		
	public User(int id, String fullname, int phone, String email, Date userLog, boolean enabled, Account account,String password)
			{
		super();
		this.id = id;
		this.fullname = fullname;
		this.phone = phone;
		this.email = email;
		this.userLog = userLog;
		this.enabled = enabled;
		this.account = account;
		this.password=password;
	
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
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
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

	public Account getAccount() {
		return account;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	
	public List<Credit> getCredits() {
		return credits;
	}

	public void setCredits(List<Credit> credits) {
		this.credits = credits;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	
	
	
}
