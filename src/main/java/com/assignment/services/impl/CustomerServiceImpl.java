package com.assignment.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.entities.Customer;
import com.assignment.exceptions.ResourceNotFoundException;
import com.assignment.repos.CustomerRepo;
import com.assignment.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo customerRepo;
	
	@Override
	public Iterable<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	@Override
	public Customer getCustomer(Long id) {
		return customerRepo.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
	}

	@Override
	public Customer addCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	@Override
	public void deleteCustomer(Long id) {
		customerRepo.deleteById(id);
	}

	@Override
	public String getCityByCustomerId(Long customerId) {
		return customerRepo.findCityById(customerId);
	}

}
