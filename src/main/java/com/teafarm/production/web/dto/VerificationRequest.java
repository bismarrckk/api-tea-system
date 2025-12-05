package com.teafarm.production.web.dto;

import java.time.LocalDateTime;

public class VerificationRequest {
	private String code;

	
	
	public VerificationRequest() {
		super();
	}


	public VerificationRequest(String code) {
		super();
		this.code = code;
		
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	
	
}
