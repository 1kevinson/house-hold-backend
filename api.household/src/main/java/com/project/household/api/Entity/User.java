package com.project.household.api.Entity;

import java.util.Set;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "USERS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @JsonSubTypes.Type(value = Tenant.class, name = "tenant"),
		@JsonSubTypes.Type(value = Owner.class, name = "owner"),
		@JsonSubTypes.Type(value = Admin.class, name = "admin") })
//Add this Annotation to avoid Hateaos recursion response
@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class, property = "user_id")
public abstract class User implements UserDetails {

	// Default Serial ID
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String accountStatus;

	@ManyToOne
	@JoinColumn(name = "house_id", nullable = true)
	private House house;

	@OneToMany(mappedBy = "user")
	private Set<Request> requests;

	@OneToMany(mappedBy = "user")
	private Set<Appointment> appointments;

	// Default constructor
	public User() {
	}

	// Parameters constructor
	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public Set<Request> getRequests() {
		return requests;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public String getName() {
		return this.firstName + " " + this.lastName;
	}

	public void setName(String name) {
		String[] parts = name.split(" ");
		this.firstName = parts[0];
		this.lastName = parts[1];
	}

	public Integer getId() {
		return this.id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", accountStatus=" + accountStatus + "]";
	}

}