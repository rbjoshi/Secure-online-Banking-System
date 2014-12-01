<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>

<html>
<head>
	<title>Devil's Bank | SignUp</title>
	<style>
		iframe {
			display: none;
		}
	</style>
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
			
		<div class="col-md-6 col-xs-6">
			<form:form role="form" method="post" action="/SecureBankingSystem/add" commandName="user">
		       		        
		        <div class="form-group">
		        	<form:label path="personalInfo.address">Address:</form:label>
		            <form:input class="form-control"  path="personalInfo.address"  /><form:errors path="personalInfo.address" />
		        </div>
		        
		        <div class="form-group">
		        	<form:label path="personalInfo.phone">Phone:</form:label>
		            <form:input class="form-control"  path="personalInfo.phone"  /><form:errors path="personalInfo.phone" />
		        </div>
		            
		        <div class="form-group">
		        	<form:label path="personalInfo.emailId">Email:</form:label>
		            <form:input class="form-control" path="personalInfo.emailId" type="email"/><form:errors path="personalInfo.emailId" />
		        </div>   
		        
		  
		        
		       
		        
		        <div class="form-group">
		        	<% ReCaptcha c = ReCaptchaFactory.newReCaptcha("6Ldmo_wSAAAAABzrBU3ENitM8h0hQ2KPNLfbBqhW", 
																	 "6Ldmo_wSAAAAAC7I-zSIdDdzticlMqElXePIuaKH", 
																	 false);
						out.print(c.createRecaptchaHtml(null, null));
					  %>
		        </div>	
		        
		        <br/>
		        
				<div class="form-group">
					<button class="btn btn-success" type="submit">Submit Change</button>
				</div>
		        

	    	</form:form>
		</div>
	</div>
</body>
</html>
>