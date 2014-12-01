package com.security.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.security.app.dao.UserAttemptsDao;
import com.security.app.model.UserAttempts;


@Service("userAttemptsManager")
public class UserAttemptsManagerImpl implements UserAttemptsManager {

	@Autowired
	UserAttemptsDao userAttemptsDao;

	@Transactional
	public void updateAttempts(UserAttempts userAttempts) {
		// TODO Auto-generated method stub
		userAttemptsDao.updateAttempts(userAttempts);
	}

	@Transactional
	public UserAttempts getUserAttempts(String username) {
		// TODO Auto-generated method stub
		return userAttemptsDao.getUserAttempts(username);
	}
	
	
}
