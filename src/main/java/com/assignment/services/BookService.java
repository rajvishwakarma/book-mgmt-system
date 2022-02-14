package com.assignment.services;

import com.assignment.entities.Book;

public interface BookService {

	Iterable<Book> getAllBooks();
	
	Book getBook(Long id);
	
	Book addBook(Book book);
	
	Book updateBook(Book book);
	
	void deleteBook(Long id);

}
