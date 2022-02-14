package com.assignment.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
	
	@GetMapping(value = "/health")
	public ResponseEntity<String> healthChecks() {
		return new ResponseEntity<>("{\"status\":\"OK\"}", HttpStatus.OK);
	}

}
