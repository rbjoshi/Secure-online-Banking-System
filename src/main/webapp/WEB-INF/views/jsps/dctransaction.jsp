<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>
        <title>Software Security </title>
 		
 		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/bootstrapValidator.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/transaction.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/validator.js"></script>
    </head>
    <body>
    <h2> Debit Credit Transaction </h2>
    <c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
	</c:if>
    
    <div class="row">
    	
    	<div class="col-md-3 col-xs-3">
    		<form:form id="transaction-form" method="post" action="/SecureBankingSystem/dcadd" commandName="transaction" >
		    	<div class="form-group">
		    		<form:label path="amount">Amount</form:label>
		            <form:input class="form-control" path="amount" required="required"/>
		    	</div>
		        
				<div class="form-group">
					<form:label path="transactionTypeName">Type</form:label>
		            <div class="radio">
		        		<label>
		        			<form:radiobutton class="radio" path="transactionTypeName" checked="checked" value="debit"/> Debit	
		        		</label>
		       		</div>	
		            
		            <div class="radio">
		        		<label>
		        			<form:radiobutton class="radio" path="transactionTypeName" value="credit"/> Credit	
		        		</label>
		        	</div>
		            
				</div>
		        <input class="btn btn-success" type="submit" value="add"/>
    
   			</form:form>	
    	</div>
    
    </div>
    
   </body>
   </html>
   