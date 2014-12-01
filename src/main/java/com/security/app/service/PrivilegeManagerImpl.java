package com.security.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.security.app.dao.PrivilegeDao;
import com.security.app.model.Privilege;

@Service("privilegeManager")
public class PrivilegeManagerImpl implements PrivilegeManager {

	@Autowired
	private PrivilegeDao privilegeDao;

	@Transactional
	public void addPrivilege(Privilege privilege) {
		// TODO Auto-generated method stub

	}
}
