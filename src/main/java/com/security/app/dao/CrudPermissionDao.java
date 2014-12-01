package com.security.app.dao;

import com.security.app.model.CrudPermission;

public interface CrudPermissionDao {
	public void addCrudPermission(CrudPermission crudPermission);
	public CrudPermission getCrudPermission(int crudPermissionId);
}
