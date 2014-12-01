<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>
        <title>Software Security </title>
     </head>
    <body>
	
	<div class="container">
	
 		      	  
	<form:form  method="post" action="/SecureBankingSystem/acc_auth_add" commandName="authorization">     	  
		
		
		
		<div class="form-group">	
				<form:label path="empId">Administrator:</form:label>
				<form:select path="empId"> 
				   <c:forEach items="${administrators}" var="admin">
 						<form:option value="${admin.userId}"> ${admin.userId} </form:option>
 					</c:forEach>
				</form:select>
		</div>	
		<div class="form-group">
		        	<form:label path="permissionId">Permissions:</form:label>
		        	
		        	<div class="radio">
		        		<label>
		        			<form:radiobutton path="permissionId" checked="checked" value="4" /> Read		
		        		</label>
		        	</div>
		        	
		        	<div class="radio">
		        		<label>
		        			<form:radiobutton path="permissionId" value="3" /> Update	
		        		</label>
		        	</div>
		        	
		        	<div class="radio">
		        		<label>
		        			<form:radiobutton path="permissionId" value="6" /> Delete	
		        		</label>
		        	</div>
		     </div> 
		<div class="form-group">
					<button class="btn btn-success" type="submit">Submit </button>
		</div>
	</form:form>
	</div> 
	</body>
	
</html>