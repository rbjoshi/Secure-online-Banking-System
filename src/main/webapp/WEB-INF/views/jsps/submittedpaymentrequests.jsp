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
	 
	<c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
				</c:if>
 	
 	
 	<form:form method="post" action="/SecureBankingSystem/paymenttransfer" commandName="payment" >
 	
 	<c:if test="${not empty payments}">
 	<table class="table table-striped table-hover custom-table">
 		 
 	
	 	<c:forEach items="${payments}" var="item">
	 	
	 	<tr>
    	<td> <form:radiobutton path="paymentId" value="${item.paymentId}"/>${item.account.user.personalInfo.firstName} ${item.merchantName} ${item.transaction.timestamp} ${item.transaction.amount}<br>
        </td>
        </tr>
	
	 	</c:forEach>
	 	
	 	<tr>
            <td colspan="2">
                <input type="submit" value="Transfer"/>
            </td>
        </tr>
           </table>
 	</c:if>
 	
    
  <input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
       
     	
        
   
    
   </form:form>
</body>
</html>
