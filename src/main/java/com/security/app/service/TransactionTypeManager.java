package com.security.app.service;

import com.security.app.model.TransactionType;

public interface TransactionTypeManager {
	public void addTransactionType(TransactionType transactionType);
	public TransactionType getTransactionTypeByName(String transactionTypeName);
}
