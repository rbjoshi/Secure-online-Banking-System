package com.security.app.dao;

import com.security.app.model.OTP;

public interface OTPDao {
	public void addOTP(OTP otp);
	public OTP getOTPByUser(String userId);
	public void deleteOTPByUser(String userId);
}
