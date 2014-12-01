package com.security.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.security.app.dao.AccountDao;
import com.security.app.model.Account;
@Service("accountManager")
public class AccountManagerImpl implements AccountManager{
	
	@Autowired
	private AccountDao accountDao;
	
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	
	@Transactional
	public void addAccount(Account account) {
		// TODO Auto-generated method stub
		accountDao.addAccount(account);
	}
	@Transactional
	public void updateAccount(Account account) {
		// TODO Auto-generated method stub
		accountDao.updateAccount(account);
	}

	@Transactional
	public Account getAccountByAccountId(String accountId) {
		// TODO Auto-generated method stub
		return accountDao.getAccountByAccountId(accountId);
	}

	@Transactional
	public Account getAccountByUserId(String userId) {
		// TODO Auto-generated method stub
		return accountDao.getAccountByUserId(userId);
	}

}
