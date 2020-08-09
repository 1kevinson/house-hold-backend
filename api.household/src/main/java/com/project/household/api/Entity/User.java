package com.project.household.api.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.household.api.Enumeration.UserRole;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	private UserRole role;
	private String email;
	private String password;

	public User() {
	}

	public User(String firstName, String lastName, UserRole role, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return this.firstName + " " + this.lastName;
	}

	public void setName(String name) {
		String[] parts = name.split(" ");
		this.firstName = parts[0];
		this.lastName = parts[1];
	}

	public Long getId() {
		return this.id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public UserRole getRole() {
		return this.role;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Employee{" + "id=" + this.id + ", firstName='" + this.firstName + '\'' + ", lastName='" + this.lastName
				+ '\'' + ", role='" + this.role + ", email='" + this.email + ", password='" + this.password + '\''
				+ '}';
	}

}