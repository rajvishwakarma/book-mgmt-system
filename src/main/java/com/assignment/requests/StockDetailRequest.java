package com.assignment.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class StockDetailRequest {
	
	private Long warehouseId;
	private Long bookId;
	private Integer quantity;
}
