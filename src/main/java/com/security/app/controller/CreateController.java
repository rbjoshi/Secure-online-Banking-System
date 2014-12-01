package com.security.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.security.app.model.PlaceHolder;
import com.security.app.model.Transaction;
import com.security.app.model.TransactionType;
import com.security.app.model.User;
import com.security.app.service.AccountManager;
import com.security.app.service.AuthorizationManager;
import com.security.app.service.CrudPermissionManager;
import com.security.app.service.OTPManager;
import com.security.app.service.PersonalInfoManager;
import com.security.app.service.TransactionSbsManager;
import com.security.app.service.TransactionTypeManager;
import com.security.app.service.UserManager;
import com.security.app.util.AuthService;
import com.security.app.util.StaticMethods;


@Controller
@SessionAttributes({"authorization"})
public class CreateController {
	

	@Autowired
	private AccountManager accountManager;
	
	@Autowired
	private AuthorizationManager authorizationManager;
	
	@Autowired
	private CrudPermissionManager crudPermissionManager;
	
	
	@Autowired
	private TransactionSbsManager transactionSbsManager;
	
	@Autowired
	private TransactionTypeManager transactionTypeManager;
	
	
	@Autowired
	private UserManager userManager;

	@Autowired
	private PersonalInfoManager personalInfoManager;
	
	@Autowired
	private OTPManager otpManager;
	
	private FormValidator dcvalidation;
	
	private Authorization authorization;
	
	@RequestMapping(value="trans_auth_create_form", method=RequestMethod.GET)
	public String generateTransactionCreateForm(ModelMap map){
		
			if(StaticMethods.isAnnonymous(AuthService.getAuthName())){
				
				return StaticMethods.INDEX;
			}
				
			
			User user = userManager.getUserByUserId(AuthService.getAuthName());
			if(user!=null){
				if(StaticMethods.isInternalEmployee(user)){
										
					return "redirect:/dashboard";
					
				}
			}
			//logger.info("Adding transaction authorization");
			
			List<User> reg_employees = userManager.getRegEmployees();
	
					
		
			map.addAttribute("employees", reg_employees);
			map.addAttribute("authorization", new Authorization());
			
			return "/transactionauthorizationcreate";
	}
	@RequestMapping(value = "trans_auth_create_add", method = RequestMethod.POST)
	public String addTransactionCreateEmp(@ModelAttribute(value = "authorization") Authorization authorization,
			BindingResult result, ModelMap map) {
		
			if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
				return StaticMethods.INDEX;
		
			User user = userManager.getUserByUserId(AuthService.getAuthName());
			if(user!=null){
				if(StaticMethods.isInternalEmployee(user))
					return "redirect:/dashboard";
			}
			//logger.info("Adding transaction authorization for transaction "+authorization.getTransactionId());
			CrudPermission crudPermission = crudPermissionManager.getCrudPermission(5);
			Transaction t = new Transaction();
			
			Account account = accountManager.getAccountByUserId(user.getUserId());
			if(account==null){
				return "redirect:/dashboard";	
			}
			t.setStatus("R");
			t.setAccount(account);
			authorization.setTransaction(t);
			//logger.info("Added permissions have id "+crudPermission.getCrudPermissionId());
			authorization.setTranCRUDPermission(crudPermission);
			User employee = userManager.getUserByUserId(authorization.getEmpId());
			if(employee == null)
				return "redirect:/dashboard";
			authorization.setEmployee(employee);
		//	logger.info("Authorization for employee  "+authorization.getEmpId());
			
			if(!authorizationManager.checkIfAuthorizationExists(authorization.getTransaction().getTransactionId(), authorization.getTranCRUDPermission().getCrudPermissionId()))
				authorizationManager.addAuthorization(authorization);	
			else return "redirect:/dashboard";
		
		return "redirect:/dashboard";
	}
	
	
	@RequestMapping(value="transaction_create_form", method=RequestMethod.GET)
	public String createTransactionOption(ModelMap map){
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
			User user = userManager.getUserByUserId(AuthService.getAuthName());
			if(user!=null){
			if(!StaticMethods.isRegularEmployee(user))
				return "redirect:/dashboard";
			}
			List<Authorization> authorizations = authorizationManager.getAuthorizationsOfEmployee(AuthService.getAuthName(), 5);
			map.addAttribute("authorizations", authorizations);
			map.addAttribute("placeholder", new PlaceHolder());
			
			
			return "/employee_select_transaction_for_creation";
	}
	
