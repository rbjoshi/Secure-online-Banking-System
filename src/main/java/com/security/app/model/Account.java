package com.security.app.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="account")

public class Account {
	
	@Override
	public String toString() {
		return "Account [userId=" + userId + ", accountId=" + accountId
				+ ", user=" + user + ", accountType=" + accountType
				+ ", balance=" + balance + "]";
	}


	@Transient
	private String userId;
	
	@Transient
	private String firstName;
	
	@Transient
	private String lastName;
	
	@Transient
	private String address;

	@Transient
	private String phone;
	
	@Transient
	private String emailId;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	@Id
	@Column(name="ACCOUNTID")
	private String accountId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="USERIDTOUSER")
	private User user;
	
	@Column(name="ACCOUNTTYPE")
	private String accountType;
	
	@Column(name="BALANCE")
	private double balance;
	
	public String getAccountId() {
		return accountId;
	}
	public String getAccountType() {
		return accountType;
	}
	public double getBalance() {
		return balance;
	}
	public User getUser() {
		return user;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	
	
	public void setUser(User user) {
		this.user = user;
	}
}
