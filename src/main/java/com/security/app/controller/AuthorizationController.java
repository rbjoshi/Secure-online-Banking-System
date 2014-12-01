package com.security.app.controller;

import java.text.Normalizer.Form;
import java.util.List;

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

import com.security.app.model.Account;
import com.security.app.model.Authorization;
import com.security.app.model.CrudPermission;
import com.security.app.model.PersonalInfo;
import com.security.app.model.PlaceHolder;
import com.security.app.model.Transaction;
import com.security.app.model.User;
import com.security.app.service.AccountManager;
import com.security.app.service.AuthorizationManager;
import com.security.app.service.CrudPermissionManager;
import com.security.app.service.TransactionSbsManager;
import com.security.app.service.UserManager;
import com.security.app.util.AuthService;
import com.security.app.util.StaticMethods;
import com.security.app.util.SystemLoggerService;


@Controller
@SessionAttributes({"authorization"})

public class AuthorizationController {
	private static final Logger logger = SystemLoggerService.logger;
	
	@Autowired
	private AccountManager accountManager;
	
	@Autowired
	private AuthorizationManager authorizationManager;
	
	@Autowired
	private CrudPermissionManager crudPermissionManager;
	
	private FormValidator validate;
	
	@Autowired
	private TransactionSbsManager transactionSbsManager;
	
	
	@Autowired
	private UserManager userManager;
	
	Authorization authorization;
	
	@RequestMapping(value="trans_auth_form", method=RequestMethod.GET)
	public String generateTransactionForm(ModelMap map){
			if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
				return StaticMethods.INDEX;
			
			User user = userManager.getUserByUserId(AuthService.getAuthName());
			if(user!=null){
				if(StaticMethods.isInternalEmployee(user))
					return "redirect:/dashboard";
			}
			logger.info("Adding transaction authorization");
			List<Transaction> transactions = transactionSbsManager.getUserDCTransactions(accountManager.getAccountByUserId(AuthService.getAuthName()));
			
			List<User> reg_employees = userManager.getRegEmployees();
	
			
			
			map.addAttribute("transactions", transactions);
			map.addAttribute("employees", reg_employees);
			map.addAttribute("authorization", new Authorization());
			
			
			return "/transactionauthorization";
	}
	@RequestMapping(value = "trans_auth_add", method = RequestMethod.POST)
	public String addTransactionAdmin(@ModelAttribute(value = "authorization") Authorization authorization,
			BindingResult result, ModelMap map) {
			if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
				return StaticMethods.INDEX;
		
			User user = userManager.getUserByUserId(AuthService.getAuthName());
			if(user!=null){
				if(StaticMethods.isInternalEmployee(user))
					return "redirect:/dashboard";
			}
			if(!user.getNotDeleted())
				return "redirect:/dashboard";
			
			logger.info("Adding transaction authorization for transaction "+authorization.getTransactionId());
			CrudPermission crudPermission = crudPermissionManager.getCrudPermission(authorization.getPermissionId());
			logger.info("Added permissions have id "+crudPermission.getCrudPermissionId());
			authorization.setTranCRUDPermission(crudPermission);
			User employee = userManager.getUserByUserId(authorization.getEmpId());
			if(employee == null)
				return "redirect:/dashboard";
			authorization.setEmployee(employee);
			logger.info("Authorization for employee  "+authorization.getEmpId());
			if(transactionSbsManager.isValidTransaction(AuthService.getAuthName(), authorization.getTransactionId()))
					authorization.setTransaction(transactionSbsManager.getTransactionById(authorization.getTransactionId()));
			else return "redirect:/dashboard";
			if(!authorizationManager.checkIfAuthorizationExists(authorization.getTransaction().getTransactionId(), authorization.getTranCRUDPermission().getCrudPermissionId()))
				authorizationManager.addAuthorization(authorization);	
			else return "redirect:/dashboard";
		
		return "redirect:/dashboard";
	}
	
