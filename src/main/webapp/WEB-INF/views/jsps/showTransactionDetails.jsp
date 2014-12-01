<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<body>
 <table class="table table-striped table-hover custom-table"><tr><td>
	${transaction.transactionId} ${transaction.transactionType.transactionTypeName} ${transaction.account.accountId} ${transaction.timestamp} ${transaction.amount}
 </td></tr></table>
</body>
</html>