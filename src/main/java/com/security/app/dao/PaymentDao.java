package com.security.app.dao;

import java.util.List;

import com.security.app.model.Account;
import com.security.app.model.Payment;


public interface PaymentDao {
	public void addPayment(Payment payment);
	public List<Payment> getPaymentRequests(Account account);
	public Payment getPaymentById(String paymentId);
	public List<Payment> getApprovedPaymentRequests(Account account);
	public List<Payment> getSubmittedPaymentRequests(Account account);
}
