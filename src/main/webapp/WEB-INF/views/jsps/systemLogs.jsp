<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>
        <title>Software Security </title>
 		
    </head>
    <body>
    <div class="row col-md-offset-5">
    	<div  class="col-md-12">
				<h2>System Logs!</h2>
		</div>
		<c:if test="${fileNames != null}">
			<c:forEach var="fileName" items="${fileNames}">
				<div class="span6">
					<a class="btn btn-info"
						href="\SecureBankingSystem\donwload-system-log?filename=${fileName}">${fileName}</a>
				</div>
				<br />
			</c:forEach>
		</c:if>
	</div>
    
   </body>
   </html>
   