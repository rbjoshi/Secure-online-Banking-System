package com.security.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.security.app.dao.TransactionTypeDao;
import com.security.app.model.TransactionType;

@Service("transactionTypeManager")
public class TransactionTypeManagerImpl implements TransactionTypeManager {

	@Autowired
	private TransactionTypeDao transactionTypeDao;

	@Transactional
	public void addTransactionType(TransactionType transactionType) {
		// TODO Auto-generated method stub
		transactionTypeDao.addTransactionType(transactionType);
	}

	@Transactional
	public TransactionType getTransactionTypeByName(String transactionTypeName) {
		// TODO Auto-generated method stub
		return transactionTypeDao.getTransactionTypeByName(transactionTypeName);
	}

}
