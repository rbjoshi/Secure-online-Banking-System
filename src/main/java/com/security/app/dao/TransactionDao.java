package com.security.app.dao;

import java.util.List;

import com.security.app.model.Account;
import com.security.app.model.Payment;
import com.security.app.model.Transaction;
import com.security.app.model.Transfer;
import com.security.app.model.User;

public interface TransactionDao {
	public void addTransaction(Transaction transaction);
	public void deleteTransaction(Transaction transaction);
	public List<Transaction> getTransactions();
	public List<Transaction> getCriticalTransactions();
	public List<Transaction> getUserTransactions(Account account);
	public int addTransferTransaction(Transaction transaction);
	public Transaction getTransactionById(int transactionId);
	public boolean isValidTransaction(String userId, int transactionId);
	public List<Transaction> getRunningTransactions();
	public List<Transaction> getRunningDCTransactions();
	public List<Transaction> getUserDCTransactions(Account account);
	public List<Payment> getUserPaymentTransactions(Account account);
	public List<Transfer> getUserTransferOutTransactions(Account account);
}
