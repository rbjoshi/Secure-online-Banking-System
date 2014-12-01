package com.security.app.dao;

import java.util.List;

import javax.management.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.security.app.model.User;
@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	 @Autowired
	 private SessionFactory sessionFactory;
	 
	 
	public void addUser(User user) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().saveOrUpdate(user);
		
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		
		return this.sessionFactory.getCurrentSession().createQuery("from User and U.notDeleted=1 ").list();
	}
	public List<User> getAdministrators(){
		
		return this.sessionFactory.getCurrentSession().createQuery("from User U where U.role.roleId=1 and U.notDeleted=1").list();
	}
	public List<User> getRegEmployees(){
		
		return this.sessionFactory.getCurrentSession().createQuery("from User U where U.role.roleId=2 and U.notDeleted=1").list();
	}
	public List<User> getCustomers(){
		
		return this.sessionFactory.getCurrentSession().createQuery("from User U where U.role.roleId=3 and U.notDeleted=1").list();
	}
	public List<User> getMerchants(){
		
		return this.sessionFactory.getCurrentSession().createQuery("from User U where U.role.roleId=4 and U.notDeleted=1").list();
	}
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		User user = (User) sessionFactory.getCurrentSession().load(User.class, userId);
		if(user != null){
				this.sessionFactory.getCurrentSession().delete(user);
		}
		
	}

	public User getUser(User user) {
		// TODO Auto-generated method stub
		//List<User> users = this.sessionFactory.getCurrentSession().createQuery("from User U where U.userId='"+user.getUserId()+"' and U.password='"+user.getPassword()+"' and U.notDeleted=1").list();
		List<User> users =  this.sessionFactory.getCurrentSession().createQuery("from User U where U.userId=:userId and U.password= :password and U.notDeleted=1")
							.setString("userId", user.getUserId())
							.setString("password", user.getPassword())
							.list();
		if(users != null && users.size()>0 && !users.isEmpty()){
			return user;
		}
		return null;
	}

	public User getUserById(String userId) {
		// TODO Auto-generated method stub
		//List<User> users = this.sessionFactory.getCurrentSession().createQuery("from User U where U.userId='"+userId+"' and U.notDeleted=1").list();
		List<User> users = this.sessionFactory.getCurrentSession().createQuery("from User U where U.userId=:userId and U.notDeleted=1")
				.setString("userId", userId)
				.list();
		
		if(users != null && users.size()>0 && !users.isEmpty()){
			users.get(0).setRoleName(users.get(0).getRole().getRoleName());
			return users.get(0);
		}
		return null;
		
	}

}
