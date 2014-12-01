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
    <div class="row">
    	
    	<div class="col-md-3 col-xs-3">
    		<form:form id="transaction-form" method="post" action="/SecureBankingSystem/transaction_create_finish" commandName="transaction" >
		    	<div class="form-group">
		    		<form:label path="amount">Amount</form:label>
		            <form:input class="form-control balance" required="required" path="amount" />
		    	</div>
		        
				<div class="form-group">
					<form:label path="transactionTypeName">Type</form:label>
		            <div class="radio">
		        		<label>
		        			<form:radiobutton class="radio" path="transactionTypeName" value="debit"/> Debit	
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
   