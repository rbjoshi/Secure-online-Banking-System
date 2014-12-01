<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>
        <title>Software Security </title>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/bootstrapValidator.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/pii-select.js"></script>
		<link href="<%=request.getContextPath()%>/resources/assets/css/pii.css" rel="stylesheet" />
    </head>
    <body>
    <div class="loader">
    	<img id='loading-img' src="<%=request.getContextPath()%>/resources/assets/img/loader.gif" />
    	<p id="info-text">Getting permissions from Government</p>
    </div>
	<c:if test="${not empty authorizations}">
	<div class = "container">
	
	<form:form  id="get-pii-form" method="post" action="/SecureBankingSystem/account_pii_maintanence" commandName="placeholder">     	  
		
		
		
		<div class="form-group">	
				<form:label path="placeholderstring">Account Number:</form:label>
				<form:select path="placeholderstring"> 
				   <c:forEach items="${authorizations}" var="authorization">
 						<form:option value="${authorization.authorizationId}"> ${authorization.account.accountId} </form:option>
 					</c:forEach>
				</form:select>
		</div>	
		<div class="form-group">
					<button class="btn btn-danger" id="get-pii" type="submit"> Get PII for user from government </button>
		</div>
	</form:form>
	</div> 
	</c:if> 
	</body>
	
</html>
