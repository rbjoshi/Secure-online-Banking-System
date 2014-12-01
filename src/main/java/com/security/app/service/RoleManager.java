package com.security.app.service;

import java.util.List;

import com.security.app.model.Role;
import com.security.app.model.User;

public interface RoleManager {
	public void addRole(Role role);
	public Role getRole(String role);
	public List<User> getAllRoles();
}
