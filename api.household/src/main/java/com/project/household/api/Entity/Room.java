package com.project.household.api.Entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "ROOMS")
@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class, property = "room_id")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	private Integer number;
	private Integer amount;
	private Integer area_length;

	@ManyToOne
	@JoinColumn(name = "house_id", nullable = true)
	private House house;

	@OneToOne(mappedBy = "room")
	private Tenant tenant;

	@OneToMany(mappedBy = "room")
	private Set<Bill> bills;

	// Default constructor
	public Room() {
	}

	// Parameters constructor
	public Room(Integer number, Integer amount, Integer area_length) {
		this.number = number;
		this.amount = amount;
		this.area_length = area_length;
	}

	public Set<Bill> getBills() {
		return bills;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getArea_length() {
		return area_length;
	}

	public void setArea_length(Integer area_length) {
		this.area_length = area_length;
	}

	@Override
	public String toString() {
		return "Rooms [id=" + id + ", number=" + number + ", amount=" + amount + ", area_length=" + area_length + "]";
	}

}