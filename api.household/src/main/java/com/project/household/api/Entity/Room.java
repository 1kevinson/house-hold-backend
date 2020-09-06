package com.project.household.api.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "ROOMS")
@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class, property = "room_id")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer number;
	private Integer amount;
	private Integer area_length;

	@ManyToOne
	@JoinColumn(name = "house_id", nullable = true)
	private House house;

	// Default constructor
	public Room() {
	}

	// Parameters constructor
	public Room(Integer number, Integer amount, Integer area_length) {
		this.number = number;
		this.amount = amount;
		this.area_length = area_length;
	}

	@Override
	public String toString() {
		return "Rooms [id=" + id + ", number=" + number + ", amount=" + amount + ", area_length=" + area_length + "]";
	}

}