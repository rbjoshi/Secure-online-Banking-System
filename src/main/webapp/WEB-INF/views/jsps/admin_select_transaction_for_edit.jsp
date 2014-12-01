<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>
        <title>Software Security </title>
    </head>
    <body>
    <c:if test="${not empty transactions}">
 		      	  
	<form:form  method="post" action="/SecureBankingSystem/admin_transaction_update" commandName="placeholder">     	  
		
		
		
		<div class="form-group">	
				<form:label path="placeholderstring">Transaction Number:</form:label>
				<form:select path="placeholderstring"> 
				   <c:forEach items="${transactions}" var="transaction">
 						<form:option value="${transaction.transactionId}"> ${transaction.transactionId} </form:option>
 					</c:forEach>
				</form:select>
		</div>	
		<div class="form-group">
					<button class="btn btn-success" type="submit">Submit </button>
		</div>
	</form:form>
	</c:if>         
	</body>
	
</html>
