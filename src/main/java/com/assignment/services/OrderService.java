package com.assignment.services;

import java.util.Date;
import java.util.List;

import com.assignment.entities.Customer;
import com.assignment.entities.Order;
import com.assignment.models.Statistics;
import com.assignment.requests.OrderRequest;

public interface OrderService {

	void addOrder(OrderRequest orderRequest, Customer customer);

	boolean validateOrderRequest(OrderRequest orderRequest);

	List<Order> findByCustomerId(Long customerId);

	List<Order> getAllOrders();

	Order getOrder(Long id);

	List<Order> findOrdersByFromDateAndToDate(Date fromDate, Date toDate);

	Iterable<Statistics> getStats(Integer year);

}
