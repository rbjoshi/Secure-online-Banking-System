<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Devil's Bank | Home</title>

	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/bootstrapValidator.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/newpassword.js"></script>
</head>
<body>
	<div class="container">
		<div class="holediv">
		
			<div class="contentleft col-md-4">
				<form method="post" id="password-change" action="/SecureBankingSystem/change-forget-password">
					<div class="form-group">
						<label id="l_npass">Enter New Password:</label><br/>
						<input class="form-control" type="password" id="n_pass" name="newpassword"><br/>
					</div>
					<div class="form-group">
						<label id="l_cnpass">Confirm Password:</label><br/>
						<input class="form-control" type="password" id="n_cpass" name="newcpassword"><br/>
					</div>
					<div class="form-group">
						<input type="submit" class="btn btn-primary" value="Submit" id="submit_pass" /><br/><br/>
					</div>
					<div class="form-group">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</div>
				
				</form>
			</div>
			
		</div>

		<div></div>
	</div>
</body>
</html>
