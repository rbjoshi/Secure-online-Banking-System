<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="navbar navbar-inverse">

		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-responsive-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>

		<div class="navbar-collapse collapse navbar-responsive-collapse">
			<c:choose>
				<c:when test="${pageContext.request.userPrincipal.name != null}">
					<a href="<%=request.getContextPath()%>/dashboard"><img id="logo-img" src="resources/assets/img/toplogo.png"/></a>
				</c:when>
				<c:otherwise>
					<a href="<%=request.getContextPath()%>/"><img id="logo-img" src="resources/assets/img/toplogo.png"/></a>	
				</c:otherwise>
			</c:choose>
			
			<ul class="nav navbar-nav navbar-right padded-nav">
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<li><a class="btn btn-default btn-sm" href="<%=request.getContextPath()%>/dashboard">${pageContext.request.userPrincipal.name}'s DashBoard</a></li>
					<li><a class="btn btn-default btn-sm" href="javascript:formSubmit()"> Logout</a></li>
				</c:if>
			</ul>
		</div>
		
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
		<!-- /.nav-collapse -->

</div>