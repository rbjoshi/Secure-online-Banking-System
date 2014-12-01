<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>
        <title>Log Viewer</title>
    </head>
    <body>
    <div class="panel panel-default">
		  <!-- Default panel contents -->
		  <div class="panel-heading"><h2>Logs : ${date}</h2></div>
		  <!-- Table -->
		<c:if test="${content != null}">
			<table class="table table-striped table-hover custom-table">
				<c:forEach var="line" items="${content}">  					
				<tr>
					<td>${line}</td>
				</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>   
   </body>
   </html>
   