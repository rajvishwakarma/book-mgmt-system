package com.assignment.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BatchUpdateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.jsf.FacesContextUtils;

import com.assignment.entities.Customer;
import com.assignment.entities.Order;
import com.assignment.requests.OrderRequest;
import com.assignment.services.CustomerService;
import com.assignment.services.OrderService;
import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/customers/{id}/orders")
	public Order addOrder(@PathVariable("id") Long customerId, @RequestBody OrderRequest orderRequest) {
		
		orderRequest.setCustomerId(customerId);
		Customer customer = customerService.getCustomer(customerId);
		if(Objects.isNull(customer))
			return null;
		
		if(orderService.validateOrderRequest(orderRequest))
			orderService.addOrder(orderRequest, customer);
		
		return null;
	}
	
	@GetMapping("/orders")
	public ResponseEntity<List<Order>> getOrders(
			@RequestParam(value = "customerId", required = false) Long customerId, 
			@RequestParam(value = "fromDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate, 
			@RequestParam(value = "toDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate) {
		
		List<Order> orders = null;
		
		if(! Objects.isNull(customerId))
			orders = orderService.findByCustomerId(customerId);
		else if(null != fromDate && null != toDate )
			orders = orderService.findOrdersByFromDateAndToDate(fromDate, toDate);
		else 
			orders = orderService.getAllOrders();
		return new ResponseEntity<List<Order>>(CollectionUtils.isEmpty(orders) ? Collections.EMPTY_LIST : orders, HttpStatus.OK);
	}
	
	@GetMapping("/orders/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable("id") Long id){
		return new ResponseEntity<Order>(orderService.getOrder(id), HttpStatus.OK);
	}
	
}
