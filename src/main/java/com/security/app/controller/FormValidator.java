package com.security.app.controller;



import java.sql.Date;
import java.util.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.security.app.controller.*;
import com.security.app.model.*;
import com.security.app.dao.*;


@Component("formValidator")
public class FormValidator {
  public boolean supports(Class<?> cl1) {
    return PersonalInfo.class.isAssignableFrom(cl1);
  }
  
  long ssn,phone;
  double ammount;

  public void validate(Object target, Errors errors,String uname) {
    PersonalInfo registration = (PersonalInfo) target;
    try{
	    	ssn = Long.parseLong(registration.getSsn());
		   
	}catch(Exception e){
		errors.rejectValue("ssn",
		          "lengthOfUser.registration.ssn",
		          "SSN must be in digits.");
    }
    
   
    
    try{
    	phone =Long.parseLong(registration.getPhone());
	}catch(Exception e){
		errors.rejectValue("phoneNumber",
		          "lengthOfUser.registration.phoneNumber",
		          "Phone number must be in digits.");
	}
    
    String userId=uname;
    System.out.print("check2");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId",
            "NotEmpty.user.userId",
            "User Name must not be Empty.");
    
    if ((userId.length()) > 10) {
      errors.rejectValue("userId",
          "lengthOfUser.user.userId",
          "User Name must not more than 10 characters.");
    }
    if (registration.getAddress().equals("")||registration.getAddress() == null ) {
      errors.rejectValue("address",
          "NotEmpty.registration.address",
          "Address must not be Empty.");
    }
    if (registration.getEmailId().equals("") || registration.getEmailId().equals("[A-Z]+[a-zA-Z_]+@\b([a-zA-Z]+.){2}\b?.[a-zA-Z]+") || registration.getEmailId() == null ) {
        errors.rejectValue("emailId",
            "NotEmpty.registration.emailId",
            "Email Id must be Entered Properly.");
     }
    if (registration.getDob() == null) {
        errors.rejectValue("dob",
            "NotEmpty.registration.dob",
            "Date Of Birth must not be Empty.");
   }
   
    if (registration.getFirstName().equals("") || registration.getFirstName() == null) {
        errors.rejectValue("firstName",
            "NotEmpty.registration.firstName",
            "First Name must not be Empty.");
      }
    if (registration.getLastName().equals("") || registration.getLastName() == null) {
        errors.rejectValue("lastName",
            "NotEmpty.registration.lastName",
            "Last Name must not be Empty.");
      }
  
    if (registration.getPhone().equals("") || registration.getPhone() == null) {
        errors.rejectValue("phoneNumber",
            "NotEmpty.registration.phoneNumber",
            "Phone must not be Empty.");
      }
    
    if (registration.getSsn().equals("")) {
        errors.rejectValue("ssn",
            "NotEmpty.registration.ssn",
            "ssn must not be Empty.");
    }
    if(registration.getSsn().length() != 10){
       	errors.rejectValue("ssn",
       	          "lengthOfUser.registration.ssn",
       	          "SSN must not more than 10 digits or less than 10 digits.");    	
     }
    if(registration.getPhone().length() > 10){
       	errors.rejectValue("phoneNumber",
       	          "lengthOfUser.registration.phoneNumber",
       	          "Phone number must not more than 10 digits.");    	
     }
      
  }
  
  
  public void dcvalidate(Object target, Errors errors) {
	  Transaction registration = (Transaction) target;
	  Double nil = 0.0;
	  try{
	      ammount = registration.getAmount();
		   
	}catch(Exception e){
		errors.rejectValue("amount",
		          "lengthOfUser.registration.amount",
		          "Amount must not be greater than ten digits.");
  }
	  if((ammount+"").equals("0.0") || (ammount+"").equals(""))
	  {
		  errors.rejectValue("amount","",
		          "Amount must not be Empty."); 
		  errors.rejectValue("amount","",
		          "Amount must be number."); 
	  }
	  
	  
  }
  
  
  
  public void editvalidate(Object target, Errors errors) {
	    PersonalInfo registration = (PersonalInfo) target;
	    
	    
	     long phone;
	    try{
	    	phone =Long.parseLong(registration.getPhone());
		}catch(Exception e){
			errors.rejectValue("phone",
			          "lengthOfUser.registration.phone",
			          "Phone number must be in digits.");
		}
	    
	    
	    if (registration.getAddress().equals("")||registration.getAddress() == null ) {
	        errors.rejectValue("address",
	            "NotEmpty.registration.address",
	            "Address must not be Empty.");
	      }
	      if (registration.getEmailId().equals("") || registration.getEmailId().equals("[A-Z]+[a-zA-Z_]+@\b([a-zA-Z]+.){2}\b?.[a-zA-Z]+") || registration.getEmailId() == null ) {
	          errors.rejectValue("emailId",
	              "NotEmpty.registration.emailId",
	              "Email Id must be Entered Properly.");
	       }
	      if(registration.getPhone().length() > 10){
	         	errors.rejectValue("phone",
	         	          "lengthOfUser.registration.phone",
	         	          "Phone number must not more than 10 digits.");    	
	       }
	      if (registration.getPhone().equals("") || registration.getPhone() == null) {
	          errors.rejectValue("phone",
	              "NotEmpty.registration.phone",
	              "Phone must not be Empty.");
	        }
	      
  }
  
  
  
  
  public void editcriticalinfovalidate(Object target, Errors errors) {
	    PersonalInfo registration = (PersonalInfo) target;
	    long ssn;
	    try{
	    	ssn = Long.parseLong(registration.getSsn());
		   
	    }catch(Exception e){
	    		errors.rejectValue("ssn",
		          "lengthOfUser.registration.ssn",
		          "SSN must be in digits.");
	    }
	    if (registration.getFirstName().equals("") || registration.getFirstName() == null) {
	        errors.rejectValue("firstName",
	            "NotEmpty.registration.firstName",
	            "First Name must not be Empty.");
	    }
	    if (registration.getMiddleName().equals("") || registration.getMiddleName() == null) {
	        errors.rejectValue("middleName",
	            "NotEmpty.registration.middleName",
	            "Middle Name must not be Empty.");
	    }
	    if (registration.getLastName().equals("") || registration.getLastName() == null) {
	        errors.rejectValue("lastName",
	            "NotEmpty.registration.lastName",
	            "Last Name must not be Empty.");
	    }
	    if (registration.getSsn().equals("")) {
	        errors.rejectValue("ssn",
	            "NotEmpty.registration.ssn",
	            "ssn must not be Empty.");
	    }
	    if(registration.getSsn().length() != 10){
	       	errors.rejectValue("ssn",
	       	          "lengthOfUser.registration.ssn",
	       	          "SSN must not more than 10 digits or less than 10 digits.");    	
	    }
	    
	    
  }
  
}