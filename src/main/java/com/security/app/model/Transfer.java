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
@Entity
@Table(name = "transfer")
public class Transfer {

	@Id
	@Column(name = "TRANSFERID")
	@GeneratedValue
	private String transferId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TRANSACTIONIDTOTRANSACTION")
	private Transaction transaction;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ACCOUNTIDTOACCOUNT")
	private Account fromAccount;

	public Transfer() {
		// TODO Auto-generated constructor stub
	}

	public String getTransferId() {
		return transferId;
	}

	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public Account getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}

	@Override
	public String toString() {
		return "Transfer [transferId=" + transferId + ", transaction="
				+ transaction + ", fromAccount=" + fromAccount + "]";
	}

	


}
