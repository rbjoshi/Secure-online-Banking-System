package com.security.app.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.security.app.model.CrudPermission;

@Repository("crudPermissionDao")
public class CrudPermissionDaoImpl implements CrudPermissionDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addCrudPermission(CrudPermission crudPermission) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(crudPermission);
	}
	public CrudPermission getCrudPermission(int crudPermissionId) {
		// TODO Auto-generated method stub
//		return (CrudPermission) this.sessionFactory.getCurrentSession().createQuery("from CrudPermission C where C.crudPermissionId="+crudPermissionId+"").list().get(0);
		List<CrudPermission> crudPermissions = this.sessionFactory.getCurrentSession().createQuery("from CrudPermission C where C.crudPermissionId=:crudPermissionId")
								.setInteger("crudPermissionId", crudPermissionId).
								list();
		if(crudPermissions!=null && crudPermissions.size()>0){
			if(crudPermissions.get(0)!=null)
				return crudPermissions.get(0);
		}
		return null;
		

	}
	
	
}
