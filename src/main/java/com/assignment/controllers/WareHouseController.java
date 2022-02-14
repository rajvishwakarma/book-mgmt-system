package com.assignment.controllers;

import java.util.List;

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

import com.assignment.entities.Stock;
import com.assignment.entities.WareHouse;
import com.assignment.enums.ValidationType;
import com.assignment.exceptions.ResourceNotFoundException;
import com.assignment.requests.StockRequest;
import com.assignment.services.StockService;
import com.assignment.services.WareHouseService;

@RestController
@RequestMapping("/warehouses")
public class WareHouseController {
	
	@Autowired
	private WareHouseService wareHouseService;
	
	@Autowired
	private StockService stockService;

	@GetMapping
	public ResponseEntity<Iterable<WareHouse>> getAllWareHouses(){
		return new ResponseEntity<Iterable<WareHouse>>(wareHouseService.getAllWareHouses(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<WareHouse> getCustomer(@PathVariable("id") Long id) throws ResourceNotFoundException{
		return new ResponseEntity<WareHouse>(wareHouseService.getWareHouse(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<WareHouse> getCustomer(@RequestBody WareHouse wareHouse){
		return new ResponseEntity<WareHouse>(wareHouseService.addWareHouse(wareHouse), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<WareHouse> updateCustomer(@PathVariable("id") Long id, @RequestBody WareHouse wareHouse){
		return new ResponseEntity<WareHouse>(wareHouseService.updateWareHouse(wareHouse), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable("id") Long id){
		wareHouseService.deleteWareHouse(id);
		return new ResponseEntity<String>("Records with id: " + id + " deleted successfully!", HttpStatus.OK);
	}
	
	@GetMapping("/{id}/stocks")
	public ResponseEntity<List<Stock>> getStocks(@PathVariable("id") Long id){
		return new ResponseEntity<List<Stock>>(stockService.getStocksByWarehouseId(id), HttpStatus.OK);
	}
	
	@PostMapping("/{id}/stocks")
	public ResponseEntity<String> addStocks(@PathVariable("id") Long id, @RequestBody StockRequest stockRequest){
		
		WareHouse wareHouse = wareHouseService.getWareHouse(id);
		if(stockService.validate(stockRequest, ValidationType.BOOK))
			stockService.addStocks(stockRequest, wareHouse);
		return new ResponseEntity<String>("Stocks Added/Updated Successfully!", HttpStatus.OK);
	}
}
