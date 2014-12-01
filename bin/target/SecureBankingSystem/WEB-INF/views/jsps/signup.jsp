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

<title>Devil's Bank | SignUp</title>


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
		<div>
		<form:form method="post" action="/SecureBankingSystem/add" commandName="user">
        <table>
        <tr>
            <td><form:label path="userId">User ID:</form:label></td>
            <td><form:input path="userId" /></td>
        </tr>
        <tr>
            <td><form:label path="password">Password:</form:label></td>
            <td><form:input path="password" type="password"/></td>
        </tr>
        
        <tr>
            <td><form:label path="password">Confirm Password:</form:label></td>
            <td><form:input path="password" type="password" /></td>
        </tr>
        
        <tr>
            <td><form:label path="personalInfo.firstName">First Name:</form:label></td>
            <td><form:input path="personalInfo.firstName"  /></td>
        </tr>
        
        <tr>
            <td><form:label path="personalInfo.middleName">Middle Name:</form:label></td>
            <td><form:input path="personalInfo.middleName"  /></td>
        </tr>
        <tr>
        	<td><form:label path="personalInfo.lastName">Last Name:</form:label></td>
            <td><form:input path="personalInfo.lastName"  /></td>
        </tr>
       
        <tr>
        	<td><form:label path="personalInfo.dob">Date of Birth(YYYY-MM-DD):</form:label></td>
            <td><form:input path="personalInfo.dob"  /></td>
         </tr>
         <tr>
        	<td><form:label path="personalInfo.address">Address:</form:label></td>
            <td><form:input path="personalInfo.address"  /></td>
         </tr>
        <tr>
        	<td><form:label path="personalInfo.phone">Phone:</form:label></td>
            <td><form:input path="personalInfo.phone"  /></td>
         </tr>
         <tr>
        	<td><form:label path="personalInfo.emailId">Email:</form:label></td>
            <td><form:input path="personalInfo.emailId" type="email"/></td>
         </tr>
         <tr>
        	<td><form:label path="personalInfo.ssn">SSN:</form:label></td>
            <td><form:input path="personalInfo.ssn"  /></td>
         </tr>
         <tr>
        	<td><form:label path="personalInfo.photo">Photo:</form:label></td>
            <td><form:input path="personalInfo.photo"  /></td>
         </tr>
        
        
         <tr>
        	<td><form:label path="roleName">Role:</form:label></td>
            <td><form:radiobutton path="roleName" value="admin" /> Administrator</td>
            <td><form:radiobutton path="roleName" value="emp" /> Employee</td>
            <td><form:radiobutton path="roleName" value="customer" /> Customer</td>
            <td><form:radiobutton path="roleName" value="merchant" /> Merchant</td>
         </tr>
       
        
        
         <tr>
            <td colspan="2">
                <input type="submit" value="add"/>
            </td>
        </tr>
    </table> 
    </form:form>
			<!-- <div class="contentleft">
				<form method="post">
					<label id="l_fname">First Name:</label><br/>
					<input type="text" id="f_name" name="firstname"/><br/>
					<label id="l_mname">Middle Name:</label><br/>
					<input type="text" id="m_name" name="middlename"/><br/>
					<label id="l_lname">Last Name:</label><br/>
					<input type="text" id="l_name" name="lastname"/><br/>
					<label id="l_uname">User Name:</label><br/>
					<input type="text" id="u_name" name="username"/><br/>
					<label id="l_pwd">Password:</label><br/>
					<input type="password" id="u_pwd" name="password"/><br/>
					<label id="l_cpwd">Confirm Password:</label><br/>
					<input type="password" id="u_cpwd" name="cpassword"/><br/>
					<label id="l_uname">Address:</label><br/>
					<textarea id="u_addr" name="address"></textarea><br/>
					<label id="l_dob">Date of Birth:</label><br/>
					<input type="text" id="dob" name="dateofbirth"/><br/>
					<label id="l_ssn">SSN:</label><br/>
					<input type="text" id="ssn" name="SSN"/><br/>
					<label id="l_contact">Contact Number:</label><br/>
					<input type="text" id="contact_no" name="contct"/><br/>
					<label id="l_lname">Role:</label><br/>
					<input type="radio"  name="role" value="Customer" />Customer
					<input type="radio"  name="role" value="Administrator" />Administrator
					<input type="radio"  name="role" value="Bank Employee" />Bank Employee<br/>
					<input type="submit" class="btn btn-primary" value="SignUp" id="signup" /><br/><br/>
					
				</form>
			</div>
			 -->
			<div class="contentright" style="background-color : #fff;">
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
