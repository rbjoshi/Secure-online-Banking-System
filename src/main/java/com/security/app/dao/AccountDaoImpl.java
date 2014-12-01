package com.security.app.dao;


import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.security.app.model.Account;

@Repository("accountDao")
public class AccountDaoImpl implements AccountDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addAccount(Account account) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(account);
	}
	public Account getAccountByAccountId(String accountId) {
		// TODO Auto-generated method stub
		List<Account> accounts= this.sessionFactory.getCurrentSession().createQuery("from Account A where A.accountId=:accountId").setString("accountId", accountId).list();
		if(accounts != null && accounts.size()>0 && !accounts.isEmpty()){
			return accounts.get(0);
		}
		return null;
	}
	public Account getAccountByUserId(String userId) {
		// TODO Auto-generated method stub
		List<Account> accounts= this.sessionFactory.getCurrentSession().createQuery("from Account A where A.user.userId=:userId").setString("userId", userId).list();
		if(accounts != null && accounts.size()>0 && !accounts.isEmpty()){
			return accounts.get(0);
		}
		return null;
	}
	public void updateAccount(Account account) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().merge(account);
	}
	
}
