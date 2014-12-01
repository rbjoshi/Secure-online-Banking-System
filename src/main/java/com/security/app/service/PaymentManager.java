package com.security.app.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.security.app.model.Account;
import com.security.app.model.Payment;


public interface PaymentManager {
	public void addPayment(Payment payment);
	public List<Payment> getUserPaymentRequests(Account account);
	public List updatePaymentForApproval(Payment payment, HttpSession session);
	public List<Payment> getApprovedPaymentRequests(Account account);
	public List updatePaymentForSubmission(Payment payment, HttpSession session);
	public List<Payment> getSubmittedPaymentRequests(Account account);
	public List performpaymenttransfer(Payment payment);
}
