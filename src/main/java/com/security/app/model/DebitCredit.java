package com.security.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "debitcredit")
public class DebitCredit {

	@Id
	@Column(name = "DEBITCREDITID")
	private String debitCreditId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TRANSACTIONIDTOTRANSACTION")
	private Transaction transaction;

	@Column(name = "TYPE")
	private String type;
	
	public DebitCredit() {
		// TODO Auto-generated constructor stub
	}

	public String getDebitCreditId() {
		return debitCreditId;
	}

	public void setDebitCreditId(String debitCreditId) {
		this.debitCreditId = debitCreditId;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
