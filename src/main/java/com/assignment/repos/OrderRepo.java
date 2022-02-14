package com.assignment.repos;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assignment.entities.Order;
import com.assignment.models.Statistics;

@Repository
public interface OrderRepo extends CrudRepository<Order, Long>{

	List<Order> findByCustomerId(Long customerId);

	@Query("Select o from Order o where createdTS between :fromDate and :toDate")
	List<Order> findBetween(Date fromDate, Date toDate);

//	@Query(value = "Select year(createdts) as year, month(createdts) as month, count(*) as orderCount, "
//			+ "sum(no_of_books) as bookCount, sum(amount) as totalAmount \n"
//			+ "from orders o \n"
//			+ "group by month(createdts), year(createdts)", nativeQuery = true)
//	Iterable<Statistics> findStats();
	
	
	@Query(value = "Select new com.assignment.models.Statistics(year(createdTS) as year, month(createdTS) as month, count(*) as orderCount, "
			+ "sum(noOfBooks) as bookCount, sum(amount) as totalAmount ) \n"
			+ "from Order o \n"
			+ "group by month(createdTS), year(createdTS)")
	Iterable<Statistics> findStats();
	
	@Query(value = "Select new com.assignment.models.Statistics(year(createdTS) as year, month(createdTS) as month, count(*) as orderCount, "
			+ "sum(noOfBooks) as bookCount, sum(amount) as totalAmount ) \n"
			+ "from Order o \n WHERE year(createdTS) = :year "
			+ "group by month(createdTS), year(createdTS)")
	Iterable<Statistics> findStats(@Param("year") int year);

}
