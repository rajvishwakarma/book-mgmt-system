package com.assignment.responses;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = 462846284874924L;
	private final String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}
	
}