	@RequestMapping(value="acc_auth_form", method=RequestMethod.GET)
	public String generateAdminAccessForm(ModelMap map){
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
			User user = userManager.getUserByUserId(AuthService.getAuthName());
			if(user!=null){
				if(StaticMethods.isInternalEmployee(user))
					return "redirect:/dashboard";
			}
			
			if(!user.getNotDeleted())
				return "redirect:/dashboard";
		
			List<User> administrators = userManager.getAdministrators();
	
		
			map.addAttribute("administrators", administrators);
			map.addAttribute("authorization", new Authorization());
			
			
			return "/accountauthorization";
	}
	@RequestMapping(value = "acc_auth_add", method = RequestMethod.POST)
	public String addAccountAdmin(@ModelAttribute(value = "authorization") Authorization authorization,
			BindingResult result, ModelMap map) {
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
			User user = userManager.getUserByUserId(AuthService.getAuthName());
			if(user!=null){
				if(StaticMethods.isInternalEmployee(user))
					return "redirect:/dashboard";
			}
			
			CrudPermission crudPermission = crudPermissionManager.getCrudPermission(authorization.getPermissionId());
			authorization.setTranCRUDPermission(crudPermission);
			
			logger.info("Account authorization is given to "+authorization.getEmpId());
			authorization.setEmployee(userManager.getUserByUserId(authorization.getEmpId()));
			authorization.setAccount(accountManager.getAccountByUserId(AuthService.getAuthName()));
			logger.info("Adding Account authorization for account "+authorization.getAccount().getAccountId());
			
			if(!authorizationManager.checkIfAccountAuthorizationExists(authorization.getAccount().getAccountId(), authorization.getTranCRUDPermission().getCrudPermissionId()))
				authorizationManager.addAuthorization(authorization);
			else {
				return "redirect:/dashboard";
			}
		
		return "redirect:/dashboard";
	}
	
	@RequestMapping(value="acc_auth_pii_form", method=RequestMethod.GET)
	public String generateAdminAccessPIIForm(ModelMap map){
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
			User user = userManager.getUserByUserId(AuthService.getAuthName());
			if(user!=null){
				if(StaticMethods.isInternalEmployee(user))
					return "redirect:/dashboard";
			}
			
			if(!user.getNotDeleted())
				return "redirect:/dashboard";
		
			List<User> administrators = userManager.getAdministrators();
	
		
			Account account = accountManager.getAccountByUserId(user.getUserId());
			if(authorizationManager.checkIfAccountAuthorizationExists(account.getAccountId(), 7))
			{
				//pending
				map.addAttribute("authorization", "");
			}
			else{	
					map.addAttribute("administrators", administrators);
					map.addAttribute("authorization", new Authorization());
			}
			
			
			return "/accountpiiauthorization";
	}
	
	
	@RequestMapping(value = "acc_auth_pii_add", method = RequestMethod.POST)
	public String addAccountPIIAdmin(@ModelAttribute(value = "authorization") Authorization authorization,
			BindingResult result, ModelMap map) {
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
			User user = userManager.getUserByUserId(AuthService.getAuthName());
			if(user!=null){
				if(StaticMethods.isInternalEmployee(user))
					return "redirect:/dashboard";
			}
			
			authorization.setComments(StaticMethods.html2text(authorization.getComments()));
			CrudPermission crudPermission = crudPermissionManager.getCrudPermission(7);
			authorization.setTranCRUDPermission(crudPermission);
			
			logger.info("Account authorization is given to "+authorization.getEmpId());
			authorization.setEmployee(userManager.getUserByUserId(authorization.getEmpId()));
			authorization.setAccount(accountManager.getAccountByUserId(AuthService.getAuthName()));
			logger.info("Adding Account authorization for account "+authorization.getAccount().getAccountId());
			
			if(!authorizationManager.checkIfAccountAuthorizationExists(authorization.getAccount().getAccountId(), authorization.getTranCRUDPermission().getCrudPermissionId()))
				authorizationManager.addAuthorization(authorization);
			else {
				
			}
		
		return "redirect:/dashboard";
	}
	
	
	
