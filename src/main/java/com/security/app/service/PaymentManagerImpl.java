package com.security.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.security.app.dao.PaymentDao;
import com.security.app.model.Account;
import com.security.app.model.Payment;
import com.security.app.model.TransactionType;
import com.security.app.model.Transfer;
import com.security.app.util.AuthService;
import com.security.app.util.KeyGenerator;
import com.security.app.util.PasswordEncryptService;

@Service("paymentManager")
public class PaymentManagerImpl implements PaymentManager {

	@Autowired
	private PaymentDao paymentDao;

	@Transactional
	public void addPayment(Payment payment) {
		// TODO Auto-generated method stub
		paymentDao.addPayment(payment);
	}
	
	
	@Transactional
	public List<Payment> getUserPaymentRequests(Account account) {
		// TODO Auto-generated method stub
		return paymentDao.getPaymentRequests(account);
	}
	@Transactional
	public List updatePaymentForApproval(Payment paymentupdate,HttpSession session) {
		// TODO Auto-generated method stub
		List resultList =new ArrayList();
		String msg = "";
		boolean result=false;
		
		if(paymentupdate.getPaymentId()==null)
		{
			 msg = "Select a Payment";
			 result=false;
			 resultList.add(result);
				resultList.add(msg);
				return resultList;
		}
		
		Payment payment = paymentDao.getPaymentById(paymentupdate.getPaymentId());
		Account fromAccount = payment.getAccount();
		
		//check if the payment from account user is the logged in user
		if(!(fromAccount.getUser().getUserId().equals(AuthService.getAuthName())))
		{
			msg = "You cannot Approve for other payments than yours";
			result = false;
		}
		else
		{
		String userId = fromAccount.getUser().getUserId();
		boolean verificationResult = KeyGenerator.verifyCertificate(userId,session);
		
		
		if(verificationResult)
				{
			
					payment.getTransaction().setStatus("A");
					paymentDao.addPayment(payment);
					//delete the cert file
					
					msg = "Approved successfully";
					result = true;
					
				}
		else
		{
			//user certificate error
			//not doing the transfer .setting status as failed
			//msg= "Transaction failed .Authentication fail";
			//payment.getTransaction().setStatus("FSF");
			//paymentDao.addPayment(payment);
			msg = "Bad Certificate.Authentication Failed";
			result = false;
		}
	}
		resultList.add(result);
		resultList.add(msg);
		return resultList;
	}
	@Transactional
	public List<Payment> getApprovedPaymentRequests(Account account) {
		// TODO Auto-generated method stub
		return paymentDao.getApprovedPaymentRequests(account);
	}
	
	@Transactional
	public List updatePaymentForSubmission(Payment paymentupdate,HttpSession session) {
		// TODO Auto-generated method stub
		
		List resultList =new ArrayList();
		String msg = "";
		boolean result=false;
		
		if(paymentupdate.getPaymentId()==null)
		{
			 msg = "Select a Payment";
			 result=false;
			 resultList.add(result);
				resultList.add(msg);
				return resultList;
		}
		
		
		
		Payment payment = paymentDao.getPaymentById(paymentupdate.getPaymentId());
		Account toAccount = payment.getTransaction().getAccount();
		
		if((!(toAccount.getUser().getUserId().equals(AuthService.getAuthName())) || (!payment.getTransaction().getStatus().equals("A"))))
		{
			msg = "You cannot Submit other payments other than yours and which are not in approved state";
			result = false;
		}
		else
		{
		
		
		String merchantId = toAccount.getUser().getUserId();
		boolean verificationResult = KeyGenerator.verifyCertificate(merchantId,session);
		
		
		if(verificationResult)
				{
			
					payment.getTransaction().setStatus("S");
					paymentDao.addPayment(payment);
					//delete the cert file
					msg = "Submission successful";
					result = true;
					
				}
		else
		{
			//user certificate error
			//not doing the transfer .setting status as failed
			//msg= "Transaction failed .Authentication fail";
			//payment.getTransaction().setStatus("FSF");
			//paymentDao.addPayment(payment);
			msg = "Bad Certificate.Authentication Failed";
			result = false;
		}
		}
		resultList.add(result);
		resultList.add(msg);
		return resultList;
	}
	
	@Transactional
	public List<Payment> getSubmittedPaymentRequests(Account account) {
		// TODO Auto-generated method stub
		return paymentDao.getSubmittedPaymentRequests(account);
	}
	
	
	@Transactional
	public List performpaymenttransfer(Payment paymentupdate) {
		// TODO Auto-generated method stub
		List resultList =new ArrayList();
		
		String msg = "";
		boolean result=false;
		
		if(paymentupdate.getPaymentId()==null)
		{
			 msg = "Select a Payment";
			 result=false;
			 resultList.add(result);
				resultList.add(msg);
				return resultList;
		}
		
		
		Payment payment = paymentDao.getPaymentById(paymentupdate.getPaymentId());
		
		Account toAccount = payment.getTransaction().getAccount();
		Account fromAccount = payment.getAccount();
		
		//check if the from account has sufficient funds
		if(fromAccount.getBalance()<payment.getTransaction().getAmount())
		{
			//not doing the transfer
			msg= "Transaction failed insufficient funds";
			payment.getTransaction().setStatus("F");
			paymentDao.addPayment(payment);
		}
		else
		{
			//Transfer amount from from to to account
			toAccount.setBalance(toAccount.getBalance()+payment.getTransaction().getAmount());
			fromAccount.setBalance(fromAccount.getBalance()-payment.getTransaction().getAmount());
			payment.getTransaction().setStatus("C");
			paymentDao.addPayment(payment);
			result= true;
			msg="Payment Transaction successful";
		}
		
		
		resultList.add(result);
		resultList.add(msg);
		
		return resultList;
		
	}

}
