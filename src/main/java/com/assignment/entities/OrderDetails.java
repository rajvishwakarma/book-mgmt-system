package com.assignment.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name= "order_details")
@JsonInclude(Include.NON_NULL)
public class OrderDetails extends BaseEntity {
	
	@EmbeddedId
    @JsonIgnore
    private OrderBookKey orderBookKey;

    @Column(nullable = false)
	private Integer quantity;
    
    private Double amount;
    
    @Transient
    private Long bookId;

    public OrderDetails(Order order, Book book, Integer quantity) {
    	orderBookKey = new OrderBookKey();
    	orderBookKey.setOrder(order);
    	orderBookKey.setBook(book);
        this.quantity = quantity;
        this.amount = this.quantity * book.getPrice();
    }
}
