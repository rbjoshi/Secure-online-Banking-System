<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>
        <title>Software Security </title>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/bootstrapValidator.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/editAccountInfo.js"></script>
    </head>

    <body>
    <div class = "col-md-6 col-xs-6">
	
    
    <form:form id="transaction-form" method="post" action="/SecureBankingSystem/account_maintanence_update" commandName="account" >
    	<div class="form-group">
		    	<form:label path="accountType">Account Type:</form:label>
		        <select name="accountType">
				    <option value="Checking">Checking</option>
				    <option value="Saving">Saving</option>
				 </select>
		</div>
       
       	<div class="form-group">
		    	<form:label path="balance">Balance</form:label>
		        <form:input class="form-control" path="balance" value="${account.balance}" />
		</div>
        
        <div class="form-group">
		    	<form:label path="address">Address</form:label>
		    	<form:input class="form-control" path="address" required="required" value="${account.user.personalInfo.address}" /><form:errors style="color:red;" path="address" />
		</div>
      
      	<div class="form-group">
		    	<form:label path="phone">Phone</form:label>
		    	<form:input class="form-control" path="phone" required="required" value="${account.user.personalInfo.phone}" /><form:errors style="color:red;" path="phone" />
		</div>
      
      	<div class="form-group">
		    	<form:label path="emailId">Email</form:label>
		    	<form:input type="email" class="form-control" path="emailId" required="required" value="${account.user.personalInfo.emailId}" /><form:errors style="color:red;" path="emailId" />
		</div>
     
        <div class="form-group">
        		<input type="submit" class="btn btn-success" value="update account"/>		    	
		</div> 
    </form:form>
    </div>
    </body>
 </html>
    
    