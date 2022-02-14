package com.assignment.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assignment.entities.Stock;

@Repository
public interface StockRepo extends CrudRepository<Stock, Long>{

//	@Query(value = "FROM Stock s where s.book.id = :bookId")
//	List<Stock> findByBookId(@Param("bookId") Long bookId);
	
	@Query("SELECT DISTINCT s FROM Stock s WHERE s.book.id = :bookId")
	List<Stock> findByBookId(@Param("bookId") Long bookId);
	
	@Query(value = "SELECT DISTINCT s FROM Stock s WHERE s.warehouse.id = :warehouseId")
	List<Stock> findByWareHouseId(@Param("warehouseId") Long warehouseId);
	
	@Query(value = "Select DISTINCT s from Stock s where s.warehouse.id = :warehouseId and s.book.id = :bookId")
	Stock findByWareHouseIdAndBookId(@Param("warehouseId") Long warehouseId, @Param("bookId") Long bookId);

}
