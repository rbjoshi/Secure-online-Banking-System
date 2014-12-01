package com.security.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.security.app.dao.CrudPermissionDao;
import com.security.app.model.CrudPermission;

@Service("crudPermissionManager")
public class CrudPermissionManagerImpl implements CrudPermissionManager {

	@Autowired
	private CrudPermissionDao crudPermissionDao;

	@Transactional
	public void addCrudPermission(CrudPermission crudPermission) {
		// TODO Auto-generated method stub
		crudPermissionDao.addCrudPermission(crudPermission);
	}

	@Transactional
	public CrudPermission getCrudPermission(int crudPermissionId) {
		// TODO Auto-generated method stub
		return crudPermissionDao.getCrudPermission(crudPermissionId);
	}
}
