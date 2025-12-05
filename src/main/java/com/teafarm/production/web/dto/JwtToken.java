package com.teafarm.production.web.dto;

public class JwtToken {
	String token;

	public JwtToken(String token) {
		super();
		this.token = token;
	}

	public JwtToken() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
