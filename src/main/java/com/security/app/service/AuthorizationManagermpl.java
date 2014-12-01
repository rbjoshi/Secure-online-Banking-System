package com.security.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.security.app.dao.AuthorizationDao;
import com.security.app.model.Authorization;

@Service("authorizationManager")
public class AuthorizationManagermpl implements AuthorizationManager {

	@Autowired
	private AuthorizationDao authorizationDao;

	@Transactional
	public void addAuthorization(Authorization authorization) {
		// TODO Auto-generated method stub
		authorizationDao.addAuthorization(authorization);
	}
	@Transactional
	public void deleteAuthorization(Authorization authorization){
		authorizationDao.deleteAuthorization(authorization);
	}
	
	@Transactional
	public Authorization getAuthorizationsById(String authId) {
		// TODO Auto-generated method stub
		return authorizationDao.getAuthorizationsById(authId);
	}

	@Transactional
	public List<Authorization> getAuthorizationsOfEmployee(String employeeid, int permId) {
		// TODO Auto-generated method stub
		return authorizationDao.getAuthorizationsOfEmployee(employeeid, permId);
	}
	@Transactional
	public List<Authorization> getAuthorizationsOfAdministrator(String adminid, int permId) {
		// TODO Auto-generated method stub
		return authorizationDao.getAuthorizationsOfAdministrator(adminid,permId);
	}
	@Transactional
	public boolean isTransactionActuallyAuthorized(String empId, int transactionId , int permId){
		return authorizationDao.isTransactionActuallyAuthorized(empId, transactionId, permId);
	}
	@Transactional
	public boolean checkIfAuthorizationExists(int transactionId , int permId){
		return authorizationDao.checkIfAuthorizationExists(transactionId, permId);
	}
	@Transactional
	public boolean isAccountActuallyAuthorized(String empId, String accountId,
			int permId) {
		// TODO Auto-generated method stub
		return authorizationDao.isAccountActuallyAuthorized(empId, accountId, permId);
	}
	@Transactional
	public boolean checkIfAccountAuthorizationExists(String accountId,
			int permId) {
		// TODO Auto-generated method stub
		return authorizationDao.checkIfAccountAuthorizationExists(accountId, permId);
	}
}
