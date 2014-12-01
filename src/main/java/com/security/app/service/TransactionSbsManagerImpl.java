package com.security.app.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.security.app.dao.TransactionDao;
import com.security.app.dao.TransferDao;
import com.security.app.model.Account;
import com.security.app.model.Payment;
import com.security.app.model.Transaction;
import com.security.app.model.TransactionType;
import com.security.app.model.Transfer;
import com.security.app.util.AuthService;

@Service("transactionSbsManager")
public class TransactionSbsManagerImpl implements TransactionSbsManager {

	@Autowired
	private TransactionDao transactionDao;
	
	@Autowired
	private TransferManager transferManager;
	
	@Autowired
	private AccountManager accountManager;
	
	@Autowired
	private PaymentManager paymentManager;
	
	@Autowired
	private TransactionTypeManager transactionTypeManager;
	
	@Transactional
	public void addTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		transactionDao.addTransaction(transaction);
	}
	@Transactional
	public void deleteTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		transactionDao.deleteTransaction(transaction);
	}

	@Transactional
	public List<Transaction> getTransactions() {
		// TODO Auto-generated method stub
		return transactionDao.getTransactions();
	}
	@Transactional
	public List<Transaction> getRunningTransactions(){
		return transactionDao.getRunningTransactions();
	}
	@Transactional
	public List<Transaction> getRunningDCTransactions(){
		return transactionDao.getRunningDCTransactions();
	}
	
	@Transactional
	public List<Transaction> getCriticalTransactions() {
		// TODO Auto-generated method stub
		return transactionDao.getCriticalTransactions();
	}
	
	@Transactional
	public Transaction getTransactionById(int transactionId){
		return transactionDao.getTransactionById(transactionId);
	}

	@Transactional
	public void addTransferTransaction(Transaction transaction) throws Exception {
		Account toAccount = accountManager.getAccountByAccountId(transaction.getToAccountId());
		if(toAccount == null) {
			throw new Exception("Receiver account doesn't exist!! Your transaction is failed.");
		}
		toAccount.setBalance(toAccount.getBalance()+transaction.getAmount());
		TransactionType transactionType = transactionTypeManager.getTransactionTypeByName("transfer");
		
		transaction.setAccount(toAccount);
		transaction.setTransactionType(transactionType);
		transaction.setStatus("C");
		int transactionID = transactionDao.addTransferTransaction(transaction);
		System.out.println("ID="+transactionID);
		
		Transfer transfer = new Transfer();
		Account fromAccount = accountManager.getAccountByUserId(AuthService.getAuthName());
		
		if(transaction.getAmount()<10)
			throw new Exception("Amount is less than ten!! Your transaction is failed.");
		
		if(fromAccount.getBalance()<transaction.getAmount())
			throw new Exception("Less Balance.!! Your transaction is failed.");
		
		if(toAccount.getUser().getNotDeleted()== false)
			throw new Exception("Receiver account information not valid..!! Your transaction is failed.");
		fromAccount.setBalance(fromAccount.getBalance()-transaction.getAmount());
		transfer.setFromAccount(fromAccount);
		transaction.setTransactionId(transactionID);
		transfer.setTransaction(transaction);
		transferManager.addTransfer(transfer);
	}

	@Transactional
	public List<Transaction> getUserTransactions(Account account){
		return transactionDao.getUserTransactions(account);
	}
	@Transactional
	public List<Transaction> getUserDCTransactions(Account account){
		return transactionDao.getUserDCTransactions(account);
	}
	@Transactional
	public boolean isValidTransaction(String userId, int transactionId){
		return transactionDao.isValidTransaction(userId, transactionId);
	}
	
	@Transactional
	public void addPaymentTransaction(Transaction transaction) throws Exception {
		
		
		Account toAccount = accountManager.getAccountByUserId(AuthService.getAuthName());
		TransactionType transactionType = transactionTypeManager.getTransactionTypeByName("payment");
		transaction.setAccount(toAccount);
		transaction.setTransactionType(transactionType);
		transaction.setStatus("P");
		
		
		
		
		if(toAccount.getUser().getNotDeleted()== false)
			throw new Exception("check receiver's information");
		
		
		Payment payment = new Payment();
		Account fromAccount = accountManager.getAccountByAccountId(transaction.getFromAccountId());
		
		if(fromAccount == null) {
			throw new Exception("Customer account doesn't exist!! Your transaction is failed.");
		}
		if(transaction.getAmount()<10)
			throw new Exception("Amount is less than ten!! Your transaction is failed.");
		
		payment.setAccount(fromAccount);
        payment.setMerchantName(toAccount.getUser().getUserId());
        payment.setTransaction(transaction);		
		paymentManager.addPayment(payment);
	}
	@Transactional
	public List<Transaction> getUserPaymentTransactions(Account account) {
		// TODO Auto-generated method stub
		List<Payment> paymentsList =  transactionDao.getUserPaymentTransactions(account);
		List<Transaction> transactionList = new ArrayList<Transaction>();
	  Iterator< Payment> paymentIterator=paymentsList.iterator();
	  while(paymentIterator.hasNext())
	  {
		  transactionList.add(paymentIterator.next().getTransaction());
	  }
	  return transactionList;
	}
	
	
	@Transactional
	public List<Transaction> getUserTransferOutTransactions(Account account) {
		// TODO Auto-generated method stub
		List<Transfer> transferList =  transactionDao.getUserTransferOutTransactions(account);
		List<Transaction> transactionList = new ArrayList<Transaction>();
	  Iterator< Transfer> transferIterator=transferList.iterator();
	  while(transferIterator.hasNext())
	  {
		  transactionList.add(transferIterator.next().getTransaction());
	  }
	  return transactionList;
	}
}
