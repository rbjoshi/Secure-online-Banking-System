package com.security.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.security.app.dao.RoleDao;
import com.security.app.model.Role;
import com.security.app.model.User;

@Service("roleManager")
public class RoleManagerImpl implements RoleManager {
	@Autowired
	private RoleDao roleDao;

	@Transactional
	public void addRole(Role role) {
		// TODO Auto-generated method stub
		roleDao.addRole(role);
	}

	@Transactional
	public List<User> getAllRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public Role getRole(String role) {
		return roleDao.getRole(role);
	}
}
