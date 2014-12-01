<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>
        <title>Software Security </title>
    <link href="<%=request.getContextPath()%>/resources/assets/css/bootstrap-united.css" rel="stylesheet" />
	<link href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.css" rel="stylesheet" />
    </head>
    <body>
    <c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
 	
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Hello user : ${pageContext.request.userPrincipal.name} | <a
				href="javascript:formSubmit()"> Logout</a>
		</h2>
	</c:if>
	
	
    </body>
    
 </html>