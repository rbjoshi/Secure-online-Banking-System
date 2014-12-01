package com.security.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "transactiontype")
public class TransactionType {

	@Override
	public String toString() {
		return "TransactionType [transactionTypeId=" + transactionTypeId
				+ ", transactionTypeName=" + transactionTypeName
				+ ", transactionTableName=" + transactionTableName + "]";
	}

	@Id
	@Column(name = "TRANSACTIONTYPEID")
	private String transactionTypeId;

	@Column(name = "TRANSACTIONTYPENAME")
	private String transactionTypeName;

	@Column(name = "TRANSACTIONTABLENAME")
	private String transactionTableName;

	public TransactionType() {
		// TODO Auto-generated constructor stub
	}

	public String getTransactionTypeId() {
		return transactionTypeId;
	}

	public void setTransactionTypeId(String transactionTypeId) {
		this.transactionTypeId = transactionTypeId;
	}

	public String getTransactionTypeName() {
		return transactionTypeName;
	}

	public void setTransactionTypeName(String transactionTypeName) {
		this.transactionTypeName = transactionTypeName;
	}

	public String getTransactionTableName() {
		return transactionTableName;
	}

	public void setTransactionTableName(String transactionTableName) {
		this.transactionTableName = transactionTableName;
	}
}
