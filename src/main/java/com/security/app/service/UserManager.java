package com.security.app.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import com.security.app.model.PersonalInfo;
import com.security.app.model.User;

public interface UserManager {
	public void addUser(User user);
	public User getUser(User user);
	public User getUserByUserId(String Id);
	public List<User> getAllUsers();
	
	public List<User> getAdministrators();
	public List<User> getRegEmployees();
	public List<User> getCustomers();
	public List<User> getMerchants();
	
	public void deleteUser(Long userId);
	public void sendResetPassWordEmail(PersonalInfo personalInfo, String OTP);
	public void sendAccountCreationPassWordEmail(PersonalInfo personalInfo, String password, String passKey);
	public void sendAccountDeletionNotificationEmail(PersonalInfo personalInfo);
	public void sendUserCreationCertificationEmail(PersonalInfo personalInfo, String userId, HttpSession session) throws MessagingException;

}
