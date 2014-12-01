package com.security.app.dao;

import java.util.List;

import com.security.app.model.Role;

public interface RoleDao {
	public void addRole(Role user);
	public List<Role> getAllRoles();
	public Role getRole(String role);
}
