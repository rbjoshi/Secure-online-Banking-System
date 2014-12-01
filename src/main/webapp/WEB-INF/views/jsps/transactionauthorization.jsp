<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>
        <title>Software Security </title>
    </head>
    <body>
    <div class="container">
	
	
	<c:if test="${not empty transactions}">
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
		<form:form  method="post" action="/SecureBankingSystem/trans_auth_add" commandName="authorization">     	  
			 <div class="form-group">
			 		<form:label path="transactionId">Transaction:</form:label>
			        <form:select class="form-control" path="transactionId"> 
					   <c:forEach items="${transactions}" var="item">
	 						<form:option value="${item.transactionId}"> ${item.transactionId} </form:option>
	 					</c:forEach>
					</form:select>
			</div>
			
			
			<div class="form-group">	
					<form:label path="empId">Employee:</form:label>
					<form:select class="form-control" path="empId"> 
					   <c:forEach items="${employees}" var="emp">
	 						<form:option value="${emp.userId}"> ${emp.userId} </form:option>
	 					</c:forEach>
					</form:select>
			</div>	
			
			<div class="form-group">
		        	<form:label path="permissionId">Permissions:</form:label>
		        	
		        	<div class="radio">
		        		<form:label path="permissionId" class="form-control">
		        			<form:radiobutton path="permissionId" checked="checked" value="4"/> Read
		        		</form:label>
		        	</div>
		        	<div class="radio">	
		        		<form:label path="permissionId" class="form-control">
		        			<form:radiobutton  path="permissionId" value="3" /> Update
		        		</form:label>
		        	</div>	
		        	<div class="radio">		
		        		<form:label path="permissionId" class="form-control">
		        			<form:radiobutton path="permissionId" value="6" /> Delete
		        		</form:label>		
		        	</div>
		     </div>  	
			<div class="form-group">
						<button class="btn btn-success"  type="submit">Submit </button>
			</div>
		</form:form>
		
	</c:if>   
	</div>    
	</body>
	
</html>