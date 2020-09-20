package com.project.household.api.Entity;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.project.household.api.Enumeration.TenantStatus;
import com.project.household.api.Enumeration.UserRole;

@Entity
@DiscriminatorValue("T")
public class Tenant extends User {

	private String role = UserRole.TENANT.getEnumString();
	private String status = TenantStatus.INACTIVE.getEnumString();

	@OneToOne
	@JoinColumn(name = "room_id", referencedColumnName = "id")
	private Room room;

	@OneToMany(mappedBy = "tenant")
	private Set<Bill> bills;

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
