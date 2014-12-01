<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>
        <title>Software Security </title>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/bootstrapValidator.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/account-pii-auth.js"></script>
     </head>
    <body>
	
	<div class="container">
	<c:choose>
		<c:when test="${not empty authorization}">
			<form:form  id="comment-form" method="post" action="/SecureBankingSystem/acc_auth_pii_add" commandName="authorization">     	  
	
			<div class="form-group">	
					<form:label path="empId">Administrator:</form:label>
					<form:select path="empId"> 
					   <c:forEach items="${administrators}" var="admin">
	 						<form:option value="${admin.userId}"> ${admin.userId} </form:option>
	 					</c:forEach>
					</form:select>
			</div>	
			
			<div class="form-group">
				<form:label path="comments">Comments:</form:label>
				<textarea class="form-control" name="comments" rows="3"></textarea> 
				
			</div>
			<div class="form-group">
						<button class="btn btn-success" type="submit">Submit </button>
			</div>
			</form:form>
		</c:when>
		<c:otherwise>
			<div class="row">
				<h3> Your previous request for critical personal information change is still pending</h3>
			</div>
		</c:otherwise>
	</c:choose>
	
 		      	  
	
	</div> 
	</body>
	
</html>