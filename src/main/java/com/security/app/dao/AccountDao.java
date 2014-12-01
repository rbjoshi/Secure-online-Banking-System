package com.security.app.dao;

import com.security.app.model.Account;

public interface AccountDao {
	public void addAccount(Account account);
	public void updateAccount(Account account);
	public Account getAccountByAccountId(String accountId);
	public Account getAccountByUserId(String userId);
}
