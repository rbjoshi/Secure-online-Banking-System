package com.security.app.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.security.app.model.Authorization;

@Repository("authorizationDao")
public class AuthorizationDaoImpl implements AuthorizationDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addAuthorization(Authorization authorization) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().saveOrUpdate(authorization);
	}

	public void deleteAuthorization(Authorization authorization){
		SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery("delete from authorisation where AUTHORIZATIONID = "+authorization.getAuthorizationId());
		query.executeUpdate();	
	}
	
	public List<Authorization> getAuthorizationsOfAdministrator(String adminid, int permId) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createQuery("from Authorization a where a.status=0 and a.employee.userId=:adminId and a.tranCRUDPermission.crudPermissionId=:permId and a.employee.role.roleId=1")
				.setString("adminId", adminid)
				.setInteger("permId", permId).list();
	}
	
	public List<Authorization> getAuthorizationsOfEmployee(String employeeid, int permId) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createQuery("from Authorization a where a.status=0 and a.employee.userId=:userId and a.tranCRUDPermission.crudPermissionId=:permId and a.employee.role.roleId=2").setString("userId", employeeid).setInteger("permId", permId).list();
	}
	


	public Authorization getAuthorizationsById(String authId) {
		// TODO Auto-generated method stub
		List<Authorization> authorizations =  this.sessionFactory.getCurrentSession().createQuery("from Authorization a where a.status=0 and a.authorizationId=:authId").setString("authId", authId).list();
		if(authorizations!=null && authorizations.size()>0){
			if(authorizations.get(0)!=null)
				return authorizations.get(0);
		}
		return null;

	}
	
	public boolean isTransactionActuallyAuthorized(String empId, int transactionId , int permId){
		List<Authorization> authorizations = this.sessionFactory.getCurrentSession().createQuery("from Authorization a where a.status=0 and a.employee.userId=:empId and a.transaction.transactionId=:transactionId and a.tranCRUDPermission.crudPermissionId=:permId and a.employee.role.roleId=2").setString("empId", empId).setInteger("transactionId", transactionId).setInteger("permId", permId).list();
		if(authorizations!=null && authorizations.size()>0){
			if(authorizations.get(0)!=null)
				return true;
		}
		
		
		return false;
	}
	
	public boolean checkIfAuthorizationExists(int transactionId , int permId){
		List<Authorization> authorizations = this.sessionFactory.getCurrentSession().createQuery("from Authorization a where a.status=0 and a.transaction.transactionId=:transactionId and a.tranCRUDPermission.crudPermissionId=:permId and a.employee.role.roleId=2").setInteger("transactionId", transactionId).setInteger("permId", permId).list();
		if(authorizations!=null && authorizations.size()>0){
			if(authorizations.get(0)!=null)
				return true;
		}
		return false;
	}
	
	public boolean isAccountActuallyAuthorized(String empId, String accountId , int permId){
		List<Authorization> authorizations = this.sessionFactory.getCurrentSession().createQuery("from Authorization a where a.status=0 and a.employee.userId=:empId and a.account.accountId=:accountId and a.tranCRUDPermission.crudPermissionId=:permId and a.employee.role.roleId=1").setString("empId", empId).setString("accountId", accountId).setInteger("permId", permId).list();
		if(authorizations!=null && authorizations.size()>0){
			if(authorizations.get(0)!=null)
				return true;
		}
		
		
		return false;
	}
	
	public boolean checkIfAccountAuthorizationExists(String accountId , int permId){
		List<Authorization> authorizations = this.sessionFactory.getCurrentSession().createQuery("from Authorization a where a.status=0 and a.account.accountId=:accountId and a.tranCRUDPermission.crudPermissionId=:permId and a.employee.role.roleId=1").setString("accountId", accountId).setInteger("permId", permId).list();
		if(authorizations!=null && authorizations.size()>0){
			if(authorizations.get(0)!=null)
				return true;
		}
		return false;
	}
	
}
