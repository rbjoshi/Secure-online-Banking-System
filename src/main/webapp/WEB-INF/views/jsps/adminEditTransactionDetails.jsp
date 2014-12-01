<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>
        <title>Software Security </title>
    <link href="<%=request.getContextPath()%>/resources/assets/css/bootstrap-united.css" rel="stylesheet" />
	<link href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.css" rel="stylesheet" />
    </head>
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
 
	<div class="container">
		<div>
		<form:form method="post" action="/SecureBankingSystem/admin_transaction_edit_finish" commandName="transaction">
        <table class="table table-striped table-hover custom-table">
		
        <tr><td>
           ${transaction.transactionType.transactionTypeName}
        </td></tr>
        
        <tr><td>
            ${transaction.account.accountId}
        </td></tr>
        
        <tr><td>
      		${transaction.amount}
        </td></tr>
        
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
