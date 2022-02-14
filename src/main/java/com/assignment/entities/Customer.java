package com.assignment.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name= "customers")
@JsonInclude(Include.NON_NULL)
public class Customer extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false, length = 50)
	private String firstName;
	@Column(length = 50)
	private String lastName;
	
	@Column(nullable = false, unique = true, length = 11)
	private String mobile;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	private String address;
	private String city;
	private String status;
	
	@JsonIgnore
	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="customer_id")
	private Set<Order> orders;
}