	@RequestMapping(value="transaction_edit_form", method=RequestMethod.GET)
	public String editTransactionOption(ModelMap map){
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
			User user = userManager.getUserByUserId(AuthService.getAuthName());
			if(user!=null){
			if(!StaticMethods.isRegularEmployee(user))
				return "redirect:/dashboard";
			}
			List<Authorization> authorizations = authorizationManager.getAuthorizationsOfEmployee(AuthService.getAuthName(), 3);
			map.addAttribute("authorizations", authorizations);
			map.addAttribute("placeholder", new PlaceHolder());
			
			
			return "/employee_select_transaction";
	}
	
	@RequestMapping(value = "transaction_maintainence", method = RequestMethod.POST)
	public String employeeSelectAccount(@ModelAttribute(value = "placeholder") PlaceHolder placeHolder,
			BindingResult result, ModelMap map, HttpSession session) {
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
		User user = userManager.getUserByUserId(AuthService.getAuthName());
		
		if(user!=null){
			if(!StaticMethods.isRegularEmployee(user))
				return "redirect:/dashboard";
		}
		//Account account = accountManager.getAccountByAccountId(placeHolder.getPlaceholderstring());
		if(placeHolder == null || placeHolder.getPlaceholderstring() == null)
		{
			return "redirect:/dashboard";
		}
		Authorization authorization = authorizationManager.getAuthorizationsById(placeHolder.getPlaceholderstring());
		if(authorization == null){
			return "redirect:/dashboard";
		}
		boolean isAuthorized = authorizationManager.isTransactionActuallyAuthorized(AuthService.getAuthName(), authorization.getTransaction().getTransactionId(), 3);
		if(!isAuthorized){
			return "redirect:/dashboard";
		}
		if(!authorization.getEmployee().getUserId().equals(AuthService.getAuthName())){
			map.addAttribute("msg", "you do not have sufficient privileges");
			return "redirect:/dashboard";	
		}
		
		map.addAttribute("transaction", authorization.getTransaction());
		//session.setAttribute("authorization", authorization);
		this.authorization = authorization;
		
		return "/editTransactionDetails";
	}
	@RequestMapping(value = "transaction_maintanence_update", method = RequestMethod.POST)
	public String employeeTransactionUpdate(@ModelAttribute(value = "transaction") Transaction transaction, //@ModelAttribute(value="authorization") Authorization authorization,
			BindingResult result, ModelMap map, HttpSession session, SessionStatus status) {
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
	
		
		User user = userManager.getUserByUserId(AuthService.getAuthName());
		if(user != null){
			if(!StaticMethods.isRegularEmployee(user))
				return "redirect:/dashboard";
		}
		
	
		authorization.getTransaction().setStatus(transaction.getStatus());
		
		if(transaction.getStatus().equals("C")){
			
		
		if(authorization.getTransaction().getTransactionType().getTransactionTypeName().equals("debit")){
			authorization.getTransaction().getAccount().setBalance(authorization.getTransaction().getAccount().getBalance()-authorization.getTransaction().getAmount());
			
		}
		else if(authorization.getTransaction().getTransactionType().getTransactionTypeName().equals("credit")){
			authorization.getTransaction().getAccount().setBalance(authorization.getTransaction().getAccount().getBalance()+authorization.getTransaction().getAmount());
			
		}
		
		authorization.setStatus(true);
		authorizationManager.addAuthorization(authorization);
		}
		
		//status.setComplete();
		
		authorization = null;
		
		return "redirect:/dashboard";
	}
	
	
	
	
	@RequestMapping(value="transaction_read_form", method=RequestMethod.GET)
	public String readTransactionOption(ModelMap map){
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
			User user = userManager.getUserByUserId(AuthService.getAuthName());
			if(user != null){
			if(!StaticMethods.isRegularEmployee(user))
				return "redirect:/dashboard";
			}
			List<Authorization> authorizations = authorizationManager.getAuthorizationsOfEmployee(AuthService.getAuthName(), 4);
			map.addAttribute("authorizations", authorizations);
			map.addAttribute("placeholder", new PlaceHolder());
			
			
			return "/employee_select_transaction_for_read";
	}
	
