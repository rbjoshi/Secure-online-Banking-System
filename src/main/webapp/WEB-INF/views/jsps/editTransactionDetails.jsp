<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>
        <title>Software Security </title>
    </head>
    <body>
    <div class="container">
		<div>
		<form:form method="post" action="/SecureBankingSystem/transaction_maintanence_update" commandName="transaction">
        <table class="table table-striped table-hover custom-table"><tr><td>
	
	       <tr>
	       <td>
	           ${transaction.transactionType.transactionTypeName}
	        </td>
	        
	        <td>
	            ${transaction.account.accountId}
	        </td>
	        
	        <td>
	      		${transaction.amount}
	        </td>
	        </tr>
	        
	        <br/>
	        <tr>
	        	<td><form:label path="status">Status </form:label></td>
	            <td>
	            	<div class="col-md-3">	            	
	            		<form:select class="form-control" path="status" required="required">	            		
		            		<form:option value="C" label="Complete" />
		            		<form:option value="R" label="Review" /> 
               			 </form:select>
	            	</div>
	            </td>
	        </tr>
         <tr>
            <td colspan="2">
                <input type="submit"  class="btn btn-success" value="update"/>
            </td>
        </tr>
    </table> 
    </form:form>
	</div>
	
	</div>		
		

</body>
</html>
