<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<title>Devil's Bank | Home</title>

</head>
<body>
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
				<form method="get" action="login">
					<!--  <label id="l_uname">User Name:</label><br/>
					<input type="text" id="u_name" name="username"><br/>-->
					<input type="submit" class="btn btn-primary" value="LogIn" id="login" /><br/><br/>
					 <a class="btn btn-primary" href="login">check </a><br/><br/>
					<a class="btn btn-primary" href="signup">Enroll </a>
				</form>
					
			</div>
			<div class="contentright">
				<h1>Welcome to Devil's Bank!</h1>
				<p>We provide you the best quality services. To get connected, you need to enter your details to enroll with
					us. Or login to access your details, if you are already enrolled.</p>
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