	@RequestMapping(value = "transaction_display", method = RequestMethod.POST)
	public String readTransaction(@ModelAttribute(value = "placeholder") PlaceHolder placeHolder,
			BindingResult result, ModelMap map, HttpSession session) {
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
		User user = userManager.getUserByUserId(AuthService.getAuthName());
		if(user!=null){
			if(!StaticMethods.isRegularEmployee(user))
				return "redirect:/dashboard";
		}
		//Account account = accountManager.getAccountByAccountId(placeHolder.getPlaceholderstring());
		if(placeHolder == null || placeHolder.getPlaceholderstring() == null)
		{
			return "redirect:/dashboard";
		}
		Authorization authorization = authorizationManager.getAuthorizationsById(placeHolder.getPlaceholderstring());
		if(authorization == null){
			return "redirect:/dashboard";
		}
		boolean isAuthorized = authorizationManager.isTransactionActuallyAuthorized(AuthService.getAuthName(), authorization.getTransaction().getTransactionId(), 4);
		if(!isAuthorized){
			return "redirect:/dashboard";
		}
		if(!authorization.getEmployee().getUserId().equals(AuthService.getAuthName())){
			map.addAttribute("msg", "you do not have sufficient privileges");
			return "redirect:/dashboard";	
		}
		
		map.addAttribute("transaction", authorization.getTransaction());
		authorization.setStatus(true);
		authorizationManager.addAuthorization(authorization);
		return "/showTransactionDetails";
	}
	@RequestMapping(value="transaction_delete_form", method=RequestMethod.GET)
	public String deleteTransactionOption(ModelMap map){
			if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
				return StaticMethods.INDEX;
			User user = userManager.getUserByUserId(AuthService.getAuthName());
			if(user != null){
				if(!StaticMethods.isRegularEmployee(user))
					return "redirect:/dashboard";
			}
			List<Authorization> authorizations = authorizationManager.getAuthorizationsOfEmployee(AuthService.getAuthName(), 6);
			map.addAttribute("authorizations", authorizations);
			map.addAttribute("placeholder", new PlaceHolder());
			
			
			return "/employee_select_transaction_for_delete";
	}
	
