package com.assignment.models;

import java.text.DateFormatSymbols;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Statistics {
	
	private int year;
	private String month;
	private long orderCount;
	private long bookCount;
	private Double totalAmount;
	
	public Statistics(int year, int month, long orderCount, long bookCount, Double totalAmount) {
		this.year = year;
		this.month = getMonth(month);
		this.orderCount = orderCount;
		this.bookCount = bookCount;
		this.totalAmount = totalAmount;
	}
	
	public String getMonth(int month) {
	    return new DateFormatSymbols().getMonths()[month-1];
	}
	
	
}
