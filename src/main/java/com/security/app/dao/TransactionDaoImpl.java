package com.security.app.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.security.app.model.Account;
import com.security.app.model.Payment;
import com.security.app.model.Transaction;
import com.security.app.model.Transfer;

@Repository("transactionDao")
public class TransactionDaoImpl implements TransactionDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().saveOrUpdate(transaction);
	}
	public void deleteTransaction(Transaction transaction){
		SQLQuery query = (SQLQuery) this.sessionFactory.getCurrentSession().createSQLQuery("delete from authorisation where TRANSACTIONIDTOTRANSACTION=:transactionId").setInteger("transactionId", transaction.getTransactionId());
		query.executeUpdate();
		query = (SQLQuery) this.sessionFactory.getCurrentSession().createSQLQuery("delete from debitcredit where TRANSACTIONIDTOTRANSACTION=:transactionId").setInteger("transactionId", transaction.getTransactionId());
		query.executeUpdate();
		query = (SQLQuery) this.sessionFactory.getCurrentSession().createSQLQuery("delete from payment where TRANSACTIONIDTOTRANSACTION=:transactionId").setInteger("transactionId", transaction.getTransactionId());
		query.executeUpdate();
		query = (SQLQuery) this.sessionFactory.getCurrentSession().createSQLQuery("delete from transfer where TRANSACTIONIDTOTRANSACTION=:transactionId").setInteger("transactionId", transaction.getTransactionId());
		query.executeUpdate();
		query = (SQLQuery) this.sessionFactory.getCurrentSession().createSQLQuery("delete from transaction where TRANSACTIONID=:transactionId").setInteger("transactionId", transaction.getTransactionId());
		query.executeUpdate();
	}
	public List<Transaction> getTransactions() {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createQuery("from Transaction").list();
	}
	
	public List<Transaction> getRunningTransactions() {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createQuery("from Transaction T where T.status='R'").list();
	}
	
	public List<Transaction> getRunningDCTransactions() {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createQuery("from Transaction T where T.status='R' and T.transactionType.transactionTypeId <3").list();
	}
	
	

	public int addTransferTransaction(Transaction transaction) {
		sessionFactory.getCurrentSession().save(transaction);
		return transaction.getTransactionId();
		// TODO Auto-generated method stub
		
		
	}
	public Transaction getTransactionById(int transactionId) {
		// TODO Auto-generated method stub
		//List<Transaction> transactions = this.sessionFactory.getCurrentSession().createQuery("from Transaction T where T.transactionId="+transactionId).list();
		List<Transaction> transactions = this.sessionFactory.getCurrentSession().createQuery("from Transaction T where T.transactionId=:transactionId").setInteger("transactionId", transactionId).list();
		
		
		if(transactions != null && transactions.size()>0){
			if(transactions.get(0) != null)
				return transactions.get(0);
		}
		return null;
	}
	
	public List<Transaction> getUserTransactions(Account account) {
		// TODO Auto-generated method stub
		//return this.sessionFactory.getCurrentSession().createQuery("from Transaction T where T.account.accountId ='"+account.getAccountId()+"'").list();

		return this.sessionFactory.getCurrentSession().createQuery("from Transaction T where T.transactionType is not null and T.account.accountId = :accountId").setString("accountId", account.getAccountId()).list();

	}
	
	public List<Transaction> getUserDCTransactions(Account account) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createQuery("from Transaction T where T.status='R' and T.transactionType.transactionTypeId < 3 and  T.account.accountId =:accountId").setString("accountId", account.getAccountId()).list();

	}
	

	public boolean isValidTransaction(String userId, int transactionId){
		//List<Transaction> transactions = this.sessionFactory.getCurrentSession().createQuery("from Transaction T where T.status='R' and T.account.user.userId='"+userId+"' and T.transactionId="+transactionId+"").list();
		
		List<Transaction> transactions = this.sessionFactory.getCurrentSession().createQuery("from Transaction T where T.status='R' and T.account.user.userId=:userId and T.transactionId=:transactionId").setString("userId", userId).setInteger("transactionId", transactionId).list();
		
		if(transactions != null && transactions.size()>0){
			if(transactions.get(0) != null)
				return true;
		}
		return false;
	}
	
	public List<Transaction> getCriticalTransactions() {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createQuery("from Transaction t where t.critical=1").list();
	}
	public List<Payment> getUserPaymentTransactions(Account account) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createQuery("from Payment P where P.transaction.transactionType is not null and P.account.accountId = :accountId").setString("accountId", account.getAccountId()).list();
	}
	public List<Transfer> getUserTransferOutTransactions(Account account) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createQuery("from Transfer T where T.transaction.transactionType is not null and T.fromAccount.accountId = :accountId").setString("accountId", account.getAccountId()).list();
	}
}
