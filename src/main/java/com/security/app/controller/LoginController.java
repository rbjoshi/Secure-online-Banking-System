package com.security.app.controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.security.app.constant.Constants;
import com.security.app.model.Account;
import com.security.app.model.OTP;
import com.security.app.model.PersonalInfo;
import com.security.app.model.Transaction;
import com.security.app.model.User;
import com.security.app.model.UserAttempts;
import com.security.app.service.AccountManager;
import com.security.app.service.OTPManager;
import com.security.app.service.PersonalInfoManager;
import com.security.app.service.RoleManager;
import com.security.app.service.TransactionSbsManager;
import com.security.app.service.UserAttemptsManager;
import com.security.app.service.UserManager;
import com.security.app.util.AuthService;
import com.security.app.util.OTPService;
import com.security.app.util.StaticMethods;
import com.security.app.util.SystemLoggerService;

@Controller
@SessionAttributes({"auth_user","forget_pass_user"})
public class LoginController {
	private static final Logger logger = SystemLoggerService.logger;
	
	@Autowired
	private AccountManager accountManager;
	
	@Autowired
	private UserManager userManager;
	
	private FormValidator validate;
	
	@Autowired
	private UserAttemptsManager userAttemptsManager;

	@Autowired
	private RoleManager roleManager;
	
	@Autowired
	private TransactionSbsManager transactionManager;

	@Autowired
	private PersonalInfoManager personalInfoManager;
	
	@Autowired
	private OTPManager otpManager;
	
	/*@RequestMapping(value = Constants.LOGIN, method = RequestMethod.GET)
	public String loginUser(ModelMap map, HttpSession session) {
		if (session.getAttribute("auth_user") != null) {
			return Constants.DASHBOARD;
		}
		map.addAttribute("user", new User());
		return Constants.LOGIN;

	}*/
	
	
	@RequestMapping(value=Constants.LOGIN, method=RequestMethod.GET)
	public ModelAndView loginUser(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout){
		ModelAndView model = getModelAndView("/index");
		 if(!model.getViewName().equalsIgnoreCase("/index")) {
			 return model;
		 } 
		 model = new ModelAndView();
		 	if (error != null) {
				model.addObject("error", "Invalid username and password!");
			  }
		 
		 	else if (logout != null) {
				model.addObject("msg", "You've been logged out successfully.");
			  }
			  
		 	
			  model.setViewName("/index");
			return model;
	}
	
	@RequestMapping(value=Constants.USERNAME_AUTH, method=RequestMethod.POST)
	public ModelAndView authenticateUserName(@RequestParam(value = "username", required = true) String username, HttpSession session){
		logger.info(username+" is trying to login.");
		 ModelAndView model = getModelAndView("/login");
		 if(!model.getViewName().equalsIgnoreCase("/login")) {
			 return model;
		 }
		
		 model = new ModelAndView();
		 User user=userManager.getUserByUserId(username) ; 
		
		 	if (user != null) {
		 		 UserAttempts userAttempts = userAttemptsManager.getUserAttempts(user.getUserId());
		 		if(userAttempts != null){
			 		if(!user.getAccountNonLocked() && !user.getEnabled()){
						//System.out.println("difference is " + (StaticMethods.getDate().getTime() - userAttempts.getLastModified().getTime()));
						
						if((StaticMethods.getDate().getTime() - userAttempts.getLastModified().getTime()) > (600*1000)){
							
							user.setAccountNonLocked(true);
							user.setEnabled(true);
							//userManager.addUser(user);
							
							userAttempts.setUser(user);
				 			userAttempts.setAttempts(0);
				 			userAttempts.setLastModified(StaticMethods.getDate());
				 			userAttemptsManager.updateAttempts(userAttempts);
				 			
				 		
						}
						else {
							model.addObject("error", "Your account is temporarily locked for 10 minutes since "+userAttempts.getLastModified().toString());
							model.setViewName("/index"); 		
							return model;
						}
			 		}
		 		}
			 	else{
			 	
			 			userAttempts = new UserAttempts();
			 			userAttempts.setUser(user);
			 			userAttempts.setAttempts(0);
			 			userAttemptsManager.updateAttempts(userAttempts);
			 		}
				
				model.addObject("username", user.getUserId());
				model.addObject("passKey", user.getPassKey());
				model.setViewName("/login");
				 
				session.setAttribute("forget_pass_user", user.getUserId());
			  }
		 	else {
		 		model.addObject("error", "Invalid username!");
				 model.setViewName("/index");
		 	}
			 
			return model;
	}
	/*@RequestMapping(value = Constants.AUTH, method = RequestMethod.POST)
	public String addUser(@ModelAttribute(value = "user") User user,
			BindingResult result, HttpSession session) {
		user = userManager.getUser(user);
		ModelMap map = new ModelMap();
		if (user != null) {
			// map.addAttribute("auth_user", user);
			session.setAttribute("auth_user", user);

			return Constants.DASHBOARD;
		}
		return Constants.LOGIN;
	}*/
	
