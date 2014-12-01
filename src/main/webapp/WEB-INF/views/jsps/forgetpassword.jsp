<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/bootstrapValidator.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/forgetpwd.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
    		<div class="col-md-3">
    			
    			<form id="otp-form" role="form" method="post" action="/SecureBankingSystem/newpassword">
				
					<div class="form-group">
						<label for="forOtp">Enter OTP:</label> 
	           			<input type="password" name="OTP" class="form-control" placeholder="Enter OTP here"/>
					</div>
					
					<div class="form-group">
						<button type="submit" class="btn btn-primary" value="Submit" id="submit">Submit</button>
					</div>
					
					
					<div class="form-group">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</div>
					
				</form>
    		</div>
		
		</div>
	</div>
</body>