<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<body>
 
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
	
  	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Hello user ${pageContext.request.userPrincipal.name};
			<a	href="javascript:formSubmit()"> Logout</a>
			List of Approved Payments </h2>
	</c:if>
	 
	 <c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
				</c:if>
	 
	 <form:form method="post" action="/SecureBankingSystem/submit_payment_request_tobank" commandName="payment" enctype="multipart/form-data">
 	
 	<c:if test="${not empty payments}">
 	<table>
 		 
 	
	 	<c:forEach items="${payments}" var="item">
	 	
	 	<tr>
    	<td> <form:radiobutton path="paymentId" value="${item.paymentId}"/> ${item.merchantName} ${item.transaction.timestamp} ${item.transaction.amount}<br>
        </td>
        </tr>
	
	 	</c:forEach>
	 	
	 	
	 	<tr><td>
	 	 Certificate Upload: <input type="file" name="certfile"><br /> 
       
       
	 	</td></tr>
	 	
	 	
	 	<tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
            </td>
        </tr>
           </table>
 	</c:if>
 	
 	
 	<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
 	
   
   </form:form>
	 
		
</body>
</html>