	@RequestMapping(value = "transaction_delete", method = RequestMethod.POST)
	public String deleteTransaction(@ModelAttribute(value = "placeholder") PlaceHolder placeHolder,
			BindingResult result, ModelMap map, HttpSession session) {
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
		User user = userManager.getUserByUserId(AuthService.getAuthName());
		if(user!=null){
			if(!StaticMethods.isRegularEmployee(user))
				return "redirect:/dashboard";
		}
		//Account account = accountManager.getAccountByAccountId(placeHolder.getPlaceholderstring());
		if(placeHolder == null || placeHolder.getPlaceholderstring() == null)
		{
			return "redirect:/dashboard";
		}
		Authorization authorization = authorizationManager.getAuthorizationsById(placeHolder.getPlaceholderstring());
		if(authorization==null)
			return "redirect:/dashboard";
		boolean isAuthorized = authorizationManager.isTransactionActuallyAuthorized(AuthService.getAuthName(), authorization.getTransaction().getTransactionId(), 6);
		if(!isAuthorized){
			return "redirect:/dashboard";
		}
		if(!authorization.getEmployee().getUserId().equals(AuthService.getAuthName())){
			map.addAttribute("msg", "you do not have sufficient privileges");
			return "redirect:/dashboard";	
		}
		
		Transaction transaction = authorization.getTransaction();
		
		
		authorizationManager.deleteAuthorization(authorization);
		transactionSbsManager.deleteTransaction(transaction);
		return "/dashboard";
	}
	
	
	@RequestMapping(value="acc_edit_form", method=RequestMethod.GET)
	public String editAccountOption(ModelMap map){
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
		User user = userManager.getUserByUserId(AuthService.getAuthName());
		if(user!=null){
			if(!StaticMethods.isAdmin(user))
				return "redirect:/dashboard";
		}
		
		
			List<Authorization> authorizations = authorizationManager.getAuthorizationsOfAdministrator(AuthService.getAuthName(), 3);
			
			map.addAttribute("authorizations", authorizations);
			map.addAttribute("placeholder", new PlaceHolder());
			
			
			return "/admin_select_account";
	}
	
	
	@RequestMapping(value = "account_maintanence", method = RequestMethod.POST)
	public String adminSelectAccount(@ModelAttribute(value = "placeholder") PlaceHolder placeHolder,
			BindingResult result, ModelMap map, HttpSession session) {
		
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
		User user = userManager.getUserByUserId(AuthService.getAuthName());
		if(user!=null){
			if(!StaticMethods.isAdmin(user))
				return "redirect:/dashboard";
		}
		//Account account = accountManager.getAccountByAccountId(placeHolder.getPlaceholderstring());
		if(placeHolder == null || placeHolder.getPlaceholderstring() == null)
		{
			return "redirect:/dashboard";
		}
		Authorization authorization = authorizationManager.getAuthorizationsById(placeHolder.getPlaceholderstring());
		if(authorization==null){
			map.addAttribute("msg", "you do not have sufficient privileges");
			return "redirect:/dashboard";		
		
		}
		if(!authorization.getEmployee().getUserId().equals(AuthService.getAuthName())){
			map.addAttribute("msg", "you do not have sufficient privileges");
			return "redirect:/dashboard";	
		}
		if(!authorizationManager.isAccountActuallyAuthorized(authorization.getEmployee().getUserId(), authorization.getAccount().getAccountId(), authorization.getTranCRUDPermission().getCrudPermissionId())){
			map.addAttribute("msg", "account not authorized");
			return "dashboard";	
		}
		map.addAttribute("account", authorization.getAccount());
		//session.setAttribute("authorization", authorization);
		this.authorization = authorization;
		
		return "/editAccountInfo";
	}
	@RequestMapping(value = "account_maintanence_update", method = RequestMethod.POST)
	public String adminUpdate(@ModelAttribute(value = "account") Account account, 
			BindingResult result, ModelMap map, HttpSession session, SessionStatus status) {
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
		User user = userManager.getUserByUserId(AuthService.getAuthName());
		if(user != null){
			if(!StaticMethods.isAdmin(user))
				return "redirect:/dashboard";
		}
		//Authorization authorization = (Authorization) session.getAttribute("authorization");
		
		if(!account.getAccountType().equals("Checking") && !account.getAccountType().equals("Saving")){
			map.addAttribute("msg", "Incorrect values");
			return "redirect:/dashboard";
		}
		try{
			double amount = account.getBalance();
			
		}
		catch(Exception e){
			map.addAttribute("msg", "incorrect values");
			return "redirect:/dashboard";
		}
		
		authorization.getAccount().setAccountType(account.getAccountType());
				
		authorization.getAccount().setBalance(account.getBalance());
		
		authorization.getAccount().getUser().getPersonalInfo().setAddress(account.getAddress());
		authorization.getAccount().getUser().getPersonalInfo().setEmailId(account.getEmailId());
	//	authorization.getAccount().getUser().getPersonalInfo().setFirstName(account.getFirstName());
	//	authorization.getAccount().getUser().getPersonalInfo().setLastName(account.getLastName());
		authorization.getAccount().getUser().getPersonalInfo().setPhone(account.getPhone());
		
		PersonalInfo pinfo = authorization.getAccount().getUser().getPersonalInfo();
		validate = new FormValidator();
		validate.editvalidate(pinfo, result);
		
		if(result.hasErrors())
		{
			return "/editAccountInfo";
		}
		
		
		//System.out.println(	authorization.getAccount().getAccountType() + " " + authorization.getAccount().getUser().getPersonalInfo().getPhone());
		
		authorization.setStatus(true);
		authorizationManager.addAuthorization(authorization);
		//session.removeAttribute("authorization");
	//	status.setComplete();
		
		authorization = null;
		
		
		map.addAttribute("msg", "account successfully updated");
		return "redirect:/dashboard";
	}
	
