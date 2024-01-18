package com.ecommerceapi.controller;

public class AuthResponse {
	
	
	String jwt;
	String message;
	
	public AuthResponse(String jwt, String message) {
		super();
		this.jwt = jwt;
		this.message = message;
	} 
	
	

}
