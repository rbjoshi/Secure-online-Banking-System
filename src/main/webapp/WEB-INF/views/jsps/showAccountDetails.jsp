<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<body>
	${account.balance} ${account.user.userId} ${account.user.personalInfo.firstName} ${account.user.personalInfo.lastName} ${account.user.personalInfo.address} ${account.user.personalInfo.emailId} ${account.user.personalInfo.phone}
</body>
</html>