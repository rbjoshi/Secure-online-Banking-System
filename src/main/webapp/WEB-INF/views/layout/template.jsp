<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width" />
<link href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/bootstrap/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/main.js"></script>
<link href="<%=request.getContextPath()%>/resources/assets/css/main.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/assets/css/bootstrapValidator.min.css" rel="stylesheet" />
<c:set var="titleKey">
    <tiles:insertAttribute name="title" ignore="true" />
</c:set>
<title><spring:message code="${titleKey}" text="Devil's Bank" /></title>

</head>
<body>
	<tiles:insertAttribute name="header" />
	<div id="wrap">
		<div id="main" class="container clear-top">
        	<tiles:insertAttribute name="body" />
    	</div>
	</div>
    <tiles:insertAttribute name="footer" />
</body>
</html>
