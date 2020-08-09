package com.project.household.api.Entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.project.household.api.Enumeration.TenantStatus;
import com.project.household.api.Enumeration.UserRole;

@Entity
@DiscriminatorValue("T")
public class Tenant extends User {

	private String role = UserRole.TENANT.getEnumString();
	private String status = TenantStatus.INACTIVE.getEnumString();

	public Tenant() {

	}

	public Tenant(String firstName, String lastName, String email, String password) {
		super(firstName, lastName, email, password);
	}

	public String getRole() {
		return role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(TenantStatus status) {
		this.status = status.getEnumString();
	}
}
