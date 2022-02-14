package com.assignment.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name= "stocks")
@JsonInclude(Include.NON_EMPTY)
public class Stock extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 121783489123891L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;
    
    @JsonBackReference
  	@ManyToOne
    @JoinColumn(name = "warehouse_id")
    private WareHouse warehouse;
    
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
  	
  	@Transient
  	private Long bookId;
  	@Transient
  	private Long warehouseId;
  	
  	public Stock(Integer quantity, Book book, WareHouse warehouse) {
		this.quantity = quantity;
		this.book = book;
		this.warehouse = warehouse;
	}

}