	@RequestMapping(value = "transaction_create", method = RequestMethod.POST)
	public String employeeCreateTrans(@ModelAttribute(value = "placeholder") PlaceHolder placeHolder,
			BindingResult result, ModelMap map, HttpSession session, SessionStatus status) {
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
		User user = userManager.getUserByUserId(AuthService.getAuthName());
		
		if(user!=null){
			if(!StaticMethods.isRegularEmployee(user))
				return "redirect:/dashboard";
		}
		if(!user.getNotDeleted())
			return "redirect:/dashboard";
		
		if(placeHolder == null || placeHolder.getPlaceholderstring() == null)
		{
			return "redirect:/dashboard";
		}
		Authorization authorization = authorizationManager.getAuthorizationsById(placeHolder.getPlaceholderstring());
		if(authorization == null){
			return "redirect:/dashboard";
		}
		boolean isAuthorized = authorizationManager.isTransactionActuallyAuthorized(AuthService.getAuthName(), authorization.getTransaction().getTransactionId(), 5);
		if(!isAuthorized){
			return "redirect:/dashboard";
		}
		map.addAttribute("transaction", authorization.getTransaction());
		//session.setAttribute("authorization", authorization);
		this.authorization = authorization;
		
		return "/employee_dctransaction";
	}
	@RequestMapping(value = "transaction_create_finish", method = RequestMethod.POST)
	public String employeeTransactionCreate(@ModelAttribute(value = "transaction") Transaction transaction,
			BindingResult result, ModelMap map, HttpSession session, SessionStatus status) {
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		//Authorization authorization = (Authorization) session.getAttribute("authorization");
		
		User user = userManager.getUserByUserId(AuthService.getAuthName());
		if(user != null){
			if(!StaticMethods.isRegularEmployee(user))
				return "redirect:/dashboard";
		}
		
		
		authorization.getTransaction().setTransactionType(transactionTypeManager.getTransactionTypeByName(transaction.getTransactionTypeName()));
		authorization.getTransaction().setAmount(transaction.getAmount());
		
		dcvalidation = new FormValidator();
		dcvalidation.dcvalidate(authorization.getTransaction(),result);
		if(result.hasErrors())
		{
			
        	return "/employee_dctransaction";
        }
		
		
		
		if(authorization.getTransaction().getTransactionType().getTransactionTypeName().equals("debit")){
			if(authorization.getTransaction().getAccount().getBalance() - authorization.getTransaction().getAmount() > 0)
				authorization.getTransaction().getAccount().setBalance(authorization.getTransaction().getAccount().getBalance()-authorization.getTransaction().getAmount());
			else{
				authorization.getTransaction().setStatus("F");
				authorizationManager.addAuthorization(authorization);
				map.addAttribute("msg", "the account does not have enough balance to complete the transaction.");
				return "redirect:/dashboard";	
			}
		}
		else if(authorization.getTransaction().getTransactionType().getTransactionTypeName().equals("credit")){
			authorization.getTransaction().getAccount().setBalance(authorization.getTransaction().getAccount().getBalance()+authorization.getTransaction().getAmount());
			
		}
		authorization.getTransaction().setStatus("C");
		authorization.setStatus(true);
		authorizationManager.addAuthorization(authorization);
		
		authorization =null;
		//session.removeAttribute("authorization");
		
		return "redirect:/dashboard";
	}
	
}
