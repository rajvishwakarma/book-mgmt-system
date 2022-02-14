package com.assignment.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name= "books")
@JsonInclude(Include.NON_EMPTY)
public class Book extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 4238478923749L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String isbn;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false, length = 25)
    private String title;
    
    private String description;
    
    @Column(length = 50)
    private String publisher;
    
    @Column(length = 10)
    private String yearOfPublication;
    
    private String imageUrl;
    private Double price;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private Set<Stock> stocks;
}
