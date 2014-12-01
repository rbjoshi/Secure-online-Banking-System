package com.security.app.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.security.app.model.Account;
import com.security.app.model.Payment;

@Repository("paymentDao")
public class PaymentDaoImpl implements PaymentDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addPayment(Payment payment) {
		System.out.println("Transfer obj = " + payment);
		try
		{
			sessionFactory.getCurrentSession().saveOrUpdate(payment);
			System.out.println("Payment"+payment.getPaymentId());
		}
		catch(Exception e)
			{
				e.printStackTrace();
			}

	}
	public List<Payment> getPaymentRequests(Account account) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createQuery("from Payment P where P.account.accountId =:accountId and P.transaction.status = 'P'").setString("accountId", account.getAccountId()).list();

	}

	public Payment getPaymentById(String paymentId) {
		// TODO Auto-generated method stub
		List<Payment> payments = this.sessionFactory.getCurrentSession().createQuery("from Payment P where P.paymentId=:paymentId").setString("paymentId", paymentId).list();
		if(payments!=null && payments.size()>0){
			if(payments.get(0)!=null){
				return payments.get(0);
			}
		}
		
		return null;

	}

	public List<Payment> getApprovedPaymentRequests(Account account) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createQuery("from Payment P where P.transaction.account.accountId =:accountId and P.transaction.status = 'A'").setString("accountId", account.getAccountId()).list();
	}

	public List<Payment> getSubmittedPaymentRequests(Account account) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createQuery("from Payment P where P.transaction.status = 'S'").list();
	}
	
	

}
