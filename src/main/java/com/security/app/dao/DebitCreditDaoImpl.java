package com.security.app.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.security.app.model.DebitCredit;

@Repository("debitCreditDao")
public class DebitCreditDaoImpl implements DebitCreditDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addDebitCredit(DebitCredit debitCredit) {
		// TODO Auto-generated method stub

	}
}
