package com.security.app.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.security.app.constant.Constants;
import com.security.app.model.Account;
import com.security.app.model.Payment;
import com.security.app.model.PlaceHolder;
import com.security.app.model.Transaction;
import com.security.app.model.TransactionType;
import com.security.app.model.User;
import com.security.app.service.AccountManager;
import com.security.app.service.PaymentManager;
import com.security.app.service.TransactionSbsManager;
import com.security.app.service.TransactionTypeManager;
import com.security.app.service.UserManager;
import com.security.app.util.AuthService;
import com.security.app.util.KeyGenerator;
import com.security.app.util.StaticMethods;
import com.security.app.util.SystemLoggerService;

@Controller
@SessionAttributes({"transaction"})
public class TransactionController {

	private static final Logger logger = SystemLoggerService.logger;
	
	@Autowired
	private AccountManager accountManager;
	
	private FormValidator dcvalidation;
	
	@Autowired
	private PaymentManager paymentManager;
	
	
	@Autowired
	private TransactionTypeManager transactionTypeManager;
	

	@Autowired
	private TransactionSbsManager transactionsbsManager;
	
	@Autowired
	private UserManager userManager;
	
	
	Transaction transaction;
	
	@RequestMapping(value = Constants.DEBITCREDIT, method = RequestMethod.GET)
	public String signUpUser(ModelMap map,@RequestParam(value="msg",required=false)String msg) {
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
		logger.info("Debit-Credit is requested.");
		
		 User user = userManager.getUserByUserId(AuthService.getAuthName());
		  if(StaticMethods.isInternalEmployee(user))
				return "redirect:/dashboard";
		if(msg!=null)
		map.addAttribute("msg",msg);
		map.addAttribute("accountId", accountManager.getAccountByUserId(AuthService.getAuthName()).getAccountId());
		map.addAttribute("transaction", new Transaction());
		return "/dctransaction";
	}
	@RequestMapping(value = Constants.DEBITCREDIT_ADD, method = RequestMethod.POST)
	public String debitcredit(@ModelAttribute(value = "transaction") Transaction transaction,
			BindingResult result, ModelMap map, HttpSession session) {
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
		User user = userManager.getUserByUserId(AuthService.getAuthName());
		  if(StaticMethods.isInternalEmployee(user))
				return "redirect:/dashboard";
		
		dcvalidation = new FormValidator();
		dcvalidation.dcvalidate(transaction,result);
		if(result.hasErrors())
		{
			
        	return "/dctransaction";
        }
		
		else{	
				Account account = accountManager.getAccountByUserId(AuthService.getAuthName());
				transaction.setAccount(account);
				 logger.info("Received a Debit-Credit request for account"+transaction.getAccount().getAccountId());
				
				TransactionType transactionType = transactionTypeManager.getTransactionTypeByName(transaction.getTransactionTypeName());
				
				if(transaction.getAmount()<=1000)
				{
					if(transaction.getTransactionTypeName().equals("credit"))
						account.setBalance((account.getBalance()+transaction.getAmount()));
					else{
						if (account.getBalance()-transaction.getAmount() > 0){
							account.setBalance((account.getBalance()-transaction.getAmount()));
						}
						else{
							map.addAttribute("msg", "you do not have enough balance to complete the transaction.");
							return "redirect:/dashboard";	
						}
					}
					accountManager.updateAccount(account);
					transaction.setStatus("C");
					
				}
				else{
					transaction.setStatus("R");
			
					
					//transaction.setCritical(true);
					
					transaction.setAccount(account);
					transaction.setTransactionType(transactionType);
					
					//transactionsbsManager.addTransaction(transaction);
					
					this.transaction = transaction;
					//session.setAttribute("transaction", transaction);
					
					return "/transaction_certificate";
				}
				transaction.setAccount(account);
				transaction.setTransactionType(transactionType);
				
				transactionsbsManager.addTransaction(transaction);
				
				
				return "redirect:/dashboard";
		}
	}
	
	
	@RequestMapping(value = "/critical_transaction_approve", method = RequestMethod.POST)
	public String dc_cert_approve(ModelMap map, HttpSession session,@RequestParam(value="certfile",required = true) MultipartFile file, SessionStatus status ) {
		String msg=""; boolean result=false;
		
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		Account account = accountManager.getAccountByUserId(AuthService.getAuthName());
	  File tempfile=null;
	  
	  
	  
	  if (!file.isEmpty() && file.getSize()!=0) {
          try {
              byte[] bytes = file.getBytes();

              // Creating the directory to store file
              ServletContext context = session.getServletContext();
              String realContextPath = context.getRealPath("/");
              File dir = new File(realContextPath+"/certificates");
              if (!dir.exists())
                  dir.mkdirs();

              // Create the file on server
              tempfile = new File(realContextPath+"/certificates/"+AuthService.getAuthName()+"_cert.pem");
              BufferedOutputStream stream = new BufferedOutputStream(
                      new FileOutputStream(tempfile));
              stream.write(bytes);
              stream.close();
          } catch (Exception e) {
              
          }
      } else {
    	  status.setComplete();
    	  map.addAttribute("msg", "No Certificate provided");
    	  return "redirect:"+Constants.DEBITCREDIT;
      }
	  
	  if(!transaction.getAccount().getUser().getUserId().equals(AuthService.getAuthName())){
		  
		  ServletContext context = session.getServletContext();
			String realContextPath = context.getRealPath("/");
			String certpath = realContextPath+"/certificates/"+AuthService.getAuthName()+"_cert.pem";
			File filetoDelete = new File(certpath);
			filetoDelete.delete();
		  
		  
		  map.addAttribute("msg", "Bad credentials");
		  transaction=null;
    	  return "redirect:"+Constants.DEBITCREDIT;
	  }
	  
	  
	//  List result = paymentManager.updatePaymentForApproval(payment,session);
	  boolean verificationResult = KeyGenerator.verifyCertificate(transaction.getAccount().getUser().getUserId(), session);
	  
	  if(verificationResult)
		{
	
			transaction.setCritical(true);
			transactionsbsManager.addTransaction(transaction);
			
			//delete the cert file
			msg = "Submission successful. Authorize an employee or wait for admin to complete it";
			result = true;
			
		}
		else
		{
	
			msg = "Bad Certificate.Authentication Failed";
			result = false;
		}
	//delete certificate
			ServletContext context = session.getServletContext();
			String realContextPath = context.getRealPath("/");
			String certpath = realContextPath+"/certificates/"+AuthService.getAuthName()+"_cert.pem";
			File filetoDelete = new File(certpath);
			filetoDelete.delete();
			
			
			transaction = null;
			
			
			
			map.addAttribute("msg", msg);
		
	
	  
			return "redirect:"+Constants.DEBITCREDIT;

	}
	
	
	
