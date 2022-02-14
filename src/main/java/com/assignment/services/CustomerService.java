package com.assignment.services;

import com.assignment.entities.Customer;

public interface CustomerService {

	Iterable<Customer> getAllCustomers();
	
	Customer getCustomer(Long id);
	
	Customer addCustomer(Customer customer);
	
	Customer updateCustomer(Customer customer);
	
	void deleteCustomer(Long id);

	String getCityByCustomerId(Long customerId);

}