	@RequestMapping(value = Constants.FORGET_PASS, method = RequestMethod.GET)
	public ModelAndView forgetPasswordPage(HttpSession session) {
	  ModelAndView model = new ModelAndView();
	  
	  String userId = (String) session.getAttribute("forget_pass_user");
	  if(userId == null) {
		  model.addObject("error", "Invalid username!");
		  model.setViewName("/index");
		  return model;
	  }
	  
	  User user = userManager.getUserByUserId(userId);
	  if(user == null) {
		  model.addObject("error", "Invalid username!");
		  model.setViewName("/index");
		  return model;
	  }
	  //Get user from client
	  String otpString = OTPService.createOTP()+"";
	  logger.info("OTP generated "+otpString);
	  
	  OTP otp = new OTP(otpString,user);
	  
	  //Send an OTP email.
	  userManager.sendResetPassWordEmail(user.getPersonalInfo(), otpString);

	  //Persist to DB
	  otpManager.addOTP(otp);
	  
	  model.addObject("OTP", "");
	  model.setViewName("/forgetpassword");
	  return model;
 
	}
	
	@RequestMapping(value = Constants.ADMIN_MAPPING, method = RequestMethod.GET)
	public String adminPage(ModelMap map) {
 
	  //ModelAndView model = new ModelAndView();
	  //model.setViewName("admin");
		List<Transaction> transactions = transactionManager.getTransactions();
		map.addAttribute("transactions",transactions);
	  return "admin";
 
	}
	@RequestMapping(value="/editPersonalInfo", method=RequestMethod.GET)
	public String editPersonalInfo(ModelMap map){
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
		User user = userManager.getUserByUserId(AuthService.getAuthName());
		if(StaticMethods.isInternalEmployee(user)) {
			map.addAttribute("forget_pass_user","");
			map.addAttribute("msg","You are not allowed to access.");
			return "redirect:/dashboard";
		}
		map.addAttribute("personalInfo", user.getPersonalInfo());
		return "/editpersonalinfo";
	}
	
	@RequestMapping(value="/editCriticalInfo", method=RequestMethod.GET)
	public String editCriticalInfo(ModelMap map){
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
		User user = userManager.getUserByUserId(AuthService.getAuthName());
		map.addAttribute("personalInfo", user.getPersonalInfo());
		
		
		
		return "/criticalEditInfo";
	}
	
	@RequestMapping(value="/updatePersonalInfo", method=RequestMethod.POST)
	public String updatePersonalInfo(@ModelAttribute(value="personalInfo") PersonalInfo personalInfo, ModelMap map, BindingResult result){
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
		validate = new FormValidator();
		validate.editvalidate(personalInfo, result);
		if(result.hasErrors())
		{
			return "/editpersonalinfo";
		}
		
		else
		{
			
			User user = userManager.getUserByUserId(AuthService.getAuthName());
			user.getPersonalInfo().setEmailId(personalInfo.getEmailId());
			user.getPersonalInfo().setAddress(personalInfo.getAddress());
			user.getPersonalInfo().setPhone(personalInfo.getPhone());
			personalInfoManager.updatePersonalInfo(user.getPersonalInfo());
	
			return "redirect:/dashboard";
		}
	}
	@RequestMapping(value = Constants.DASHBOARD_MAPPING, method = RequestMethod.GET)
	public ModelAndView dashboard(ModelMap map, @RequestParam(value="msg",required=false)String msg) {
 
		String role=null;
		String view=null;
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		 if(authorities.iterator().hasNext())
		{
			  role = authorities.iterator().next().getAuthority();	
			  
		}
		 if(role.equals("admin"))
		 {
				view = "/admin";
		 }
		 else if(role.equals("emp"))
		 {
				view = "/employeedashboard";
		 }
		 else if(role.equals("customer"))
		 {
				view = "/customerdashboard";
		 }
		 else if(role.equals("merchant"))
		 {
				view = "/merchantdashboard";
		 }
		
	  ModelAndView model = new ModelAndView();
	  if(msg!=null)
		  	  model.addObject("msg", msg);
	  else
		  	model.addObject("msg", "");
	  model.setViewName(view);
	  return model;
 
	}
	@RequestMapping(value = Constants.INDEX, method = RequestMethod.GET)
	public ModelAndView index() {
		return getModelAndView("/index");
	}
	
