<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>
        <title>Software Security </title>
        
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/bootstrapValidator.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/edit-profile.js"></script>
        
    </head>
    <body>
    <div class="row">
		
		<div class="col-md-4 col-xs-4 col-md-offset-1 col-xs-offset-1">
			<form:form role="form" id="edit-personal-form" method="post" action="/SecureBankingSystem/updatePersonalInfo/" commandName="personalInfo">
		        <div class='form-group'>
		        	<form:label path="firstName">First Name</form:label>
		            <form:input path="firstName" class="form-control" disabled="true" required="required" value="${personalInfo.firstName}" />
		        </div>
		            
		        <div class="form-group">
		        	<form:label path="middleName">Middle Name</form:label>
		            <form:input path="middleName" class="form-control" required="required" disabled="true" value="${personalInfo.middleName}" />
		        </div>
		        
		        <div class="form-group">
		        	<form:label path="lastName">Last Name</form:label>
		            <form:input path="lastName" class="form-control" disabled="true" required="required" value="${personalInfo.lastName}" />	
		        </div>
		        
		        <div class="form-group">
		        	<form:label path="lastName">Date of Birth</form:label>
		            <form:input path="lastName" class="form-control" disabled="true" required="required" value="${personalInfo.dob}" />	
		        </div>
		        
		        <div class="form-group">
		        	<form:label path="address">Address</form:label>
		            <form:input path="address"  class="form-control" required="required" value="${personalInfo.address}" /><form:errors style="color:red;" path="address" />
		        </div>
		        
		        <div class="form-group">
		        	<form:label path="phone">Phone</form:label>
		            <form:input path="phone" class="form-control" required="required" value="${personalInfo.phone}"/><form:errors style="color:red;" path="phone" />
		        </div>
		            
		        <div class="form-group">
		        	<form:label path="emailId">Email</form:label>
		            <form:input path="emailId" class="form-control" required="required" value="${personalInfo.emailId}" type="email"/><form:errors style="color:red;" path="emailId" />
		        </div>
		
		        <input  class="btn btn-info" type="submit" value="update"/>       
	    	</form:form>
		</div>
	</div>
</body>
</html>
