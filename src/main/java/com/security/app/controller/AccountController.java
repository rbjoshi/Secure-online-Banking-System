package com.security.app.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.security.app.constant.Constants;
import com.security.app.model.Account;
import com.security.app.model.User;
import com.security.app.service.AccountManager;
import com.security.app.service.UserManager;
import com.security.app.util.AuthService;
import com.security.app.util.OTPService;
import com.security.app.util.StaticMethods;
import com.security.app.util.SystemLoggerService;

@Controller
@SessionAttributes ({"customerid"})
public class AccountController {

	private static final Logger logger = SystemLoggerService.logger;
	@Autowired
	private AccountManager accountManager;
	
	
	@Autowired
	private UserManager userManager;
	
	
	@RequestMapping(value=Constants.ACCOUNT_ADD, method=RequestMethod.POST)
	public String addAccount(@ModelAttribute(value="account") Account account, BindingResult result, HttpSession session,ModelMap map,SessionStatus status,RedirectAttributes ra){
		if(!StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return "redirect:dashboard";
		logger.info("Adding a acocunt for user "+account.getUserId());
		if(!account.getAccountType().equals("Checking") && !account.getAccountType().equals("Saving")) {
			map.put("msg", "Invalid account type..!!");
			return "/signup";
		}
		
		try{
			double amount = account.getBalance();
			
		}
		catch(Exception e){
			map.addAttribute("msg", "incorrect values");
			return "redirect:/"+Constants.SIGN_UP;
		}
		String accountId = null;
		Account ac = null;
		do {
			accountId = OTPService.generateAccoundId();
			ac = accountManager.getAccountByAccountId(accountId);
		}while(ac != null);
		
		User user = userManager.getUserByUserId((String)session.getAttribute("customerid"));
		account.setAccountId(accountId);
		account.setUser(user);
		
		accountManager.addAccount(account);
		
		session.removeAttribute("customerid");
		
		map.addAttribute("user", new User());
		status.setComplete();
		session.removeAttribute("customerid");
		return "redirect:"+Constants.INDEX;
	}
	
	
	@RequestMapping(value="/viewAccountInfo", method=RequestMethod.GET)
	public String editPersonalInfo(ModelMap map){
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
		User user = userManager.getUserByUserId(AuthService.getAuthName());
		if(StaticMethods.isInternalEmployee(user))
			return "redirect:/dashboard";
		
		Account account = accountManager.getAccountByUserId(AuthService.getAuthName());
		map.addAttribute("account", account);
		return "/useraccountinfo";
	}
}
