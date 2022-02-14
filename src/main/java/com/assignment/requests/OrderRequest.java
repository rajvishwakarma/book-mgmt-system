package com.assignment.requests;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderRequest {
	
	private String city;
	private Long warehouseId;
	private Long customerId;
	private List<OrderItem> orders;
	
}