	@RequestMapping(value="admin_transaction_edit_form", method=RequestMethod.GET)
	public String showTransactionEditForm(ModelMap map, HttpSession session){
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
		User user = userManager.getUserByUserId(AuthService.getAuthName());
		  if(!StaticMethods.isAdmin(user))
				return "redirect:/dashboard";
		
		
		List<Transaction> transactions = transactionsbsManager.getRunningDCTransactions();
		map.addAttribute("transactions", transactions);
		map.addAttribute("placeholder", new PlaceHolder());
		return "/admin_select_transaction_for_edit";
	}
	@RequestMapping(value = "admin_transaction_update", method = RequestMethod.POST)
	public String adminSelectAccount(@ModelAttribute(value = "placeholder") PlaceHolder placeHolder,
			BindingResult result, ModelMap map, HttpSession session) {
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
		User user = userManager.getUserByUserId(AuthService.getAuthName());
		 if(!StaticMethods.isAdmin(user))
				return "redirect:/dashboard";
			
		int t_id = Integer.parseInt(placeHolder.getPlaceholderstring());
		if(t_id>0){
				
				Transaction transaction = transactionsbsManager.getTransactionById(t_id);
				if(transaction==null)
				{
					return "redirect:/dashboard";
				}
				if(!transaction.getStatus().equals("R"))
					return "redirect:/dashboard";
				
				map.addAttribute("transaction", transaction);
				this.transaction = transaction;
				return "/adminEditTransactionDetails";
		}
		return "redirect:/dashboard";
	}
	@RequestMapping(value = "admin_transaction_edit_finish", method = RequestMethod.POST)
	public String adminTransactionUpdate(@ModelAttribute(value = "transaction") Transaction new_transaction,
			BindingResult result, ModelMap map, HttpSession session) {
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
		User user = userManager.getUserByUserId(AuthService.getAuthName());
		 if(!StaticMethods.isAdmin(user))
				return "redirect:/dashboard";
		
		Transaction transaction= (Transaction) session.getAttribute("transaction");
		
	
		transaction.setStatus(new_transaction.getStatus());
		
		
		if(transaction.getStatus().equals("C")){
			if(transaction.getTransactionType().getTransactionTypeName().equals("debit")){
				if(transaction.getAccount().getBalance() - transaction.getAmount() > 0)
					transaction.getAccount().setBalance(transaction.getAccount().getBalance()-transaction.getAmount());
				else{
					
					transaction.setStatus("F");
					transactionsbsManager.addTransaction(transaction);
					map.addAttribute("msg", "you do not have enough balance to complete the transaction.");
					return "redirect:/dashboard";	
				}
			}
			else if(transaction.getTransactionType().getTransactionTypeName().equals("credit")){
				transaction.getAccount().setBalance(transaction.getAccount().getBalance()+transaction.getAmount());
				
			}
			transactionsbsManager.addTransaction(transaction);
		}
		
		//session.removeAttribute("transaction");
		
		transaction=null;
		return "redirect:/dashboard";
	}
	
