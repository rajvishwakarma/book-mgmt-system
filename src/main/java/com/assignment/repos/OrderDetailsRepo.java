package com.assignment.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.entities.OrderBookKey;
import com.assignment.entities.OrderDetails;

@Repository
public interface OrderDetailsRepo extends CrudRepository<OrderDetails, OrderBookKey>{

}
