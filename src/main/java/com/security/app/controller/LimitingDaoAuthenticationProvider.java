package com.security.app.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.security.app.model.User;
import com.security.app.model.UserAttempts;
import com.security.app.service.UserAttemptsManager;
import com.security.app.service.UserManager;
import com.security.app.util.StaticMethods;
import com.security.app.util.SystemLoggerService;


public class LimitingDaoAuthenticationProvider extends DaoAuthenticationProvider {
	
	private static final Logger logger = SystemLoggerService.logger;
	
	@Autowired
	private UserManager userManager;
	
	
	@Autowired
	private UserAttemptsManager userAttemptsManager;
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		User user;
		UserAttempts userAttempts;
		try{
			Authentication auth =  super.authenticate(authentication);
			
			 user = userManager.getUserByUserId(authentication.getName());
			 userAttempts = userAttemptsManager.getUserAttempts(authentication.getName());
			
			
			 logger.info("User "+user.getUserId()+" is trying to login for "+userAttempts.getAttempts()+" times");
			
			 if(user !=null ){
				
				userAttempts.setUser(user);
				userAttempts.setAttempts(0);
				userAttempts.setLastModified(StaticMethods.getDate());
				userAttemptsManager.updateAttempts(userAttempts);
				
			}
			
			
			
			return auth;
			
		}
		catch(BadCredentialsException e){
			 user = userManager.getUserByUserId(authentication.getName());
			 userAttempts = userAttemptsManager.getUserAttempts(authentication.getName());
			 
			 logger.info("Bad attempt by "+user.getUserId()+" is trying to login for "+userAttempts.getAttempts()+" times");
				
			 
			if(user!=null && userAttempts != null){
				userAttempts.setAttempts((userAttempts.getAttempts()+1));
				
				userAttempts.setLastModified(StaticMethods.getDate());
				
				
				if(userAttempts.getAttempts()>3){
					user.setAccountNonLocked(false);
					user.setEnabled(false);
					
					//userManager.addUser(user);
				}
				userAttempts.setUser(user);
				userAttemptsManager.updateAttempts(userAttempts);
			}
		
			
		}
		catch(LockedException e){
			
			//System.out.println("you account is locked");
			user = userManager.getUserByUserId(authentication.getName());
			 userAttempts = userAttemptsManager.getUserAttempts(authentication.getName());
			
			
		}
		return null;
	}	

}
