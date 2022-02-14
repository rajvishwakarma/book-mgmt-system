package com.assignment.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assignment.entities.Customer;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Long>{

	@Query("select c.city from Customer c where c.id =:customerId")
	String findCityById(@Param("customerId") Long customerId);
	
}
