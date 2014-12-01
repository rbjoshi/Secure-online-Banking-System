package com.security.app.service;

import com.security.app.model.OTP;

public interface OTPManager {
	public void addOTP(OTP otp);
	public OTP getOTPByUser(String userId);
	public void deleteOTPByUser(String userId);
}
