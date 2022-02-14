package com.assignment.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.entities.Book;
import com.assignment.exceptions.ResourceNotFoundException;
import com.assignment.repos.BookRepo;
import com.assignment.services.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepo bookRepo;
	
	@Override
	public Iterable<Book> getAllBooks() {
		return bookRepo.findAll();
	}

	@Override
	public Book getBook(Long id) {
		return bookRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found"));
	}

	@Override
	public Book addBook(Book book) {
		return bookRepo.save(book);
	}

	@Override
	public Book updateBook(Book book) {
		return bookRepo.save(book);
	}

	@Override
	public void deleteBook(Long id) {
		bookRepo.deleteById(id);
	}

}