	@RequestMapping(value="critical_transactions_form", method = RequestMethod.GET)
	public String showCriticalTransacations(ModelMap map){
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
		List<Transaction> transactions = transactionsbsManager.getCriticalTransactions();
		
		map.addAttribute("transactions", transactions);
		
		
		return "/edit";
	}
	@RequestMapping(value = Constants.TRANSFER, method = RequestMethod.GET)
	public String transferAccounts(ModelMap map) {
		
		//Check if annonymous 
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		User user = userManager.getUserByUserId(AuthService.getAuthName());
		if(StaticMethods.isInternalEmployee(user)) {
			return "redirect:/dashboard";
		}
		logger.info("Transfer request");
		map.addAttribute("accountId", accountManager.getAccountByUserId(AuthService.getAuthName()));
		map.addAttribute("transaction", new Transaction());
		return "/accttransfer";
	}
	
	@RequestMapping(value = Constants.TRANSFER , method=RequestMethod.POST)
	public String transferAccounts(@ModelAttribute(value="transaction") Transaction  transaction,
			BindingResult result, ModelMap map) {
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		User user = userManager.getUserByUserId(AuthService.getAuthName());
		if(StaticMethods.isInternalEmployee(user)) {
			return "redirect:/dashboard";
		}
		try
		{
			transactionsbsManager.addTransferTransaction(transaction);
		}
		catch(Exception e)
		{
			map.put("error",e.getMessage());
		}
		return StaticMethods.isMerchant(user) ? "/merchantdashboard" : "/customerdashboard";
	}
	
	
	
	@RequestMapping(value = Constants.PAYMENT, method = RequestMethod.GET)
	public String requestPayment(ModelMap map) {
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;

		User user = userManager.getUserByUserId(AuthService.getAuthName());
		if(StaticMethods.isInternalEmployee(user) || StaticMethods.isCustomer(user)) {
			map.addAttribute("msg", "Not allowed to access this.");
			return "redirect:/dashboard";
		}
		map.addAttribute("accountId", accountManager.getAccountByUserId(AuthService.getAuthName()));
		map.addAttribute("transaction", new Transaction());
		return "/paymentrequest";
	}
	
