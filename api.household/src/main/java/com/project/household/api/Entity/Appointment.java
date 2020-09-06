package com.project.household.api.Entity;

import java.sql.Time;
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
@Table(name = "APPOINTMENTS")
@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class, property = "appointements_id")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer senderId;
	private Integer receiverId;
	private Date date;
	private Time time;
	private String place;
	private String notes;
	private String status;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = true)
	private User user;

	// Default constructor
	public Appointment() {
	}

	public Appointment(String place, String notes, String status, Date date, Time time, User user) {
		this.place = place;
		this.time = time;
		this.notes = notes;
		this.date = date;
		this.status = status;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Request [senderId=" + senderId + ", receiverId=" + receiverId + ",  date=" + date + ", place=" + place
				+ ",notes=" + notes + ", status=" + status + ", user=" + user + "]";
	}

}