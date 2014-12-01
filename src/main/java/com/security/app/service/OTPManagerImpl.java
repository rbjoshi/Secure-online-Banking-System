package com.security.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.security.app.dao.OTPDao;
import com.security.app.model.OTP;

@Service("oTPManager")
public class OTPManagerImpl implements OTPManager {

	@Autowired
	private OTPDao otpDao;

	@Transactional
	public void addOTP(OTP otp) {
		// TODO Auto-generated method stub
		otpDao.addOTP(otp);
	}

	@Transactional
	public OTP getOTPByUser(String userId) {
		return otpDao.getOTPByUser(userId);
	}

	public void deleteOTPByUser(String userId) {
		otpDao.deleteOTPByUser(userId);
	}

}
