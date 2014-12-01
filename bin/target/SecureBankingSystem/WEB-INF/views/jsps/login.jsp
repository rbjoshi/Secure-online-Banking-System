<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true"%>
<html>
<head>
<link href="<%=request.getContextPath()%>/resources/assets/css/bootstrap-united.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap-responsive.css" rel="stylesheet" />

<style>
body {
	height: 100%;
	margin: 0;
	background: url(assets/img/books.jpg);
	background-size: 1440px 800px;
	background-repeat: no-repeat;
	display: compact;
}
</style>

<title>Devil's Bank | LogIn</title>
</head>
<body  onload='document.loginForm.username.focus();'>
	<div class="navbar navbar-default">

		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-responsive-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>

		<div class="navbar-collapse collapse navbar-responsive-collapse">
			<img src="resources/assets/img/toplogo.png"/>
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="#">Home</a></li>
				<li><a href="#">Services</a></li>
				<li><a href="#">Contact Us</a></li>
				<li><a href="#">About Us</a></li>
				<li><a href="#">FAQ</a></li>
				
			</ul>
		</div>
		<!-- /.nav-collapse -->
	</div>
	<div class="container">
		<div class="holediv">
		
			<div class="contentleft">
			<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
				<form name='loginForm'
		  action="<c:url value='/j_spring_security_check' />" method='POST'>
 
		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='username'></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
				  value="LogIn" class="btn btn-primary"/></td>
			</tr>
		  </table>
 
		  <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
 
		</form>
			</div>
			<div class="contentright">
				<p>
					If you forget your password then there is not to worry. Click the link available below to change your password. 
				</p>
				<a href="forgetpassword">Forget password ? </a>
			</div>

		</div>

		<div></div>
	</div>
	<script src="jquery-1.8.3.js">
</script>

	<script src="bootstrap/js/bootstrap.js">
</script>

</body>
</html>
