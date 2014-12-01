<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>
        <title>Software Security </title>
    </head>
    <body>
    <div class="container">
	 

 	
 	<h2>TRANSFERS OUTWARD</h2>
 	<div class="table-responsive">
	<table class="table table-stripped">
	<thead><tr><th> Transaction Id </th> <th> Type of transaction </th>  <th> Time of transaction </th> <th>Amount</th> <th>Status</th><th>To</th></th></thead>
 	<c:if test="${not empty transferTransactions}">
	 	<c:forEach items="${transferTransactions}" var="item">
	 		<tr><td>${item.transactionId}</td><td> ${item.transactionType.transactionTypeName}</td><td>${item.timestamp}</td><td> ${item.amount}</td><td> <c:choose><c:when test="${item.status=='R'}"> Running</c:when> 
	 		<c:when test="${item.status=='C'}"> Completed</c:when>
	 		<c:when test="${item.status=='F'}"> Failed</c:when>  </c:choose></td><td>${item.account.user.personalInfo.firstName}</td></tr>
	 	</c:forEach>
 	</c:if>
 	</table>
 	</div>
 	
 	<br/><br/>
 	<h2>PAYMENTS OUTWARD</h2>
 	<div class="table-responsive">
	<table class="table table-stripped">
	<thead><tr><th> Transaction Id </th> <th> Type of transaction </th>  <th> Time of transaction </th> <th>Amount</th> <th>Status</th><th>To</th></tr></thead>
 	<c:if test="${not empty paymenttransactions}">
	 	<c:forEach items="${paymenttransactions}" var="item">
	 		<tr><td>${item.transactionId}</td><td> ${item.transactionType.transactionTypeName}</td><td>${item.timestamp}</td><td> ${item.amount}</td><td> <c:choose><c:when test="${item.status=='P'}"> Pending</c:when> <c:when test="${item.status=='A'}"> Approved</c:when> 
	 		<c:when test="${item.status=='S'}"> Submitted</c:when><c:when test="${item.status=='C'}"> Completed</c:when>
	 		<c:when test="${item.status=='F'}"> Failed</c:when>  </c:choose></td><td>${item.account.user.personalInfo.firstName}</td></tr>
	 	</c:forEach>
 	</c:if>
 	</table>
</div>
<br/><br/>
	 	<h2>DEBIT CREDIT TRANSFER INWARD PAYMENT INWARD</h2>
 		<div class="table-responsive">
			<table class="table table-stripped">
				
				<thead>
					<tr>
						<th>Transaction Id</th>
						<th>Type of transaction</th>
						<th>Time of transaction</th>
						<th>Amount</th>
						<th>Status</th>
					</tr>
				</thead>
				<c:if test="${not empty transactions}">
				 	<c:forEach items="${transactions}" var="item">
				 		<tr>
				 			<td>${item.transactionId}</td>
				 			<td> ${item.transactionType.transactionTypeName}</td>
				 			<td>${item.timestamp}</td>
				 			<td> ${item.amount}</td>
				 			<td> 
				 				<c:choose>
				 					<c:when test="${item.status=='R'}"> Running</c:when> 
				 					<c:when test="${item.status=='C'}"> Completed</c:when> 
				 					<c:when test="${item.status=='F'}"> Failed</c:when>  
			 					</c:choose>
		 					</td>
			 			</tr>
				 	</c:forEach>
	 			</c:if>	
			</table>
		</div>
	

 	</div>
</body>
</html>