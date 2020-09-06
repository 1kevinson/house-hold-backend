package com.project.household.api.Entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "HOUSES")
@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class, property = "house_id")
public class House {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String address;
	private String identifier;

	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = true)
	private Owner owner;

	@OneToMany(mappedBy = "house")
	private Set<Tenant> tenants;

	@OneToMany(mappedBy = "house")
	private Set<Room> rooms;

	public Set<Room> getRooms() {
		return rooms;
	}

	public Set<Tenant> getTenants() {
		return tenants;
	}

	// Default constructor
	public House() {
	}

	// Parameters constructor
	public House(String address, String identifier) {
		super();
		this.address = address;
		this.identifier = identifier;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "House [id=" + id + ", address=" + address + ", identifier=" + identifier + ", owner=" + owner + "]";
	}

}