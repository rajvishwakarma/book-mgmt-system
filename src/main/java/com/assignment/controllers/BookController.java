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

import com.assignment.entities.Book;
import com.assignment.entities.Stock;
import com.assignment.enums.ValidationType;
import com.assignment.requests.StockRequest;
import com.assignment.services.BookService;
import com.assignment.services.StockService;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private StockService stockService;

	@GetMapping
	public ResponseEntity<Iterable<Book>> getAllBooks(){
		return new ResponseEntity<Iterable<Book>>(bookService.getAllBooks(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getCustomer(@PathVariable("id") Long id){
		return new ResponseEntity<Book>(bookService.getBook(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Book> getCustomer(@RequestBody Book book){
		return new ResponseEntity<Book>(bookService.addBook(book), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Book> updateCustomer(@PathVariable("id") Long id, @RequestBody Book book){
		return new ResponseEntity<Book>(bookService.updateBook(book), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable("id") Long id){
		bookService.deleteBook(id);
		return new ResponseEntity<String>("Records with id: " + id + " deleted successfully!", HttpStatus.OK);
	}
	
	@GetMapping("/{id}/stocks")
	public ResponseEntity<List<Stock>> getStocks(@PathVariable("id") Long id){
		return new ResponseEntity<List<Stock>>(stockService.getStocksByBookId(id), HttpStatus.OK);
	}
	
	@PostMapping("/{id}/stocks")
	public ResponseEntity<String> addStocks(@PathVariable("id") Long id, @RequestBody StockRequest stockRequest){
		
		Book book = bookService.getBook(id);
		if(stockService.validate(stockRequest, ValidationType.WAREHOUSE))
			stockService.addStocks(stockRequest, book);
		return new ResponseEntity<String>("Stocks Added/Updated Successfully!", HttpStatus.OK);
	}
}
