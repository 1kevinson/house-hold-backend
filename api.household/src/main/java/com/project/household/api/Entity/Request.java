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
@Table(name = "REQUESTS")
@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class, property = "request_id")
public class Request {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer senderId;
	private Integer receiverId;
	private String content;
	private Date date;
	private String status;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = true)
	private User user;

	// Default constructor
	public Request() {
	}

	public Request(String content, String status, Date date, User user) {
		this.content = content;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
		return "Request [senderId=" + senderId + ", receiverId=" + receiverId + ", content=" + content + ", date="
				+ date + ", status=" + status + ", user=" + user + "]";
	}

}