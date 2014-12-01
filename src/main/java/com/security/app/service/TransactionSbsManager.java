package com.security.app.service;

import java.util.List;

import com.security.app.model.Account;
import com.security.app.model.Transaction;

public interface TransactionSbsManager {
	public void addTransaction(Transaction transaction);
	public void deleteTransaction(Transaction transaction);
	public List<Transaction> getTransactions();
	public List<Transaction> getCriticalTransactions();
	public List<Transaction> getUserTransactions(Account account);
	public Transaction getTransactionById(int transactionId);
	public void addTransferTransaction(Transaction transaction) throws Exception;
	public boolean isValidTransaction(String userId, int transactionId);
	public void addPaymentTransaction(Transaction transaction) throws Exception; 
	public List<Transaction> getRunningTransactions();
	public List<Transaction> getRunningDCTransactions();
	public List<Transaction> getUserDCTransactions(Account account);
	public List<Transaction> getUserPaymentTransactions(Account account);
	public List<Transaction> getUserTransferOutTransactions(Account account);
	
}
