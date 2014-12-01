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
				<h1>Employee Dashboard!</h1>
			</div>
	</div>
	
	<c:if test="${not empty error}">
		<div class="error">${error}</div>
	</c:if>
	
	
	<c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
	</c:if>
	
	<div class="form-group">
		<a class="btn btn-primary" href="/SecureBankingSystem/transaction_read_form">View Authorized Transactions</a>
	</div>
	
	<div class="form-group">
		<a class="btn btn-primary" href="/SecureBankingSystem/transaction_edit_form">Update Authorized Transactions</a>
	</div>
	
	<div class="form-group">
		<a class="btn btn-primary" href="/SecureBankingSystem/transaction_create_form">Create Authorized Transactions</a>
	</div>
		
	<div class="form-group">
		<a class="btn btn-primary" href="/SecureBankingSystem/transaction_delete_form">Delete Authorized Transactions</a>
	</div>
	
	<div class="form-group">
		<a class="btn btn-primary" href="/SecureBankingSystem/submitted_payment_requests">View Submitted Payment Requests</a>
	</div>
	
	
	
	
    </body>
    
 </html>
