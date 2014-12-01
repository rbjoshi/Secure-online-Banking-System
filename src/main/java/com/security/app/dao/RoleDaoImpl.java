package com.security.app.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.security.app.model.Role;

@Repository("roleDao")
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addRole(Role role) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(role);
	}

	public List<Role> getAllRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	public Role getRole(String role) {
		// TODO Auto-generated method stub
		//List<Role> roles = sessionFactory.getCurrentSession().createQuery("from Role R where R.roleName='" + role + "'").list();
		List<Role> roles = sessionFactory.getCurrentSession().createQuery("from Role R where R.roleName=:roleName").setString("roleName", role).list();

		if (roles != null && !roles.isEmpty()) {
			return roles.get(0);
		}
		return null;
	}

}