	@RequestMapping(value="acc_display_form", method=RequestMethod.GET)
	public String displayAccountOption(ModelMap map){
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
			User user = userManager.getUserByUserId(AuthService.getAuthName());
			if(user!=null){
				if(!StaticMethods.isAdmin(user))
					return "redirect:/dashboard";
			}
			List<Authorization> authorizations = authorizationManager.getAuthorizationsOfAdministrator(AuthService.getAuthName(), 4);
			
			map.addAttribute("authorizations", authorizations);
			map.addAttribute("placeholder", new PlaceHolder());
			
			
			return "/admin_select_account_for_display";
	}
	
	
	@RequestMapping(value = "account_display_maintanence", method = RequestMethod.POST)
	public String adminSelectAccountForDisplay(@ModelAttribute(value = "placeholder") PlaceHolder placeHolder,
			BindingResult result, ModelMap map, HttpSession session) {
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
		User user = userManager.getUserByUserId(AuthService.getAuthName());
		if(user!=null){
			if(!StaticMethods.isAdmin(user))
				return "redirect:/dashboard";
		}
		//Account account = accountManager.getAccountByAccountId(placeHolder.getPlaceholderstring());
		if(placeHolder == null || placeHolder.getPlaceholderstring() == null)
		{
			return "redirect:/dashboard";
		}
		Authorization authorization = authorizationManager.getAuthorizationsById(placeHolder.getPlaceholderstring());
		if(authorization==null)
			return "redirect:/dashboard";
		
		if(!authorizationManager.isAccountActuallyAuthorized(authorization.getEmployee().getUserId(), authorization.getAccount().getAccountId(), authorization.getTranCRUDPermission().getCrudPermissionId()))
			return "redirect:/dashboard";	
		map.addAttribute("account", authorization.getAccount());
		
		if(!authorization.getEmployee().getUserId().equals(AuthService.getAuthName())){
			map.addAttribute("msg", "you do not have sufficient privileges");
			return "redirect:/dashboard";	
		}
		
		
		authorization.setStatus(true);
		
		authorizationManager.addAuthorization(authorization);
		
		return "/showAccountDetails";
	}
		
