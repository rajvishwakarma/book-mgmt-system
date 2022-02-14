package com.assignment.requests;

import java.util.List;

import com.assignment.entities.Stock;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class StockRequest {
	
	List<Stock> stocks;
}
