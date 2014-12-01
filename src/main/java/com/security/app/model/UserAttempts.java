package com.security.app.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="user_attempts")
public class UserAttempts {

	@Id
	@Column(name="ID")
	@GeneratedValue
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="USERIDTOUSER")
	private User user;
	
	@Column(name="ATTEMPTS")
	private int attempts;
	
	@Column(name="LASTMODIFIED")
	private Timestamp lastModified;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	public int getAttempts() {
		return attempts;
	}
		public void setAttempts(int attempts) {
		this.attempts = attempts;
	}
	
	public Timestamp getLastModified() {
		return lastModified;
	}
	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}
	
}
