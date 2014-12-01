package com.security.app.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.security.app.model.Transaction;
import com.security.app.model.TransactionType;

@Repository("transactionTypeDao")
public class TransactionTypeDaoImpl implements TransactionTypeDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addTransactionType(TransactionType transactionType) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(transactionType);
	}

	public TransactionType getTransactionTypeByName(String transactionTypeName) {
		// TODO Auto-generated method stub
		//List<TransactionType> transactionTypes = this.sessionFactory.getCurrentSession().createQuery("from TransactionType T where T.transactionTypeName ='" + transactionTypeName + "'").list();
		
		List<TransactionType> transactionTypes = this.sessionFactory.getCurrentSession().createQuery("from TransactionType T where T.transactionTypeName =:transactionTypeName").setString("transactionTypeName", transactionTypeName).list();

		if (transactionTypes != null && !transactionTypeName.isEmpty()) {
			return transactionTypes.get(0);
		}
		return null;
	}



}
