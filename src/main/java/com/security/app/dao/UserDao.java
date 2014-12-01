package com.security.app.dao;

import java.util.List;

import com.security.app.model.User;

public interface UserDao {
	public void addUser(User user);
	public User getUser(User user);
	public User getUserById(String userId);
	public List<User> getAllUsers();
	
	public List<User> getAdministrators();
	public List<User> getRegEmployees();
	public List<User> getCustomers();
	public List<User> getMerchants();
	
	public void deleteUser(Long userId);
	
	
}
