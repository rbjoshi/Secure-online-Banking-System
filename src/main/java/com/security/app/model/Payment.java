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
@Table(name = "payment")
public class Payment {

	@Id
	@Column(name = "PAYMENTID")
	@GeneratedValue
	private String paymentId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TRANSACTIONIDTOTRANSACTION")
	private Transaction transaction;

	@Column(name = "MERCHANTNAME")
	private String merchantName;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ACCOUNTIDTOACCOUNT")
	private Account account;
	

	public Payment() {
		// TODO Auto-generated constructor stub
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
