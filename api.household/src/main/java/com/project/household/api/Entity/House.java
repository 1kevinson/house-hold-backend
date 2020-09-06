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
public abstract class House {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String address;
	private String identifier;

	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = true)
	private Owner owner;

	@OneToMany(mappedBy = "user")
	private Set<Request> requests;

	@OneToMany(mappedBy = "house")
	private Set<Tenant> tenants;

	public Set<Request> getRequests() {
		return requests;
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

	@Override
	public String toString() {
		return "House [id=" + id + ", address=" + address + ", identifier=" + identifier + ", owner=" + owner + "]";
	}

}