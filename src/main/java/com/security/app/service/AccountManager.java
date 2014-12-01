package com.security.app.service;

import com.security.app.model.Account;

public interface AccountManager {
	public void addAccount(Account account);
	public void updateAccount(Account account);
	public Account getAccountByAccountId(String accountId);
	public Account getAccountByUserId(String userId);
	
}
