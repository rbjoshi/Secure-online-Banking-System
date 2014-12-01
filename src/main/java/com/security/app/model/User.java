package com.security.app.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "user")
public class User {
	@Id
	@Column(name = "USERID")
	private String userId;

	@Column(name = "PASSWORD")
	private String password;

	@Transient
	private String cpassword;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ROLEIDTOROLE")
	private Role role;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PERSONALINFOIDTOPERSONALINFO")
	private PersonalInfo personalInfo;
	
	@Column(name="ENABLED")
	private boolean enabled;
	
	@Column(name="ACCOUNTNONLOCKED")
	private boolean accountNonLocked;
	
	@Column(name="ACCOUNTNONEXPIRED")
	private boolean accountNonExpired;
	
	@Column(name="CREDENTIALSNONEXPIRED")
	private boolean credentialsNonExpired;
	
	@Column(name="ISNOTDELETED")
	private boolean notDeleted;
	
	@Column(name="PASSKEY")
	private String passKey;
	
	@Transient
	private String roleName;
	
	@Transient
	private String firstName;
	
	@Transient 
	private String middleName;
	
	@Transient
	private String lastName;
	
	@Transient
	private String address;
	
	@Transient
	private String emailId;
	
	@Transient
	private String phoneNumber;
	
	@Transient 
	private String ssn;
	
	@Transient
	private Date dob;
	
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCpassword() {
		return cpassword;
	}
	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}
	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public boolean getAccountNonLocked() {
		return accountNonLocked;
	}
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	
	public boolean getAccountNonExpired(){
		return accountNonExpired;
	}
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	
	public boolean getCredentialsNonExpired(){
		return credentialsNonExpired;
	}
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	public boolean getNotDeleted(){
		return notDeleted;
	}
	public void setNotDeleted(boolean isNotDeleted) {
		this.notDeleted = isNotDeleted;
	}
	
	
	
	
	
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
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getPassKey() {
		return passKey;
	}

	public void setPassKey(String passKey) {
		this.passKey = passKey;
	}

	
}
