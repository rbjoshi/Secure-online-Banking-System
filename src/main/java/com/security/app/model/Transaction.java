package com.security.app.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
@Entity
@Table(name = "transaction")
public class Transaction {

	@Transient
	private String fromAccountId;
	
	@Transient
	private String transactionTypeName;
	
	@Transient
	private String toAccountId;

	
	@Id
	@Column(name = "TRANSACTIONID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int transactionId;

	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TRANSACTIONTYPEIDTOTRANSACTIONTYPE")
	private TransactionType transactionType;

	@OnDelete(action=OnDeleteAction.NO_ACTION)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ACCOUNTIDTOACCOUNT")
	private Account account;

	
	@Column(name = "TIMESTAMP")
	private Timestamp timestamp;

	@Column(name = "AMOUNT")
	private double amount;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "ISCRITICAL")
	private boolean critical;
	
	
	
	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean getCritical(){
		return critical;
	}
	public void setCritical(boolean critical) {
		this.critical = critical;
	}
	public String getFromAccountId() {
		return fromAccountId;
	}
	public void setFromAccountId(String accountId) {
		this.fromAccountId = accountId;
	}

	public String getTransactionTypeName() {
		return transactionTypeName;
	}
	public void setTransactionTypeName(String transactionTypeName) {
		this.transactionTypeName = transactionTypeName;
	}

	public String getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(String toAccountId) {
		this.toAccountId = toAccountId;
	}
	
	

	@Override
	public String toString() {
		return "Transaction [accountId=" + fromAccountId + ", transactionTypeName="
				+ transactionTypeName + ", transactionId=" + transactionId
				+ ", transactionType=" + transactionType + ", account="
				+ account + ", timestamp=" + timestamp + ", amount=" + amount
				+ ", status=" + status + "]";
	}
	
}
