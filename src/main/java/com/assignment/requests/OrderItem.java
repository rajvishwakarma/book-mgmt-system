package com.assignment.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderItem {
	Long bookId;
	Integer quantity;
	Double price;
}
