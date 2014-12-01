package com.security.app.model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "authorisation")
public class Authorization {

	@Transient
	private int transactionId;
	
	@Transient
	private String empId;
	
	@Transient
	private int permissionId;
	
	@Id
	@Column(name = "AUTHORIZATIONID")
	@GeneratedValue
	private int authorizationId;

	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TRANSACTIONIDTOTRANSACTION")
	private Transaction transaction;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EMPIDTOUSERID")
	private User employee;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ACCOUNTIDTOACCOUNT")
	private Account account;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TRANCRUDPERMIDTOCRUDPERM")
	private CrudPermission tranCRUDPermission;
	
	@Column(name="STATUS")
	private boolean status;
	
	@Column(name="AUTHORIZATIONCOMMENTS")
	private String comments;
	
	public Authorization() {
		// TODO Auto-generated constructor stub
	}

	public int getAuthorizationId() {
		return authorizationId;
	}

	public void setAuthorizationId(int authorizationId) {
		this.authorizationId = authorizationId;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}
	
	public boolean getStatus(){
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getComments(){
		return comments;
	}
	
	public void setComments(String comments){
		this.comments = comments;
	}
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	

	public CrudPermission getTranCRUDPermission() {
		return tranCRUDPermission;
	}

	public void setTranCRUDPermission(CrudPermission tranCRUDPermission) {
		this.tranCRUDPermission = tranCRUDPermission;
	}


	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	public int getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}
}
