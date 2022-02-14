package com.assignment.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.assignment.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name= "orders")
@JsonInclude(Include.NON_NULL)
public class Order extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	private Integer noOfBooks;
	
	private Double amount;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@JsonBackReference
	@ManyToOne
	private Customer customer;
	
	@JsonManagedReference
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="order_id")
    private Set<OrderDetails> details;

	public Order(Customer customer, OrderStatus status) {
		
		this.customer = customer;
		this.status = status;
	}
	
}
