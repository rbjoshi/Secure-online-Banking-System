package com.security.app.constant;


public interface Constants {

	// Request Mappings
	public static final String SIGN_UP = "/signup";
	public static final String IN_SIGN_UP = "/internalsignup";
	public static final String ACCOUNT_ADD = "/accountadd";
	public static final String LOGIN = "/login";
	public static final String ADMIN = "/admin";
	public static final String DASHBOARD = "/dashboard";
	public static final String ADD = "/add";
	public static final String ADD_INT = "/addinternal";
	public static final String AUTH = "/auth";
	public static final String ACCOUNT_FORM = "/accountform";
	public static final String DEBITCREDIT = "/dc";
	public static final String DEBITCREDIT_ADD = "/dcadd";
	public static final String ADMIN_MAPPING = "/admin**";
	public static final String DASHBOARD_MAPPING = "/dashboard**";
	public static final String FORGET_PASS = "/forgetpassword";
	public static final String NEW_PASS = "/newpassword";
	public static final String USERNAME_AUTH ="/usernameauth";
	public static final String TRANSFER ="/accttransfer";
	public static final String INDEX="/";
	public static final String PAYMENT ="/paymentrequest";
	public static final String PAYMENT_USER_REQUEST = "/user_payment_requests";
	public static final String PAYMENT_APPROVE_REQUEST ="/approve_payment_request";
	public static final String PAYMENT_APPROVED_REQUESTS ="/approved_payment_requests";
	public static final String PAYMENT_SUBMIT_BANK ="/submit_payment_request_tobank";
	public static final String PAYMENT_SUBMITTED_REQUESTS ="/submitted_payment_requests";
	public static final String PAYMENT_TRANSFER ="/paymenttransfer";
	//Links
	public static final String LINK_PASSWORD_RESET = "localhost:8080/SecureBankingSystem/forgetpawword";
}
