package com.security.app.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.security.app.model.PersonalInfo;

@Repository("personalInfoDao")
public class PersonalInfoDaoImpl implements PersonalInfoDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addPersonalInfo(PersonalInfo info) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(info);
	
	}
	
	public void updatePersonalInfo(PersonalInfo info){
	
		sessionFactory.getCurrentSession().saveOrUpdate(info);
	}
}
