package com.security.app.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.security.app.model.UserAttempts;


@Repository("userAttemptsDao")
public class UserAttemptsDaoImpl implements UserAttemptsDao {

	@Autowired
	SessionFactory sessionFactory;

	public void updateAttempts(UserAttempts userAttempts) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().saveOrUpdate(userAttempts);
		
	}


	public UserAttempts getUserAttempts(String username) {
		// TODO Auto-generated method stub
		List<UserAttempts> userAttempts=this.sessionFactory.getCurrentSession().createQuery("from UserAttempts Ua where Ua.user.userId=:userId").setString("userId", username).list();
		if(userAttempts != null && userAttempts.size()>0){
			if(userAttempts.get(0)!=null){
				return userAttempts.get(0);
			}
		}
		 return null;
	}
	
}