	@RequestMapping(value = Constants.PAYMENT , method=RequestMethod.POST)
	public String requestPayment(@ModelAttribute(value="transaction") Transaction  transaction,
			BindingResult result, ModelMap map) {
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;

		User user = userManager.getUserByUserId(AuthService.getAuthName());
		if(!StaticMethods.isMerchant((user))) {
			map.addAttribute("forget_pass_user","");
			map.addAttribute("msg","You are not allowed to access.");
			return "redirect:/dashboard";
		}
		try
		{
			Account account = accountManager.getAccountByAccountId(transaction.getFromAccountId());
			if(account == null) {
				map.put("error", "There is no such account.!!");
				return "/merchantdashboard";
			}
			if(account.getUser().getUserId().equals(AuthService.getAuthName()))
			{
				map.put("error", "The account Id you gave is yours");
				return "/merchantdashboard";
			}
			transaction.setAccount(account);
			transactionsbsManager.addPaymentTransaction(transaction);
		}
		catch(Exception e)
		{
			map.put("error", e.getMessage());
			return "/merchantdashboard";
		}
		
		return "redirect:/dashboard";
		
	}
	
	@RequestMapping(value = Constants.PAYMENT_USER_REQUEST, method = RequestMethod.GET)
	public String user_payments(ModelMap map,@RequestParam(value = "msg", required = false) String msg) {
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		User user = userManager.getUserByUserId(AuthService.getAuthName());
		if(StaticMethods.isInternalEmployee(user)) {
			map.addAttribute("msg", "You are not allowed to access.");
			return "redirect:/dashboard";
		}
			
	  Account account = accountManager.getAccountByUserId(AuthService.getAuthName());
	  
	  List<Payment> paymentRequests = paymentManager.getUserPaymentRequests(account);
	  
	  map.addAttribute("payments", paymentRequests);
	  map.addAttribute("payment", new Payment());
	  map.addAttribute("msg", msg);
	 
	  return "/paymentrequests";

	}
	
	
	
	
	
	@RequestMapping(value = Constants.PAYMENT_APPROVE_REQUEST, method = RequestMethod.POST)
	public String payment_approve(@ModelAttribute(value = "payment") Payment payment,ModelMap map, HttpSession session,@RequestParam(value="certfile",required = true) MultipartFile file ) {
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		Account account = accountManager.getAccountByUserId(AuthService.getAuthName());
	  File tempfile=null;
	  
	  
	  
	  if (!file.isEmpty() && file.getSize()!=0) {
          try {
              byte[] bytes = file.getBytes();

              // Creating the directory to store file
              ServletContext context = session.getServletContext();
              String realContextPath = context.getRealPath("/");
              File dir = new File(realContextPath+"/certificates");
              if (!dir.exists())
                  dir.mkdirs();

              // Create the file on server
              tempfile = new File(realContextPath+"/certificates/"+AuthService.getAuthName()+"_cert.pem");
              BufferedOutputStream stream = new BufferedOutputStream(
                      new FileOutputStream(tempfile));
              stream.write(bytes);
              stream.close();
          } catch (Exception e) {
              
          }
      } else {
    	  return "redirect:"+Constants.PAYMENT_USER_REQUEST+"?msg=No Certificate provided";
      }
	  
	  
	  List result = paymentManager.updatePaymentForApproval(payment,session);
	//delete certificate
			ServletContext context = session.getServletContext();
			String realContextPath = context.getRealPath("/");
			String certpath = realContextPath+"/certificates/"+AuthService.getAuthName()+"_cert.pem";
			File filetoDelete = new File(certpath);
			filetoDelete.delete();
	  if((Boolean)result.get(0) == false)
	  {
		  //add msg that transaction failed
		  return "redirect:"+Constants.PAYMENT_USER_REQUEST+"?msg="+result.get(1);
	  }
	
	  
	  return "redirect:"+Constants.PAYMENT_USER_REQUEST+"?msg="+result.get(1);

	}
	
	
	@RequestMapping(value = Constants.PAYMENT_APPROVED_REQUESTS, method = RequestMethod.GET)
	public String user_approved_payments(ModelMap map,@RequestParam(value = "msg", required = false) String msg) {
		
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		
		User user = userManager.getUserByUserId(AuthService.getAuthName());
		if(!StaticMethods.isMerchant(user))
			return "redirect:/dashboard";
		
	  Account account = accountManager.getAccountByUserId(AuthService.getAuthName());
	  if(account == null) {
		  map.put("error", "You are registered user but don't have an account");
		  return "/merchantdashboard";
	  }
	  List<Payment> approvedpaymentRequests = paymentManager.getApprovedPaymentRequests(account);
	  
	  map.addAttribute("payments", approvedpaymentRequests);
	  map.addAttribute("payment", new Payment());
	  map.addAttribute("msg", msg);
	 
	  return "/approvedpaymentrequests";

	}
	
