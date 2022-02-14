package com.assignment.responses;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BaseStockResponse {
	
	BookResponse book;
	WareHouseResponse wareHouse;
	List<StockResponse> stocks;
}
