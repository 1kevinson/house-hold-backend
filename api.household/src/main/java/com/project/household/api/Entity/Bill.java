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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer number;
	private String bill_month;
	private Integer bill_year;
	private Integer amount_due;
	private Integer amount_paid;
	private Integer amount_remain;
	private String status;
	private String place;
	private Date date;
	private String Signature;

	@ManyToOne
	@JoinColumn(name = "room_id", nullable = true)
	private Room room;

	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = true)
	private Owner owner;

	// Default constructor
	public Bill() {
	}

	// Parameters constructor
	public Bill(Integer number, String bill_month, Integer bill_year, Integer amount_due, Integer amount_paid,
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
		Signature = signature;
	}

}