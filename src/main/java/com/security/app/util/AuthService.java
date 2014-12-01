package com.security.app.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthService {
	public static String getAuthName(){
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	     String name=authentication.getName();
	     return name;
	}
}
