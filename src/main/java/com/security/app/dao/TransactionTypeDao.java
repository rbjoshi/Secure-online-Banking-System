package com.security.app.dao;

import com.security.app.model.Transaction;
import com.security.app.model.TransactionType;

public interface TransactionTypeDao {
	public void addTransactionType(TransactionType transactionType);
	public TransactionType getTransactionTypeByName(String transactionTypeName);
	
}
