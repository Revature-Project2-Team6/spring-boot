package com.revature.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="species")
@Data @AllArgsConstructor @NoArgsConstructor
public class Species {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false, unique=true)
	private String speciesName;
	
	@Column(nullable=false)
	private String description;
	

	public Species(String speciesName, String description) {
		super();
		this.speciesName = speciesName;
		this.description = description;
	}
}
