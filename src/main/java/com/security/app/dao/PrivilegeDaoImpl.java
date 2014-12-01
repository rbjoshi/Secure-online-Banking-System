package com.security.app.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.security.app.model.Privilege;

@Repository("privilegeDao")
public class PrivilegeDaoImpl implements PrivilegeDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addPrivilege(Privilege privilege) {
		// TODO Auto-generated method stub

	}
}
