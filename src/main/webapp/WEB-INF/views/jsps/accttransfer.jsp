<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>
        <title>Software Security </title>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/bootstrapValidator.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/transfer.js"></script>
    </head>
    <body>
    <h2> Transfer </h2>
    <form:form id="transfer-form" method="post" action="/SecureBankingSystem/accttransfer" commandName="transaction" >
    	<div class="form-group">
			<form:label path="amount">Amount:</form:label>
            <form:input class="form-control" path="amount" />
         </div>
         
         <div class="form-group">
			<form:label path="toAccountId">To Account:</form:label>
           	<form:input class="form-control" path="toAccountId" />
         </div>
         
         <div class="form-group">
			<input class="btn btn-success" type="submit" value="Transfer"/>
         </div>
    
   </form:form>
   </body>
   </html>
   