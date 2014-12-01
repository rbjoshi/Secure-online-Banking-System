package com.security.app.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.security.app.model.Transfer;

@Repository("transferDao")
public class TransferDaoImpl implements TransferDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addTransfer(Transfer transfer) {
		// TODO Auto-generated method stub
		
		System.out.println("Transfer obj = " + transfer);
		try
		{
		sessionFactory.getCurrentSession().save(transfer);
		System.out.println("Transfer"+transfer.getTransferId());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
