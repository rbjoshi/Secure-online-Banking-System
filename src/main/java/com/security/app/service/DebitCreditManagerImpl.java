package com.security.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.security.app.dao.DebitCreditDao;
import com.security.app.model.DebitCredit;

@Service("debitCreditManager")
public class DebitCreditManagerImpl implements DebitCreditManager {

	@Autowired
	private DebitCreditDao debitCreditDao;

	@Transactional
	public void addDebitCredit(DebitCredit debitCredit) {
		// TODO Auto-generated method stub

	}
}
