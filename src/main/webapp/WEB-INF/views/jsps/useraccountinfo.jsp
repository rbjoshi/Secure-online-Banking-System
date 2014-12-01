<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>
        <title>Software Security </title>
    </head>
    <body>
    <c:if test="${not empty account}">
    	<div class="panel panel-default">
		  <!-- Default panel contents -->
		  <div class="panel-heading">Personal Information</div>
		  <div class="panel-body">
		    <p> This is the current piece of information that Devil's Bank helds for you. If you feel that, there are some inconsistencies
		    	then you can use edit profile page to make desired corretions.</p>
		  </div>

		  <!-- Table -->
		  <table class="table table-striped table-hover custom-table">
		  	<tr>
		  		<td><p>Account Number</p></td>
		  		<td>${account.accountId}</td>
		  	</tr>
		  	
		  	<tr>
		  		<td><p>Account Balance</p></td>
		  		<td>${account.balance}</td>
		  	</tr>
		  	
		  	<tr>
		  		<td><p>User Name</p></td>
		  		<td> ${account.user.userId}</td>
		  	</tr>
		  	
		  	<tr>
		  		<td><p>First Name</p></td>
		  		<td> ${account.user.personalInfo.firstName}</td>
		  	</tr>
		  	
		  	<tr>
		  		<td><p>Last Name</p></td>
		  		<td> ${account.user.personalInfo.lastName}</td>
		  	</tr>
		  	
		  	<tr>
		  		<td>Address</td>
		  		<td> ${account.user.personalInfo.address}</td>
		  	</tr>
		  	
		  	<tr>
		  		<td>Email Id</td>
		  		<td> ${account.user.personalInfo.emailId}</td>
		  	</tr>
		  	
		  	<tr>
		  		<td>Phone Number</td>
		  		<td>${account.user.personalInfo.phone}</td>
		  	</tr>
		  </table>
		</div>
			
	</c:if>
</body>
</html>