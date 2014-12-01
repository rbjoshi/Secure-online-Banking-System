<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width" />
<link href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/bootstrap/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/main.js"></script>
<link href="<%=request.getContextPath()%>/resources/assets/css/main.css" rel="stylesheet" />
<body>
	<div class="navbar navbar-inverse">

		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-responsive-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>

		<div class="navbar-collapse collapse navbar-responsive-collapse">
			<a href="<%=request.getContextPath()%>"><img id="logo-img" src="resources/assets/img/toplogo.png"/></a>
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
		<div class="row col-md-12 col-xs-12">
			<a href="<%=request.getContextPath() %>">
				<img id="error-img" src="<%=request.getContextPath()%>/resources/assets/img/error.png"></img>
			</a>
		</div>		
	</div>
	
	<div class="footer">
		<nav class="navbar navbar-inverse footer-nav" role="navigation">
		  <div class="container-fluid text-center">
		  	<p class="text-white">Copyright &copy; 2014 Devil's Bank</p>
		  </div>
		</nav>
	</div>
</body>
</html>
