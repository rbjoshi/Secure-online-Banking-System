<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>
        <title>Software Security </title>
    </head>
    <body>
    <div class="row">
			<div class="col-md-12">
				<h1>Merchant Dashboard!</h1>
			</div>
	</div>
	
	<c:if test="${not empty error}">
		<div class="error">${error}</div>
	</c:if>
	
	<c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
	</c:if>
	
	
	
	<div class="form-group">
		<a class="btn btn-primary" href="/SecureBankingSystem/paymentrequest">Request Payment</a>	
	</div>
	
	<div class="form-group">
		<a class="btn btn-primary" href="/SecureBankingSystem/approved_payment_requests">View Customer Approved Payments</a>	
	</div>
	
	<div class="form-group">
		<a class="btn btn-primary" href="/SecureBankingSystem/trans_auth_form">Assign transactions to employees</a>
	</div>
	
	<div class="form-group">
		<a class="btn btn-primary" href="/SecureBankingSystem/trans_auth_create_form">Assign new transactions to employees</a>
	</div>
	
	<div class="form-group">
		<a class="btn btn-primary" href="/SecureBankingSystem/dc">Debit/Credit</a>
	</div>
	
	<div class="form-group">
		<a class="btn btn-primary" href="/SecureBankingSystem/accttransfer">Transfer</a>
	</div>
	
	<div class="form-group">
		<a class="btn btn-primary" href="/SecureBankingSystem/acc_auth_form">Assign account maintanence to employee</a>
	</div>
	
	<div class="form-group">
		<a class="btn btn-primary" href="/SecureBankingSystem/editPersonalInfo">Edit Personal Info</a>	
	</div>
	
	<div class="form-group">
		<a class="btn btn-primary" href="/SecureBankingSystem/viewAccountInfo">View Account Info</a>	
	</div>
	
	<div class="form-group">
		<a class="btn btn-primary" href="/SecureBankingSystem/acc_auth_pii_form">Submit Critical PII Change</a>	
	</div>

	<div class="form-group">
		<a class="btn btn-primary" href="/SecureBankingSystem/user_payment_requests">View Payment Requests</a>	
	</div>
	
	
	<div class="form-group">
		<a class="btn btn-primary" href="/SecureBankingSystem/user_transactions">Show Transaction</a>	
	</div>
	
    </body>
    
 </html>
