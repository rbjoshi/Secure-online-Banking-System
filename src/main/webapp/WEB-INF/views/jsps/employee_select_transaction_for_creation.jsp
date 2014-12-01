<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>
        <title>Software Security </title>
    </head>
    <body>
	<c:if test="${not empty authorizations}">
 		      	  
	<form:form  method="post" action="/SecureBankingSystem/transaction_create" commandName="placeholder">     	  
		
		<div class="form-group">	
				<form:label path="placeholderstring">Transaction Number:</form:label>
				<form:select path="placeholderstring"> 
				   <c:forEach items="${authorizations}" var="authorization">
 						<form:option value="${authorization.authorizationId}"> ${authorization.transaction.transactionId} </form:option>
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
