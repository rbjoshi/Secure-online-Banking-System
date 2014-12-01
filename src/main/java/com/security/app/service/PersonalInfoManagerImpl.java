package com.security.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.security.app.dao.PersonalInfoDao;
import com.security.app.model.PersonalInfo;

@Service("personalInfoManager")
public class PersonalInfoManagerImpl implements PersonalInfoManager {
	@Autowired
	PersonalInfoDao personalInfoDao;
	
	@Transactional
	public void addPersonalInfo(PersonalInfo info) {
		// TODO Auto-generated method stub
		personalInfoDao.addPersonalInfo(info);
		
	}

	@Transactional
	public void updatePersonalInfo(PersonalInfo info) {
		
		personalInfoDao.updatePersonalInfo(info);
		
	}

}
