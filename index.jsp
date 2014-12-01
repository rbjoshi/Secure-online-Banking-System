<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- <link href="request.getContextPath()%>/resources/assets/css/bootstrap-united.css" rel="stylesheet" />
<link href="request.getContextPath()%>/resources/bootstrap/css/bootstrap-responsive.css" rel="stylesheet" />
-->
<!DOCTYPE HTML>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!--Google Fonts-->
<link href='http://fonts.googleapis.com/css@family=Open+Sans_3A400,300,300italic,400italic,600,600italic,700,700italic' rel='stylesheet' type='text/css'>

<!-- *************************************************************************
**************************   STYLE SHEET   *******************************
************************************************************************** -->

<link href="resources/assets/css/main.css" rel="stylesheet" type="text/css" />
<link href="resources/assets/css/jquery.fancybox.css" rel="stylesheet" type="text/css" />


<title>Devil's Bank</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">


 <!-- *************************************************************************
*****************                FAVICON               ********************
************************************************************************** -->

<link rel="shortcut icon" href="img/favicon.png" />




<!-- *************************************************************************
**************************        JS       *******************************
************************************************************************** -->
<script type="text/javascript" src="resources/assets/js/jquery.js"> </script>
<script type="text/javascript" src="resources/assets/js/jquery.flexslider-min.js"> </script>
<script type="text/javascript" src="resources/assets/js/jquery.easing.1.3.js"> </script>
<script type="text/javascript" src="resources/assets/js/hoverIntent.js"> </script>
<script type="text/javascript" src="resources/assets/js/jquery.sfmenu.js"> </script>
<script type="text/javascript" src="resources/assets/js/retina.js"> </script>
<script type="text/javascript" src="resources/assets/js/custom.js"> </script>
<script type="text/javascript" src="resources/assets/js/jquery.fancybox.js"> </script>



<!-- *************************************************************************
******************     THESE ARE THE SCRIPT CALLS    ***********************
************************************************************************** -->
<script type="text/javascript">
jQuery(window).load(function() {
	
jQuery('.slider').flexslider();
 
// Create the dropdown base
jQuery("<select />").appendTo(".topmenu");

// Create default option "Go to..."
jQuery("<option />", {
 "selected": "selected",
 "value"   : "",
 "text"    : "Menu"
}).appendTo(".topmenu select");

// Populate dropdown with menu items
jQuery(".topmenu a").each(function() {
var el = jQuery(this);
jQuery("<option />", {
   "value"   : el.attr("href"),
   "text"    : el.text()
}).appendTo(".topmenu select");
});

// To make dropdown actually work
// To make more unobtrusive: http://css-tricks.com/4064-unobtrusive-page-changer/
jQuery(".topmenu select").change(function() {
window.location = jQuery(this).find("option:selected").val();
});

jQuery('.fancybox').fancybox();	
	
});
	
</script>

</head>

<body>

<!-- Start of top wrapper -->
<div id="top_wrapper">

<!-- Start of content wrapper -->
<div class="content_wrapper">

</div>
<!-- End of content wrapper -->

<!-- Clear Fix --><div class="clear"></div>

</div><!-- End of top wrapper -->

<!-- Start of header wrapper -->
<div id="header_wrapper">

<!-- Start of content wrapper -->
<div class="content_wrapper">

<!-- Start of logo -->
<div id="logo">
<a href="#"><img src="resources/assets/img/toplogo.png" width="213" height="33" alt="Business Essentials" /></a>

</div><!-- End of logo -->

<!-- Start of top menu wrapper -->
<div class="topmenuwrapper">

<!-- Start of topmenu -->
<nav class="topmenu"> 
 
<ul class="sf-menu">

	<li><a href="index.jsp">Home</a></li>
	        
	<li><a href="#">Services</a></li>
	
	<li><a href="#">Contact Us</a></li>
	
	<li><a href="#">About Us</a></li>
	
	<li><a href="#">FAQ</a></li>

</ul>

</nav><!-- End of topmenu -->

<!-- Start of header phone -->
<div class="header_phone">
Toll Free: 1800 4804
</div><!-- End of header phone -->

<!-- Clear Fix --><div class="clear"></div>

</div><!-- End of top menu wrapper -->

</div><!-- End of content wrapper -->

<!-- Clear Fix --><div class="clear"></div>

</div><!-- End of header wrapper -->

<!-- Start of message wrapper -->
<div id="message_wrapper">

<!-- Start of content wrapper -->
<div class="content_wrapper">

<!-- Start of contentleft -->
<div class="contentleft">
	

</div>
<!-- End of contentleft -->

<!-- Start of contentright -->
<div class="contentright">


</div>
<!-- End of contentright -->

</div><!-- End of content wrapper -->

<!-- Clear Fix --><div class="clear"></div>

</div><!-- End of message wrapper -->

<!-- Start of content wrapper -->
<div id="contentwrapper">

<!-- Start of content wrapper -->
<div class="content_wrapper">

<div class="contentleft" style="width: 45%">
	<form method="post" action="#">
		User Name:<br/>
		<input type="text" name="username" id="u_name" /><br/>
		<input type="submit" class="button_green_image" name="login" id="log_in" value="LogIn" /><br/>
		<div class="button_blue">
			<a href="register.jsp">Enroll</a>
		</div>
	</form>

</div>
<div class="contentright" style="width: 45%">
	
		<h2 style="margin-left: 5%">Welcome TO Devil's Bank</h2><br/>
		<h3 style="margin-left: 5%">We provide you the best facilities. We give you the best services.</h3>
	
</div>

<div class="clear"></div>




</div><!-- End of content wrapper -->

<!-- Clear Fix --><div class="clear"></div>

</div><!-- End of content wrapper -->

<!-- Start of bottom wrapper -->
<div id="bottom_wrapper">

<!-- Start of content wrapper -->
<div class="content_wrapper">

<!-- Start of one fourth first -->
<div class="one_fourth_first">
<h5>Company</h5>

<ul>
<li><a href="#">Home</a></li>
<li><a href="#">About Us</a></li>

</ul>

</div><!-- End of one fourth first -->

<!-- Start of one fourth -->
<div class="one_fourth">
<h5><a href="#">FAQ</a></h5>

</div><!-- End of one fourth -->

<!-- Start of one fourth -->
<div class="one_fourth">
<h5>Get in touch</h5>

<ul>
<li><a href="#">Contact Us</a></li>
</ul>

</div><!-- End of one fourth -->

</div><!-- End of content wrapper -->

<!-- Clear Fix --><div class="clear"></div>

</div><!-- End of bottom wrapper -->

<!-- Start of copyright wrapper -->

</body>
</html>
