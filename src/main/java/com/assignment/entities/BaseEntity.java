package com.assignment.entities;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@MappedSuperclass
public class BaseEntity {
	
	private String createdBy;
	
	private String updatedBy;
	
	private Date createdTS = new Date();
	
	private Date updatedTS;
}
