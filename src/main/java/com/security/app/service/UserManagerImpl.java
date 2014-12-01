package com.security.app.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.security.app.dao.UserDao;
import com.security.app.model.PersonalInfo;
import com.security.app.model.User;
import com.security.app.util.EmailService;
import com.security.app.util.PasswordEncryptService;

@Service("userManager")
public class UserManagerImpl implements UserManager{
	@Autowired
	private UserDao userDao;
	
	@Autowired
	MailSender mailSender;
	
	public void setUserDao(UserDao userDao){
		this.userDao = userDao;
	}
	@Transactional
	public void addUser(User user) {
		// TODO Auto-generated method stub
		user.setPassword(PasswordEncryptService.getHashedPassword(user.getPassword()));
		userDao.addUser(user);
	}
	@Transactional
	public void updateDisabledUser(User user) {
		// TODO Auto-generated method stub

		userDao.addUser(user);
	}
	
	@Transactional
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}
	
	@Transactional
	public List<User> getAdministrators() {
		// TODO Auto-generated method stub
		return userDao.getAdministrators();
	}
	
	@Transactional
	public List<User> getRegEmployees() {
		// TODO Auto-generated method stub
		return userDao.getRegEmployees();
	}
	
	@Transactional
	public List<User> getCustomers() {
		// TODO Auto-generated method stub
		return userDao.getCustomers();
	}
	
	@Transactional
	public List<User> getMerchants() {
		// TODO Auto-generated method stub
		return userDao.getMerchants();
	}
	
	
	@Transactional
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		userDao.deleteUser(userId);	
		
	}
	
	@Transactional
	public User getUser(User user) {
		// TODO Auto-generated method stub
		return userDao.getUser(user);
	}
	
	@Transactional
	public User getUserByUserId(String userId) {
		// TODO Auto-generated method stub
		return userDao.getUserById(userId);
	}

	public void sendResetPassWordEmail(PersonalInfo personalInfo, String OTP) {
		// TODO Auto-generated method stub
		EmailService service = new EmailService(mailSender);
		service.sendPasswordResetEMail(personalInfo, OTP);
	}
	public void sendAccountCreationPassWordEmail(PersonalInfo personalInfo,
			String password, String passKey) {
		EmailService service = new EmailService(mailSender);
		service.sendAccountCreationPassWordEmail(personalInfo, password,passKey);
	}
	public void sendAccountDeletionNotificationEmail(PersonalInfo personalInfo) {
		// TODO Auto-generated method stub
		EmailService service = new EmailService(mailSender);
		service.sendAccountDeletionNotificationEmail(personalInfo);
	}
	public void sendUserCreationCertificationEmail(PersonalInfo personalInfo,
			String filename,HttpSession session) throws MessagingException  {
		EmailService service = new EmailService(mailSender);
		service.sendCertificateFileEmail(personalInfo, filename,session);
	}
}