	@RequestMapping(value="acc_delete_form", method=RequestMethod.GET)
	public String deleteAccountOption(ModelMap map){
			if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
				return StaticMethods.INDEX;
		
			User user = userManager.getUserByUserId(AuthService.getAuthName());
			if(user!=null){
				if(!StaticMethods.isAdmin(user))
					return "redirect:/dashboard";
			}
		
			List<Authorization> authorizations = authorizationManager.getAuthorizationsOfAdministrator(AuthService.getAuthName(), 6);
			
			map.addAttribute("authorizations", authorizations);
			map.addAttribute("placeholder", new PlaceHolder());
			
			
			return "/admin_select_account_for_delete";
	}
	
	
	@RequestMapping(value = "account_delete_maintanence", method = RequestMethod.POST)
	public String adminSelectAccountForDelete(@ModelAttribute(value = "placeholder") PlaceHolder placeHolder,
			BindingResult result, ModelMap map, HttpSession session) {
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
		User user = userManager.getUserByUserId(AuthService.getAuthName());
		if(user!=null){
			if(!StaticMethods.isAdmin(user))
				return "redirect:/dashboard";
		}
			
		//Account account = accountManager.getAccountByAccountId(placeHolder.getPlaceholderstring());
		if(placeHolder == null || placeHolder.getPlaceholderstring() == null)
		{
			return "redirect:/dashboard";
		}
		Authorization authorization = authorizationManager.getAuthorizationsById(placeHolder.getPlaceholderstring());
		if(authorization==null)
			return "redirect:/dashboard";
		
		if(!authorizationManager.isAccountActuallyAuthorized(authorization.getEmployee().getUserId(), authorization.getAccount().getAccountId(), authorization.getTranCRUDPermission().getCrudPermissionId()))
			return "redirect:/dashboard";	
		
		if(!authorization.getEmployee().getUserId().equals(AuthService.getAuthName())){
			map.addAttribute("msg", "you do not have sufficient privileges");
			return "redirect:/dashboard";	
		}
		
		
		map.addAttribute("account", authorization.getAccount());
		
		authorization.getAccount().getUser().setNotDeleted(false);
		authorization.setStatus(true);
		authorizationManager.addAuthorization(authorization);
		//Send notification email.
		userManager.sendAccountDeletionNotificationEmail(authorization.getAccount().getUser().getPersonalInfo());
		return "redirect:/dashboard";
	}
	
	
	@RequestMapping(value="acc_pii_edit_form", method=RequestMethod.GET)
	public String editAccountPIIOption(ModelMap map){
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
		User user = userManager.getUserByUserId(AuthService.getAuthName());
		if(user!=null){
			if(!StaticMethods.isAdmin(user))
				return "redirect:/dashboard";
		}
		
		
		
			List<Authorization> authorizations = authorizationManager.getAuthorizationsOfAdministrator(AuthService.getAuthName(), 7);
			
			map.addAttribute("authorizations", authorizations);
			map.addAttribute("placeholder", new PlaceHolder());
			
			
			return "/admin_select_account_for_pii_update";
	}
	
	
	@RequestMapping(value = "account_pii_maintanence", method = RequestMethod.POST)
	public String adminSelectPIIAccount(@ModelAttribute(value = "placeholder") PlaceHolder placeHolder,
			BindingResult result, ModelMap map, HttpSession session) {
		
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
		User user = userManager.getUserByUserId(AuthService.getAuthName());
		if(user!=null){
			if(!StaticMethods.isAdmin(user))
				return "redirect:/dashboard";
		}
		//Account account = accountManager.getAccountByAccountId(placeHolder.getPlaceholderstring());
		if(placeHolder == null || placeHolder.getPlaceholderstring() == null)
		{
			return "redirect:/dashboard";
		}
		Authorization authorization = authorizationManager.getAuthorizationsById(placeHolder.getPlaceholderstring());
		if(authorization==null)
			return "redirect:/dashboard";		
		
		if(!authorizationManager.isAccountActuallyAuthorized(authorization.getEmployee().getUserId(), authorization.getAccount().getAccountId(), authorization.getTranCRUDPermission().getCrudPermissionId()))
			return "redirect:/dashboard";	
		
		if(!authorization.getEmployee().getUserId().equals(AuthService.getAuthName())){
			map.addAttribute("msg", "you do not have sufficient privileges");
			return "redirect:/dashboard";	
		}
		
		map.addAttribute("personalinfo", authorization.getAccount().getUser().getPersonalInfo());
		map.addAttribute("comments", authorization.getComments());
		//session.setAttribute("authorization", authorization);
		this.authorization = authorization;
		
		return "/criticalEditInfo";
	}
	@RequestMapping(value = "account_pii_maintanence_update", method = RequestMethod.POST)
	public String adminPIIUpdate(@ModelAttribute(value = "personalinfo") PersonalInfo personalInfo, 
			BindingResult result, ModelMap map, HttpSession session, SessionStatus status) {
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
		User user = userManager.getUserByUserId(AuthService.getAuthName());
		if(user != null){
			if(!StaticMethods.isAdmin(user))
				return "redirect:/dashboard";
		}
		//Authorization authorization = (Authorization) session.getAttribute("authorization");
	
	
		authorization.getAccount().getUser().getPersonalInfo().setFirstName(personalInfo.getFirstName());
		authorization.getAccount().getUser().getPersonalInfo().setMiddleName(personalInfo.getMiddleName());
		authorization.getAccount().getUser().getPersonalInfo().setLastName(personalInfo.getLastName());
		authorization.getAccount().getUser().getPersonalInfo().setSsn(personalInfo.getSsn());
		//authorization.getAccount().getUser().getPersonalInfo().setDob(personalInfo.getDob());
		
		validate = new FormValidator();
		PersonalInfo pinfo = authorization.getAccount().getUser().getPersonalInfo();
		validate.editcriticalinfovalidate(pinfo, result);
		
		if(result.hasErrors())
		{
			return "/criticalEditInfo";
		}
		

		
		//System.out.println(	authorization.getAccount().getAccountType() + " " + authorization.getAccount().getUser().getPersonalInfo().getPhone());
		
		authorization.setStatus(true);
		authorizationManager.addAuthorization(authorization);
		//session.removeAttribute("authorization");
		//status.setComplete();
		
		authorization = null;
		map.addAttribute("msg", "successful update of pii info");
		return "redirect:/dashboard";
	}
	
	
	

}
