package com.assignment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.models.Statistics;
import com.assignment.services.OrderService;

@RestController
public class StatisticsController {
	
	@Autowired
	private OrderService orderService;

	@GetMapping("/stats")
	public ResponseEntity<Iterable<Statistics>> getStats(@RequestParam(value = "year", required = false) Integer year){
		return new ResponseEntity<Iterable<Statistics>>(orderService.getStats(year), HttpStatus.OK);
	}
	
	

}
