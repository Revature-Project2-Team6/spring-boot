package com.revature.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="stats")
@Data @AllArgsConstructor @NoArgsConstructor
public class Stats {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int statId;
	
	@Column(name="lvl")
	private int level;
	
	@Column(name="exp")
	private int experience;
	
	@Column(name="health")
	private int health;
	
	@Column(name="str")
	private int strength;
	
	@Column(name = "def")
	private int defense;
	
	@Column(name = "dex")
	private int dexterity;
	
	@Column(name = "fp")
	private int forcePower;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="character_id", referencedColumnName = "id")
	Character character;
}
