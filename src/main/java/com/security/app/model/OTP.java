package com.security.app.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "otp")
public class OTP implements Serializable{
	@Column(name = "OTP")
	private String otp;

	@Id
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USERIDTOUSER")
	private User user;

	public OTP() {
		// TODO Auto-generated constructor stub
	}

	public OTP(String otp, User user) {
		super();
		this.otp = otp;
		this.user = user;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
