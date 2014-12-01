package com.security.app.service;

import com.security.app.model.CrudPermission;

public interface CrudPermissionManager {
	public void addCrudPermission(CrudPermission crudPermission);
	public CrudPermission getCrudPermission(int crudPermissionId);
}