	@RequestMapping(value = Constants.PAYMENT_SUBMIT_BANK, method = RequestMethod.POST)
	public String payment_submit(@ModelAttribute(value = "payment") Payment payment,ModelMap map, HttpSession session,@RequestParam(value="certfile",required = true) MultipartFile file) {
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
	   Account account = accountManager.getAccountByUserId(AuthService.getAuthName());
	  
 File tempfile=null;
	  
	  
	  
	  if (!file.isEmpty() && file.getSize()!=0) {
          try {
              byte[] bytes = file.getBytes();

              // Creating the directory to store file
              ServletContext context = session.getServletContext();
              String realContextPath = context.getRealPath("/");
              File dir = new File(realContextPath+"/certificates");
              if (!dir.exists())
                  dir.mkdirs();

              // Create the file on server
               tempfile = new File(realContextPath+"/certificates/"+AuthService.getAuthName()+"_cert.pem");
              BufferedOutputStream stream = new BufferedOutputStream(
                      new FileOutputStream(tempfile));
              stream.write(bytes);
              stream.close();
          } catch (Exception e) {
              
          }
      } else {
    	  return "redirect:"+Constants.PAYMENT_APPROVED_REQUESTS+"?msg=No Certificate provided";
      }
	  
	  
	  
	  List result = paymentManager.updatePaymentForSubmission(payment,session);
	//delete certificate
		ServletContext context = session.getServletContext();
		String realContextPath = context.getRealPath("/");
		String certpath = realContextPath+"/certificates/"+AuthService.getAuthName()+"_cert.pem";
		File filetoDelete = new File(certpath);
		filetoDelete.delete();
	  if((Boolean)result.get(0) == false)
	  {
		  //add msg that transaction failed
		  return "redirect:"+Constants.PAYMENT_APPROVED_REQUESTS+"?msg="+result.get(1);
	  }
	  //tempfile.delete();
	
	  return "redirect:"+Constants.PAYMENT_APPROVED_REQUESTS+"?msg="+result.get(1);
	  

	}
	
	@RequestMapping(value = Constants.PAYMENT_SUBMITTED_REQUESTS, method = RequestMethod.GET)
	public String submitted_payments_bank(ModelMap map,@RequestParam(value = "msg", required = false) String msg) {
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
		User user = userManager.getUserByUserId(AuthService.getAuthName());
		if(!StaticMethods.isInternalEmployee(user) || StaticMethods.isAdmin(user)) {
			map.addAttribute("forget_pass_user","");
			map.addAttribute("msg","You are not allowed to access.");
			return "redirect:/dashboard";
		}
	  Account account = accountManager.getAccountByUserId(AuthService.getAuthName());
	  
	  List<Payment> submittedpaymentRequests = paymentManager.getSubmittedPaymentRequests(account);
	  
	  map.addAttribute("payments", submittedpaymentRequests);
	  map.addAttribute("payment", new Payment());
	  map.addAttribute("msg", msg);
	  
	 
	  return "/submittedpaymentrequests";

	}
	
	@RequestMapping(value = Constants.PAYMENT_TRANSFER, method = RequestMethod.POST)
	public String paymenttransfer(@ModelAttribute(value = "payment") Payment payment,ModelMap map, HttpSession session) {
		
		if(StaticMethods.isAnnonymous(AuthService.getAuthName()))
			return StaticMethods.INDEX;
	  Account account = accountManager.getAccountByUserId(AuthService.getAuthName());
	 
	 List results= paymentManager.performpaymenttransfer(payment);
	 if((Boolean)results.get(0)==false)
	 {
		 return "redirect:"+Constants.PAYMENT_SUBMITTED_REQUESTS+"?msg="+results.get(1);
		 
	 }
	 else
	 {
		 return "redirect:"+Constants.PAYMENT_SUBMITTED_REQUESTS+"?msg="+results.get(1);
	 }
	}

	
	
	
}
