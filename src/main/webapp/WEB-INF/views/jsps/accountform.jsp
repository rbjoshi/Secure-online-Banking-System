<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>
        <title>Software Security </title>
    	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/bootstrapValidator.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/transaction.js"></script>
    </head>
    <body>
    <h2> Account Form </h2>
    <div class="btn btn-primary btn-sm">
		    ${account.user.userId}
	</div>
	<br/>
    <form:form id="transaction-form" method="post" action="/SecureBankingSystem/accountadd" commandName="account" >
    	<div class="form-group">
		    	<form:select class="form-control" path="accountType">
		            <form:option value="Checking" label="Checking" />
		            <form:option value="Saving" label="Saving" /> 
                </form:select>
		</div>
		<div class="form-group">
			<form:label path="balance">Balance</form:label></td>
            <form:input class ="form-control" path="balance" required="required"/>
         </div>
		<div class="form-group">
		    	<input type="submit" value="add"/>
		</div>
    </form:form>
    </body>
 </html>
    
    