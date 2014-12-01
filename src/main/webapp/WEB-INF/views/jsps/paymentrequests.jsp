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
 	
 	<div  class="col-md-12">
				<h2>Requested Payments</h2>
	</div>
		
 	<form:form method="post" action="/SecureBankingSystem/approve_payment_request" commandName="payment" enctype="multipart/form-data">
 	
 	<c:if test="${not empty payments}">
 		<table class="table table-striped table-hover custom-table">
 		<tr><td>
 		<div class="form-group">
	 		<c:forEach items="${payments}" var="item">
    			<form:radiobutton checked="" path="paymentId" value="${item.paymentId}"/> ${item.merchantName} ${item.transaction.timestamp} ${item.transaction.amount}<br/>    	
    		</c:forEach>
        </div>
        </td></tr>
		</table>
	 	
	 	<div class="form-group">
	 	 Certificate Upload: <input class="form-control" type="file" name="certfile"><br /> 
       </div>
       
	 	<div class="form-group">
                <input class="btn btn-success" type="submit" value="Authorize"/>
         </div>
 	</c:if>
 	
    
  
       
     	<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
        
   
    
   </form:form>
</body>
</html>
