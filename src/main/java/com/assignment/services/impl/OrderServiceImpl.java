package com.assignment.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.entities.Book;
import com.assignment.entities.Customer;
import com.assignment.entities.Order;
import com.assignment.entities.OrderDetails;
import com.assignment.enums.OrderStatus;
import com.assignment.exceptions.ResourceNotFoundException;
import com.assignment.models.Statistics;
import com.assignment.repos.OrderDetailsRepo;
import com.assignment.repos.OrderRepo;
import com.assignment.requests.OrderItem;
import com.assignment.requests.OrderRequest;
import com.assignment.services.BookService;
import com.assignment.services.CustomerService;
import com.assignment.services.OrderService;
import com.assignment.services.StockService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private OrderDetailsRepo orderDetailsRepo;
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private CustomerService customerService;
	
	@Override
	public void addOrder(OrderRequest orderRequest, Customer customer) {
		
		Order order = orderRepo.save(new Order(customer, OrderStatus.CREATED));
		
		int qty = 0;
		double amount = 0;
		List<OrderDetails> orderDetails = new ArrayList<>();
		Book book = null;
		for (OrderItem orderItem : orderRequest.getOrders()) {
			book = bookService.getBook(orderItem.getBookId());
			orderDetails.add(new OrderDetails(order, book, orderItem.getQuantity()));
			amount += orderItem.getQuantity() * book.getPrice();
			qty += orderItem.getQuantity();
		}
		orderDetails = (List<OrderDetails>) orderDetailsRepo.saveAll(orderDetails);
		order.setAmount(amount);
		order.setNoOfBooks(qty);
		orderRepo.save(order);
		
		//stock update
		stockService.updateStocks(orderRequest);
	}

	@Override
	public boolean validateOrderRequest(OrderRequest orderRequest) {
		
		String city = customerService.getCityByCustomerId(orderRequest.getCustomerId());
		orderRequest.setCity(city);
		
		return orderRequest.getOrders().stream().anyMatch(order -> 
			!Objects.isNull(bookService.getBook(order.getBookId()))) && stockService.checkStocks(orderRequest);
	}

	@Override
	public List<Order> findByCustomerId(Long customerId) {
		return orderRepo.findByCustomerId(customerId);
	}

	@Override
	public List<Order> getAllOrders() {
		return (List<Order>) orderRepo.findAll();
	}

	@Override
	public Order getOrder(Long id) {
		return orderRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found"));
	}

	@Override
	public List<Order> findOrdersByFromDateAndToDate(Date fromDate, Date toDate) {
		
		return orderRepo.findBetween(fromDate, toDate);
	}

	@Override
	public Iterable<Statistics> getStats(Integer year) {
		if(!Objects.isNull(year))
			return orderRepo.findStats(year);
		return orderRepo.findStats();
	}

}
