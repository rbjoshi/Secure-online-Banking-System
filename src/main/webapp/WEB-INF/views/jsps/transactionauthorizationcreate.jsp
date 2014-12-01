<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>
        <title>Software Security </title>
    </head>
    <body>
    <div class="container">
	
	

		<!--
		<c:forEach items="${transactions}" var="item">
	 						${item.transactionId} ${item.transactionType.transactionTypeName} ${item.account.accountId} ${item.timestamp} ${item.amount}<br>
	 	
	 	</c:forEach>
	 	 <div class="form-group">
			        	<c:forEach items="${employees}" var="emp">
	 						${emp.userId}<br>
	 					</c:forEach>
		 </div>
			      -->
		<form:form  method="post" action="/SecureBankingSystem/trans_auth_create_add" commandName="authorization">     	  
		
			
			
			<div class="form-group">	
					<form:label path="empId">Employee:</form:label>
					<form:select path="empId"> 
					   <c:forEach items="${employees}" var="emp">
	 						<form:option value="${emp.userId}"> ${emp.userId} </form:option>
	 					</c:forEach>
					</form:select>
			</div>	
			
			
			<div class="form-group">
						<button class="btn btn-success"  type="submit">Submit </button>
			</div>
		</form:form>

	</div>    
	</body>
	
</html>