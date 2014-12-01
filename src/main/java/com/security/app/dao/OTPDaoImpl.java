package com.security.app.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.security.app.model.OTP;

@Repository("oTPDao")
public class OTPDaoImpl implements OTPDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addOTP(OTP otp) {
		// TODO Auto-generated method stub\
		sessionFactory.getCurrentSession().saveOrUpdate(otp);
	}

	public OTP getOTPByUser(String userId) {
		//List<OTP> otps = sessionFactory.getCurrentSession()	.createQuery("from OTP o where o.user.userId='" + userId + "'").list();
		List<OTP> otps = sessionFactory.getCurrentSession()	.createQuery("from OTP o where o.user.userId=:userId").setString("userId", userId).list();

		
		if (otps != null && otps.size() > 0 && !otps.isEmpty()) {
			return otps.get(0);
		}
		return null;
	}

	public void deleteOTPByUser(String userId) {
		OTP otp = getOTPByUser(userId);
		if(otp != null) {
			sessionFactory.getCurrentSession().delete(otp);
		}
	}

}
