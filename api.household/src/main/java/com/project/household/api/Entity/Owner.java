package com.project.household.api.Entity;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.project.household.api.Enumeration.UserRole;

@Entity
@DiscriminatorValue("O")
public class Owner extends User {

	private String role = UserRole.OWNER.getEnumString();

	@OneToMany(mappedBy = "owner")
	private Set<Bill> bills;

	@OneToMany(mappedBy = "owner")
	private Set<House> houses;

	public Owner() {

	}

	public Owner(String firstName, String lastName, String email, String password) {
		super(firstName, lastName, email, password);
	}

	// GETTERS AND SETTERS
	public String getRole() {
		return role;
	}

	public Set<Bill> getBills() {
		return bills;
	}

	public Set<House> getHouses() {
		return houses;
	}

}
