package com.assignment.services;

import java.util.List;

import com.assignment.entities.Book;
import com.assignment.entities.Stock;
import com.assignment.entities.WareHouse;
import com.assignment.enums.ValidationType;
import com.assignment.requests.OrderRequest;
import com.assignment.requests.StockRequest;

public interface StockService {

	Iterable<Stock> getAllStocks();

	boolean validate(StockRequest stockRequest, ValidationType type);

	void addStocks(StockRequest stockRequest, Book book);
	
	void addStocks(StockRequest stockRequest, WareHouse wareHouse);

	List<Stock> getStocksByBookId(Long id);
	
	List<Stock> getStocksByWarehouseId(Long id);

	boolean checkStocks(OrderRequest orderRequest);

	void updateStocks(OrderRequest orderRequest);

}
