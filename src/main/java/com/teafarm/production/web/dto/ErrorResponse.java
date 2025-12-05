package com.teafarm.production.web.dto;

public class ErrorResponse {
	public String code;
	public String message;

	


	public ErrorResponse() {
		super();
	}


	public ErrorResponse(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}




	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	
	
}