	private ModelAndView getModelAndView(String defaultView) {
		ModelAndView model = new ModelAndView();
		String role = null;
		String view = null;
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();
		if (authorities.iterator().hasNext()) {
			role = authorities.iterator().next().getAuthority();
		}
		if (role.equals("admin")) {
			view = "/admin";
		} else if (role.equals("emp")) {
			view = "/employeedashboard";
		} else if (role.equals("customer")) {
			view = "/customerdashboard";
		} else if (role.equals("merchant")) {
			view = "/merchantdashboard";
		} else {
			view = defaultView;
		}
		model.setViewName(view);
		return model;
	}

	@RequestMapping(value = Constants.NEW_PASS, method = RequestMethod.POST)
	public ModelAndView newPassword(@RequestParam(value = "OTP", required = true) String otpString,
			HttpSession session) {
		
		ModelAndView model = new ModelAndView();
		String userId = (String) session.getAttribute("forget_pass_user");
		if (userId == null) {
			model.addObject("error", "Invalid Request!");
			model.setViewName("/index");
			return model;
		}

		User user = userManager.getUserByUserId(userId);
		if (user == null) {
			model.addObject("error", "Invalid Request!");
			model.setViewName("/index");
			return model;
		}
		OTP otp = otpManager.getOTPByUser(userId);
		if(otp == null || !otp.getOtp().equals(otpString)) {
			model.addObject("error", "Incorrect OTP. You need to generate another one.");
			model.setViewName("/index");
			return model;
		}
		model.setViewName("/newpassword");
		return model;

	}
	
	
	@RequestMapping(value = "/user_transactions", method = RequestMethod.GET)
	public String user_transactions(ModelMap map) {
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
		
	  User user = userManager.getUserByUserId(AuthService.getAuthName());
	  if(StaticMethods.isInternalEmployee(user)) {
		  	map.addAttribute("forget_pass_user","");
			return "redirect:/dashboard";
	  }
	  
	  Account account = accountManager.getAccountByUserId(AuthService.getAuthName());
	  List<Transaction> transactions = transactionManager.getUserTransactions(account);
	  List<Transaction> transactionsoutward = transactionManager.getUserTransferOutTransactions(account);
	  List<Transaction> paymentTransactions = transactionManager.getUserPaymentTransactions(account);
	  map.addAttribute("transactions", transactions);
	  map.addAttribute("transferTransactions", transactionsoutward);
	  map.addAttribute("paymenttransactions", paymentTransactions);
	  
	 
	  return "/user_transactions";

	}
	
	@RequestMapping(value = "/change-forget-password", method = RequestMethod.POST)
	public ModelAndView changeForgetPassword(@RequestParam(value = "newpassword", required = true) String password, @RequestParam(value = "newcpassword", required = true) String cpassword, HttpSession session) {
		ModelAndView model = new ModelAndView();
		String userId = (String) session.getAttribute("forget_pass_user");
		session.setAttribute("forget_pass_user",null);
		if(userId == null || password == null || cpassword == null || !password.equals(cpassword)) {
				model.addObject("error", "Invalid Request");
				model.setViewName("/index");
		} else {
			User user = userManager.getUserByUserId(userId);
			if(user == null) {
				model.addObject("error", "Invalid Request");
				model.setViewName("/index");
			} else {
				user.setPassword(password);
				userManager.addUser(user);
				model.addObject("msg", "Successfully changed the password.!!");
				model.setViewName("/index");
			}
		}
		return model;
	}
	
	
}
