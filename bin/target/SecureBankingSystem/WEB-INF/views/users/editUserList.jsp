
 <h2>Software Security</h2>
 <div class="col-sm-12">
	 <form:form method="post" action="add" commandName="user">
	     <table class="table table-striped table-bordered table-condensed">
	     <tr>
	         <td><form:label path="userName">User Name</form:label></td>
	         <td><form:input path="userName" />Password</td>
	     </tr>
	     <tr>
	         <td><form:label path="password"></form:label></td>
	         <td><form:input path="password" /></td>
	     </tr>
	           <tr>
	         <td colspan="2">
	             <input type="submit" value="add"/>
	         </td>
	     </tr>
	 </table> 
	 </form:form>
	 <h3>Users</h3>
	 <c:if  test="${!empty userList}">
	 <table class="data">
	 <tr>
	     <th>Name</th>
	     <th>
	 </tr>
	 <c:forEach items="${userList}" var="u">
	     <tr>
	         <td>${u.userName}</td>
	         <td>${u.password}</td>
	        
	         <td>delete</td>
	     </tr>
	 </c:forEach>
	 </table>
	</c:if>
 </div>
 