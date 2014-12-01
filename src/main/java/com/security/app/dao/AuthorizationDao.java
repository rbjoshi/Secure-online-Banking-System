package com.security.app.dao;

import java.util.List;

import com.security.app.model.Authorization;

public interface AuthorizationDao {
	public void addAuthorization(Authorization authorization);
	public List<Authorization>  getAuthorizationsOfAdministrator(String adminid, int permId);
	public List<Authorization> getAuthorizationsOfEmployee(String employeeid, int permId);
	public Authorization  getAuthorizationsById(String authId);
	public void deleteAuthorization(Authorization authorization);
	public boolean isTransactionActuallyAuthorized(String empId, int transactionId , int permId);
	public boolean checkIfAuthorizationExists(int transactionId , int permId);
	public boolean isAccountActuallyAuthorized(String empId, String accountId , int permId);
	public boolean checkIfAccountAuthorizationExists(String accountId , int permId);
}
