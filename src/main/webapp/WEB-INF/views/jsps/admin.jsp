<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<body>
 
	<div class="row">
			<div class="col-md-12">
				<h1>Admin Dashboard!</h1>
			</div>
	</div>
	
	<c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
	</c:if>
	
	
	
	<c:if test="${not empty error}">
		<div class="error">${error}</div>
	</c:if>
	
	<c:forEach items="${transactions}" var="item">
 		${item.transactionId} ${item.transactionType.transactionTypeName} ${item.account.accountId} ${item.timestamp} ${item.amount}<br>
 	</c:forEach>
 	
 	<div class="form-group">
					<a class="btn btn-primary" href="internalsignup">Enroll Admin or Employee </a>
				</div>
	<div class="form-group">
		<a class="btn btn-primary" href="/SecureBankingSystem/acc_display_form"> View Authorized Account Details </a>
	</div>
	<div class="form-group">
		<a class="btn btn-primary" href="/SecureBankingSystem/acc_edit_form"> Update Authorized Account Details </a>
	</div>
	<div class="form-group">
		<a class="btn btn-primary" href="/SecureBankingSystem/acc_delete_form"> Delete Authorized Account Details </a>
	</div>

	
	<div class="form-group">
		<a class="btn btn-primary" href="/SecureBankingSystem/admin_transaction_edit_form"> Edit Pending Transactions </a>
	</div>
	
	<div class="form-group">
		<a class="btn btn-primary" href="/SecureBankingSystem/acc_pii_edit_form"> View pending PII changes </a>
	</div>

	<div class="form-group">
		<a class="btn btn-primary" href="/SecureBankingSystem/system-logs"> System Logs </a>
	</div>
	
	

</body>
</html>