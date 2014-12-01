package com.security.app.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="personalinfo")
public class PersonalInfo {
	
	public PersonalInfo(){
	
	}
	public PersonalInfo(String firstName){
		this.firstName = firstName;
	}
	@Id
	@GeneratedValue
	@Column(name="PERSONALINFOID")
	private int personalInfoId;

	@Column(name="FIRSTNAME")
	private String firstName;
	
	
	@Column(name="LASTNAME")
	private String lastName;
	
	@Column(name="MNAME")
	private String middleName;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="DOB")
	private Date dob;
	
	@Column(name="PHONE")
	private String phone;
	
	@Column(name="EMAILID")
	private String emailId;
	
	@Column(name="SSN")
	private String ssn;
	
	@Column(name="PHOTO")
	private String photo;
	

	
	public String getAddress() {
		return address;
	}
	public Date getDob() {
		return dob;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public int getPersonalInfoId() {
		return personalInfoId;
	}
	public String getPhone() {
		return phone;
	}
	public String getPhoto() {
		return photo;
	}
	public String getSsn() {
		return ssn;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public void setPersonalInfoId(int personalInfoId) {
		this.personalInfoId = personalInfoId;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	
	
}
