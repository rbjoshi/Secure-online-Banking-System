package com.security.app.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryptService {
	public static String getHashedPassword(String password)
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
		String hashedPassword = encoder.encode(password);
		return hashedPassword;
		
	}

}
