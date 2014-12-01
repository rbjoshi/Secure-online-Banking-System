package com.security.app.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.security.app.dao.TransferDao;
import com.security.app.model.Transfer;

@Service("transferManager")
public class TransferManagerImpl implements TransferManager {

	@Autowired
	private TransferDao transferDao;

	@Transactional
	public void addTransfer(Transfer transfer) {
		// TODO Auto-generated method stub
		transferDao.addTransfer(transfer);

	}
}
