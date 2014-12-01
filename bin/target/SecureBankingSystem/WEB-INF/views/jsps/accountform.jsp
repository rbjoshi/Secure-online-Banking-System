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
    <h2> Account Form </h2>
    
    <form:form method="post" action="/SecureBankingSystem/accountadd" commandName="account" >
    <table>
    	 <tr>
            <td><form:label path="accountId">Account ID:</form:label></td>
            <td><form:input path="accountId" /></td>
        </tr>
      hello ${account.user.userId}
        <tr>
            <td><form:label path="accountType">Account Type:</form:label></td>
            <td><form:input path="accountType" /></td>
        </tr>
        <tr>
            <td><form:label path="balance">Balance</form:label></td>
            <td><form:input path="balance" /></td>
        </tr>
        <form:label path="userId">userId</form:label></td>
        <form:input path="userId" value="${account.user.userId}" />
        <tr>
            <td colspan="2">
                <input type="submit" value="add"/>
            </td>
        </tr>
    </table>
    </form:form>
    </body>
 </html>
    
    