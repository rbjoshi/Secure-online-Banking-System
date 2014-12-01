<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
<head>
	<title>Devil's Bank | Change Personal Information</title>
	<style>
		iframe {
			display: none;
		}
	</style>

	
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/bootstrapValidator.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/signup.js"></script>

</head>

<body>
	<div class="row">
	
		<div class="col-md-4 col-xs-4">
			<h1>Devil's Bank!</h1>
			<p>We provide you the best quality services. To get connected, you need to enter your details to enroll with us. Or login to access your details, if you are already enrolled.</p>
		</div>
		
		<div class="col-md-4 col-xs-4">
				<c:if test="${not empty error}">
					<div class="error">${error}</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
				</c:if>
		</div>
			
		<div class="col-md-6 col-xs-6 col-md-offset-1 col-xs-offset-1">
			<form:form role="form" method="post" action="/SecureBankingSystem/add" commandName="user">
		        <div class="form-group">
		        	<form:label path="userId">User ID:</form:label>
	            	<form:input class="form-control" path="userId" required="required" /><form:errors style="color:red;" path="userId" />
	            	
		        </div>
		        
		        <div class="form-group">
		        	<form:label path="firstName">First Name:</form:label>
		            <form:input class="form-control" path="firstName"  required="required" /><form:errors style="color:red;" path="firstName" />
		            
		        </div>
		        
		        <div class="form-group">
		        	<form:label path="middleName">Middle Name:</form:label>
		            <form:input class="form-control" path="middleName" required="required"/>
		        </div>
		        
		        <div class="form-group">
		        	<form:label path="lastName">Last Name:</form:label>
		            <form:input class="form-control" path="lastName" required="required" /><form:errors style="color:red;" path="lastName" />
		            
		        </div>
		        
		        <div class="form-group">
		        	<form:label path="dob">Date of Birth(YYYY-MM-DD):</form:label>
		            <form:input type="text" class="form-control" path="dob" required="required" />
		            
		        </div>
		        
		        <div class="form-group">
		        	<form:label path="address">Address:</form:label>
		            <form:input class="form-control"  path="address" required="required" /><form:errors style="color:red;" path="address" />
		            
		        </div>
		        
		        <div class="form-group">
		        	<form:label path="phoneNumber">Phone:</form:label>
		            <form:input class="form-control"  path="phoneNumber" required="required" /><form:errors style="color:red;" path="phoneNumber" />
		            
		        </div>
		            
		        <div class="form-group">
		        	<form:label path="emailId">Email:</form:label>
		            <form:input class="form-control" path="emailId" type="email" required="required"/><form:errors style="color:red;" path="emailId" />
		            
		        </div>   
		        
		        <div class="form-group">

		        	<form:label path="ssn">SSN:</form:label>
		            <form:input  class="form-control" type="password" path="ssn" required="required"/><form:errors style="color:red;" path="ssn" />
		            <br/>
		            <button id="show-ssn" class="btn btn-sm btn-danger">Show SSN</button>


		        </div>
		        
		        <!-- <div class="form-group">
		        	<form:label path="personalInfo.photo">Photo:</form:label>
		            <form:input  class="form-control" path="personalInfo.photo"  />
		        </div>  -->
		        
		        <div class="form-group">
		        	<form:label path="roleName">Role:</form:label>	
		        	<div class="radio">
		        		<label>
		        			<form:radiobutton path="roleName" value="customer" checked="checked"/> Customer	
		        		</label>
		        	</div>
		        	
		        	<div class="radio">
		        		<label>
		        			<form:radiobutton path="roleName" value="merchant" /> Merchant	
		        		</label>
		        	</div>
		        </div>
		        
		        <div class="form-group">
		        
		        </div>	
		        
		        <br/>
		        
				<div class="form-group">
					<button class="btn btn-success signup-submit" type="submit">Submit Account Request</button>
				</div>
		        

	    	</form:form>
		</div>
	</div>
</body>
</html>
