package com.assignment.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.entities.Book;

@Repository
public interface BookRepo extends CrudRepository<Book, Long>{

}
