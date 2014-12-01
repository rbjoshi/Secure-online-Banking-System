package com.security.app.util;

import java.sql.Timestamp;
import java.util.Calendar;

import org.jsoup.Jsoup;

import com.security.app.model.User;

public class StaticMethods {
	public static final String INDEX="redirect:/";
	public static Timestamp getDate(){
			java.util.Calendar cal = Calendar.getInstance();
			java.util.Date utilDate = new java.util.Date(); // your util date
			cal.setTime(utilDate);    
			Timestamp timestamp = new Timestamp(utilDate.getTime());
			

			return timestamp;
	}
	public static boolean isInternalEmployee(User user){
		if(user.getRole().getRoleName().equals("admin") || user.getRole().getRoleName().equals("emp"))
			return true;
		else
			return false;
	}
	
	public static boolean isRegularEmployee(User user){
		if(user.getRole().getRoleName().equals("emp"))
			return true;
		else
			return false;
	}
	
	public static boolean isAdmin(User user){
		if(user.getRole().getRoleName().equals("admin"))
			return true;
		else
			return false;
	}
	public static boolean isAnnonymous(String s){
		System.out.println(s);
		if(s.equals("anonymousUser"))
			return true;
		else
			return false;
	}
	
	public static boolean isCustomer(User user){
		if(user.getRole().getRoleName().equals("customer"))
			return true;
		else
			return false;
	}
	
	public static boolean isMerchant(User user){
		if(user.getRole().getRoleName().equals("merchant"))
			return true;
		else
			return false;
	}
	
	public static String html2text(String html) {
	    return Jsoup.parse(html).text();
	}
}
