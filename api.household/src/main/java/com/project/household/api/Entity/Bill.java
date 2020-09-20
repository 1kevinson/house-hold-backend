package com.project.household.api.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "BILLS")
@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class, property = "bill_id")
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String number;
	private String bill_month;
	private String content;
	private Integer bill_year;
	private Integer amount_due;
	private Integer amount_paid;
	private Integer amount_remain;
	private String status;
	private String place;
	private Date date;
	private String signature;

	@ManyToOne
	@JoinColumn(name = "room_id", nullable = true)
	private Room room;

	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = true)
	private Owner owner;

	@ManyToOne
	@JoinColumn(name = "tenant_id", nullable = true)
	private Tenant tenant;

	// Default constructor
	public Bill() {
	}

	// Parameters constructor
	public Bill(String number, String bill_month, Integer bill_year, Integer amount_due, Integer amount_paid,
			Integer amount_remain, String status, String place, Date date, String signature) {
		this.number = number;
		this.bill_month = bill_month;
		this.bill_year = bill_year;
		this.amount_due = amount_due;
		this.amount_paid = amount_paid;
		this.amount_remain = amount_remain;
		this.status = status;
		this.place = place;
		this.date = date;
		this.signature = signature;
	}

	// GETTERS AND SETTERS
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getBill_month() {
		return bill_month;
	}

	public void setBill_month(String bill_month) {
		this.bill_month = bill_month;
	}

	public Integer getBill_year() {
		return bill_year;
	}

	public void setBill_year(Integer bill_year) {
		this.bill_year = bill_year;
	}

	public Integer getAmount_due() {
		return amount_due;
	}

	public void setAmount_due(Integer amount_due) {
		this.amount_due = amount_due;
	}

	public Integer getAmount_paid() {
		return amount_paid;
	}

	public void setAmount_paid(Integer amount_paid) {
		this.amount_paid = amount_paid;
	}

	public Integer getAmount_remain() {
		return amount_remain;
	}

	public void setAmount_remain(Integer amount_remain) {
		this.amount_remain = amount_remain;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}