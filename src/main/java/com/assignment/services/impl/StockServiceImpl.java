package com.assignment.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.entities.Book;
import com.assignment.entities.Stock;
import com.assignment.entities.WareHouse;
import com.assignment.enums.ValidationType;
import com.assignment.exceptions.StockNotFoundException;
import com.assignment.repos.StockRepo;
import com.assignment.requests.OrderRequest;
import com.assignment.requests.StockRequest;
import com.assignment.services.BookService;
import com.assignment.services.StockService;
import com.assignment.services.WareHouseService;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private StockRepo stockRepo;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private WareHouseService wareHouseService;
	
	@Override
	public Iterable<Stock> getAllStocks() {
		return stockRepo.findAll();
	}

	@Override
	public boolean validate(StockRequest stockRequest, ValidationType type) {
		
		if(ValidationType.BOOK.equals(type))
			return stockRequest.getStocks().stream().anyMatch(stock -> 
				!Objects.isNull(bookService.getBook(stock.getBookId())));
		else if(ValidationType.WAREHOUSE.equals(type))
			return stockRequest.getStocks().stream().anyMatch(stock -> 
			!Objects.isNull(wareHouseService.getWareHouse(stock.getWarehouseId())));
		return false;
	}

	@Override
	public void addStocks(StockRequest stockRequest, Book book) {
		List<Stock> stocks = stockRequest.getStocks().stream().map(stock -> {
								Stock currentStock = stockRepo.findByWareHouseIdAndBookId(stock.getWarehouseId(), book.getId());
								currentStock.setQuantity(currentStock.getQuantity() + stock.getQuantity());
								currentStock.setUpdatedTS(new Date());
								return currentStock;
							}).collect(Collectors.toList());
		stockRepo.saveAll(stocks);
	}

	@Override
	public List<Stock> getStocksByBookId(Long id) {
		List<Stock> stocks = stockRepo.findByBookId(id);
		return stocks.stream()
		.map(stock -> { stock.setWarehouse(stock.getWarehouse()); return stock; })
		.collect(Collectors.toList());
	}

	@Override
	public List<Stock> getStocksByWarehouseId(Long id) {
		List<Stock> stocks = stockRepo.findByWareHouseId(id);
		return stocks.stream()
				.map(stock -> { stock.setBook(stock.getBook()); return stock; })
				.collect(Collectors.toList());
	}

	@Override
	public void addStocks(StockRequest stockRequest, WareHouse wareHouse) {
		List<Stock> stocks = stockRequest.getStocks().stream().map(stock -> {
								Stock currentStock = stockRepo.findByWareHouseIdAndBookId(wareHouse.getId(), stock.getBookId());
								currentStock.setQuantity(currentStock.getQuantity() + stock.getQuantity());
								currentStock.setUpdatedTS(new Date());
								return currentStock;
							}).collect(Collectors.toList());
		stockRepo.saveAll(stocks);
	}

	@Override
	public boolean checkStocks(OrderRequest orderRequest) {
		WareHouse wareHouse = wareHouseService.getWareHouseByCity(orderRequest.getCity());
		orderRequest.setWarehouseId(wareHouse.getId());
		
		orderRequest.getOrders().stream().forEach(order -> {
			Stock stock = stockRepo.findByWareHouseIdAndBookId(wareHouse.getId(), order.getBookId());
			if(stock.getQuantity() < order.getQuantity())
				throw new StockNotFoundException("No Stocks Found for Book: " + order.getBookId());
		});
		return true;
	}

	@Override
	public void updateStocks(OrderRequest orderRequest) {
	
		List<Stock> stocks = orderRequest.getOrders().stream().map(order -> {
			Stock currentStock = stockRepo.findByWareHouseIdAndBookId(orderRequest.getWarehouseId(), order.getBookId());
			currentStock.setQuantity(currentStock.getQuantity() - order.getQuantity());
			currentStock.setUpdatedTS(new Date());
			return currentStock;
		}).collect(Collectors.toList());
		stockRepo.saveAll(stocks);
	}

}
