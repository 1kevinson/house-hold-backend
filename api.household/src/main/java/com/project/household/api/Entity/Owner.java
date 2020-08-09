package com.project.household.api.Entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.project.household.api.Enumeration.UserRole;

@Entity
@DiscriminatorValue("O")
public class Owner extends User {

	private String role = UserRole.OWNER.getEnumString();;

	public Owner() {

	}

	public Owner(String firstName, String lastName, String email, String password) {
		super(firstName, lastName, email, password);
	}

	public String getRole() {
		return role;
	}

}
