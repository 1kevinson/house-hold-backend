package com.project.household.api.Entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.project.household.api.Enumeration.UserRole;

@Entity
@DiscriminatorValue("A")
public class Admin extends User {
	private String role = UserRole.ADMIN.getEnumString();;

	public String getRole() {
		return role;
	}

}
