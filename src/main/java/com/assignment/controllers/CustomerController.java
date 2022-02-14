package com.assignment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.entities.Customer;
import com.assignment.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	public ResponseEntity<Iterable<Customer>> getAllCustomers(){
		return new ResponseEntity<Iterable<Customer>>(customerService.getAllCustomers(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id){
		return new ResponseEntity<Customer>(customerService.getCustomer(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Customer> getCustomer(@RequestBody Customer customer){
		return new ResponseEntity<Customer>(customerService.addCustomer(customer), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer){
		return new ResponseEntity<Customer>(customerService.updateCustomer(customer), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id){
		customerService.deleteCustomer(id);
		return new ResponseEntity<String>("Records with id: " + id + " deleted successfully!", HttpStatus.OK);
	}
	
	
}
