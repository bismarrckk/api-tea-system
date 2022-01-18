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
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	@OneToMany(mappedBy="user", cascade = {
	        CascadeType.ALL
	    })
	private List<Company> companies;

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
		
	public User(int id, String fullname, int phone, String email, Date userLog, boolean enabled,String password)
			{
		super();
		this.id = id;
		this.fullname = fullname;
		this.phone = phone;
		this.email = email;
		this.userLog = userLog;
		this.enabled = enabled;
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
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	
	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	
	
	
}
