<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>
        <title>Software Security </title>
        
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/bootstrapValidator.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/edit-critical-info.js"></script>
        
    </head>
    <body>
    <div class="row">
	
		<div class="col-md-4 col-cs-4">
			<h1>Critical PII Update Form!</h1>
			<p>As an Admin you have the responsibility to update the critical public information of users. These page allows you to update the
				first name, last name, ssn and date of birth of users. You have been requested to do these changes. Please read the comments
				and make desired changes only.</p>
		</div>
		
		<div class="col-md-4 col-xs-4 col-md-offset-1 col-xs-offset-1">
			<form:form role="form" id="edit-personal-form" method="post" action="/SecureBankingSystem/account_pii_maintanence_update" commandName="personalinfo">
		        <div class='form-group'>
		        	<form:label path="firstName">First Name</form:label>
		            <form:input path="firstName" required="required" class="form-control"  value="" /><form:errors style="color:red;" path="firstName" />
		        </div>
		            
		        <div class="form-group">
		        	<form:label path="middleName">Middle Name</form:label>
		            <form:input path="middleName" required="required" class="form-control"  value="" /><form:errors style="color:red;" path="middleName" />
		        </div>
		        
		        <div class="form-group">
		        	<form:label path="lastName">Last Name</form:label>
		            <form:input path="lastName" required="required" class="form-control"  value="" /><form:errors style="color:red;" path="lastName" />	
		        </div>
		        
		        
		        
		        <div class="form-group">
		        	<form:label path="ssn">SSN</form:label>
		            <form:input required="required" class="form-control" type="password" path="ssn"/><form:errors style="color:red;" path="ssn" /><form:errors style="color:red;" path="ssn" />
		            <br/>
		            <button id="show-ssn" class="btn btn-sm btn-danger">Show SSN</button>
		        </div>
		        
		        <div class="form-group">
		        	<label>Comments From User</label>
					<textarea class="form-control" name="comments" rows="3" disabled>${comments }</textarea>
					<br/>
					<p> Please hit update button directly, if you don't want to perform the requested changes. This would be considered as REJECT</p>
		        </div>
			
		        <input  class="btn btn-info" type="submit" value="update"/>       
	    	</form:form>
		</div>
	</div>
</body>
</html>
