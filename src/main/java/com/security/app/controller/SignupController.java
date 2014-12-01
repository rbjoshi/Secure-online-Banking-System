package com.security.app.controller;


import java.io.File;
import java.util.Random;

import javax.validation.Valid;
import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.security.app.constant.Constants;
import com.security.app.model.Account;
import com.security.app.model.PersonalInfo;
import com.security.app.model.Role;
import com.security.app.model.User;
import com.security.app.service.AccountManager;
import com.security.app.service.PersonalInfoManager;
import com.security.app.service.RoleManager;
import com.security.app.service.UserManager;
import com.security.app.util.AuthService;
import com.security.app.util.KeyGenerator;
import com.security.app.util.OTPService;
import com.security.app.util.StaticMethods;
import com.security.app.util.SystemLoggerService;

@Controller
@SessionAttributes ({"customerid"})
public class SignupController {

	private static final Logger logger = SystemLoggerService.logger;
	@Autowired
	private UserManager userManager;
	
	private  String uname;
	private FormValidator formvalidation;

	@Autowired
	private RoleManager roleManager;

	@Autowired
	private AccountManager accountManager;
	
	@Autowired
	private PersonalInfoManager personalInfoManager;
	
	
	
	
	
	
	@RequestMapping(value = Constants.SIGN_UP, method = RequestMethod.GET)
	public String signUpUser(ModelMap map) {
		if(!StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return "redirect:/dashboard";
		map.addAttribute("user", new User());
		return Constants.SIGN_UP;
	}

	@RequestMapping(value = Constants.IN_SIGN_UP, method = RequestMethod.GET)
	public String signUpInternalUser(ModelMap map) {
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		User user = userManager.getUserByUserId(AuthService.getAuthName());
		if(!StaticMethods.isAdmin(user)) {
			return "redirect:/dashboard";
		}
		map.addAttribute("user", new User());
		return Constants.IN_SIGN_UP;
	}
	@RequestMapping(value = Constants.ADD, method = RequestMethod.POST)
	public String addUser(@Valid @ModelAttribute(value = "user") User user,
			BindingResult result, ModelMap map, HttpServletRequest request, HttpSession session) throws MessagingException {
		
		if(!StaticMethods.isAnnonymous((AuthService.getAuthName())))
			return "redirect:/dashboard";
		if(! "customer".equals(user.getRoleName()) && ! "merchant".equals(user.getRoleName())) {
			return "/index";
		}
		
		logger.info("Trying to add user "+user.getUserId());
		
	
	        System.out.print("check1");
	        user.setPersonalInfo(new PersonalInfo());
	        user.getPersonalInfo().setFirstName(user.getFirstName());
	        user.getPersonalInfo().setLastName(user.getLastName());
	        user.getPersonalInfo().setMiddleName(user.getMiddleName());
	        user.getPersonalInfo().setDob(user.getDob());
	        user.getPersonalInfo().setAddress(user.getAddress());
	        user.getPersonalInfo().setEmailId(user.getEmailId());
	        user.getPersonalInfo().setPhone(user.getPhoneNumber());
	        user.getPersonalInfo().setSsn(user.getSsn());
	        
	        PersonalInfo pinfo = user.getPersonalInfo();
	        uname = user.getUserId();

	        formvalidation = new FormValidator();
			formvalidation.validate(pinfo,result,uname);
			if(result.hasErrors())
			{
	       
	        	return "redirect:"+Constants.SIGN_UP;
	        }
	        else
	        {
	 
		      // if (reCaptchaResponse.isValid()) {
		      //      map.addAttribute("message", "Captcha Validated");
		         {       
		            if(userManager.getUserByUserId(user.getUserId()) != null) {
		            	map.addAttribute("error","User Name Exist..!!");
		            	logger.info("User "+user.getUserId()+" already exist..!!");
		            	map.addAttribute("user", new User());
		    			return Constants.SIGN_UP;
		            }
		            
		            Role role = roleManager.getRole(user.getRoleName());
		            String password = OTPService.getRandomPassword();
		            String passKey = OTPService.getPassKey();
		            user.setPassword(password);
		    		user.setRole(role);
		    		user.setAccountNonExpired(true);
		    		user.setAccountNonLocked(true);
		    		user.setCredentialsNonExpired(true);
		    		user.setNotDeleted(true);
		    		user.setEnabled(true);
		    		user.setPassKey(passKey);
		    		userManager.addUser(user);
		    		
		    		//Send password email
		    		userManager.sendAccountCreationPassWordEmail(user.getPersonalInfo(),password,passKey);
		    		logger.info("Password email has been sent to the user "+user.getUserId());
		    		//Generate key and certificate and send certificate mail.
		    		KeyGenerator.generateKeysandCert(user.getUserId(),session);
		    		userManager.sendUserCreationCertificationEmail(user.getPersonalInfo(), user.getUserId(),session);
		    		//delete certificate
		    		ServletContext context = session.getServletContext();
		            String realContextPath = context.getRealPath("/");
		    		String certpath = realContextPath+"/certificates/"+user.getUserId()+"_cert.pem";
		    		File filetoDelete = new File(certpath);
		    		filetoDelete.delete();
		    		
		    		
		    		if (user.getRoleName().equals("customer") || user.getRoleName().equals("merchant")) {
	
		    			Account account = new Account();
		    			account.setUser(user);
	
		    			map.addAttribute("account", account);
		    			session.setAttribute("customerid", user.getUserId());
		    			return "/accountform";
		    		} else {
		    			map.addAttribute("user", new User());
		    			return Constants.SIGN_UP;
		    		}
		        } 
		       /*else {
		            map.addAttribute("message", "Captcha Validated failed.");
		            map.addAttribute("user", new User());
		    		return "redirect:"+Constants.SIGN_UP;
	
		        }
	        	*/
	        }
			
		
	}
	
	@RequestMapping(value = Constants.ADD_INT, method = RequestMethod.POST)
	public String addInternalUser(@Valid @ModelAttribute(value = "user") User user,
			BindingResult result, ModelMap map, HttpServletRequest request, HttpSession session) throws MessagingException {
		if(StaticMethods.isAnnonymous((AuthService.getAuthName())))
			return StaticMethods.INDEX;
		
		User admin = userManager.getUserByUserId(AuthService.getAuthName());
		if(!StaticMethods.isAdmin(admin)) {
			return "redirect: /dashboard";
		}
		if(! "admin".equals(user.getRoleName()) && ! "emp".equals(user.getRoleName())) {
			return "redirect: /dashboard";
		}
		
		logger.info("Trying to add user "+user.getUserId());
		

	        
	        System.out.print("check1");
	        user.setPersonalInfo(new PersonalInfo());
	        user.getPersonalInfo().setFirstName(user.getFirstName());
	        user.getPersonalInfo().setLastName(user.getLastName());
	        user.getPersonalInfo().setMiddleName(user.getMiddleName());
	        user.getPersonalInfo().setDob(user.getDob());
	        user.getPersonalInfo().setAddress(user.getAddress());
	        user.getPersonalInfo().setEmailId(user.getEmailId());
	        user.getPersonalInfo().setPhone(user.getPhoneNumber());
	        user.getPersonalInfo().setSsn(user.getSsn());
	        
	        PersonalInfo pinfo = user.getPersonalInfo();
	        uname = user.getUserId();
	        System.out.print(uname);
	        formvalidation = new FormValidator();
			formvalidation.validate(pinfo,result,uname);
			if(result.hasErrors())
			{
	       
	        	return Constants.SIGN_UP;
	        }
	        else
	        {
	 
		      
		        
		            
		            if(userManager.getUserByUserId(user.getUserId()) != null) {
		            	map.addAttribute("error","User Name Exist..!!");
		            	logger.info("User "+user.getUserId()+" already exist..!!");
		            	map.addAttribute("user", new User());
		    			return Constants.SIGN_UP;
		            }
		            
		            Role role = roleManager.getRole(user.getRoleName());
		            String password = OTPService.getRandomPassword();
		            String passKey = OTPService.getPassKey();
		            user.setPassword(password);
		    		user.setRole(role);
		    		user.setAccountNonExpired(true);
		    		user.setAccountNonLocked(true);
		    		user.setCredentialsNonExpired(true);
		    		user.setNotDeleted(true);
		    		user.setEnabled(true);
		    		user.setPassKey(passKey);
		    		userManager.addUser(user);
		    		
		    		//Send password email
		    		userManager.sendAccountCreationPassWordEmail(user.getPersonalInfo(),password,passKey);
		    		logger.info("Password email has been sent to the user "+user.getUserId());
		    		//Generate key and certificate and send certificate mail.
		    		//KeyGenerator.generateKeysandCert(user.getUserId(),session);
		    		//userManager.sendUserCreationCertificationEmail(user.getPersonalInfo(), user.getUserId(),session);
		    		//delete certificate
		    		
		    		
		    		if (user.getRoleName().equals("customer") || user.getRoleName().equals("merchant")) {
	
		    			Account account = new Account();
		    			account.setUser(user);
	
		    			map.addAttribute("account", account);
		    			session.setAttribute("customerid", user.getUserId());
		    			
		    			
		    			return "/accountform";
		    		} else {
		    			map.addAttribute("user", new User());
		    			return Constants.SIGN_UP;
		    		}
		        
	        	
	        }
			
		
	}
	
	/*public boolean check(@Valid @ModelAttribute(value = "user") PersonalInfo user,
			BindingResult result, ModelMap map, HttpServletRequest request){
		formvalidation = new FormValidator();
		formvalidation.validate(user,result,uname);
		if(result.hasErrors())
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}*/
	
}
