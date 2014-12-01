<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>

<head>
	<title>Devil's Bank Home</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/bootstrapValidator.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/index.js"></script>
	<link href="<%=request.getContextPath()%>/resources/assets/css/keyboard_style.css" rel="stylesheet" />
</head>

<body>
	<div class="container">
		<div class="row">

			<div class="col-md-4 col-xs-4">
				<h1>Welcome to Devil's Bank!</h1>
				<p>We provide you the best quality services. To get connected,
					you need to enter your details to enroll with us. Or login to
					access your details, if you are already enrolled.</p>
					<br/>
				<div class="form-group">
					<a class="btn btn-primary" href="signup">Not a Customer Yet? Click here to enroll! </a>
				</div>
			</div>

			<div class="col-md-8 col-xs-8">
				<c:if test="${not empty error}">
					<div class="error">${error}</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
				</c:if>
			</div>
			
			<br/>
			
			<div class="col-md-8 col-xs-8">
				<form role="form" method="post" autocomplete="off"	action="/SecureBankingSystem/usernameauth" id="login-form">
					<div class="row" >
						<div class="form-group col-md-4 col-xs-4">
							<input class="form-control" type="text" id="username" name="username" placeholder="User Name" required>
						</div>
					</div>
					

					<!-- <div class="form-group">
						<input class="form-control" type="text" id="password"
							name="password" placeholder="Password" required>
					</div> -->

					<div class="form-group" id = "terms-and-conditions">
						<input type="checkbox" id="terms-cond" name="termsAndCondition[]"/> I have read the
						<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModal">
  							Terms and Conditions
						</button>
						<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						  	<div class="modal-dialog">
							    <div class="modal-content">
								      <div class="modal-header">
								        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
								        <h4 class="modal-title" id="myModalLabel">Terms and Conditions</h4>
								      </div>
								      <div class="modal-body">
								         1. Admin is allowed to view your personal info.<br/>
								         2. Normal employee is allowed to do what he is authorized for.<br/>
								         3. Your personal info will never shared with anyone and is safe with us.<br/>
								         4. Sensitive info like password can't be seen by anybody.<br/>								
								      </div>
								      <div class="modal-footer">
								        Thank you..!!
								        <br/> Devil's Bank
								      </div>
							    </div>
						  </div>
						</div>
					</div>
					
					<div class="form-group">
						<input type="submit" class="btn btn-success" value="LogIn"
							id="login" />
						<br />
					</div>					
					<div class="form-group">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</div>

				</form>
			
				<br/>	
				<button class="btn btn-info" id ="virtualKeyboardLink">Show Virtual Keyboard</button>
				<br/>
				<br/>
				
				<div id="virtualKeyboardDiv">
				    <ul id="keyboard">
				        <li class="symbol"><span class="off">`</span><span class="on">~</span></li>
				        <li class="symbol"><span class="off">1</span><span class="on">!</span></li>
				        <li class="symbol"><span class="off">2</span><span class="on">@</span></li>
				        <li class="symbol"><span class="off">3</span><span class="on">#</span></li>
				        <li class="symbol"><span class="off">4</span><span class="on">$</span></li>
				        <li class="symbol"><span class="off">5</span><span class="on">%</span></li>
				        <li class="symbol"><span class="off">6</span><span class="on">^</span></li>
				        <li class="symbol"><span class="off">7</span><span class="on">&amp;</span></li>
				        <li class="symbol"><span class="off">8</span><span class="on">*</span></li>
				        <li class="symbol"><span class="off">9</span><span class="on">(</span></li>
				        <li class="symbol"><span class="off">0</span><span class="on">)</span></li>
				        <li class="symbol"><span class="off">-</span><span class="on">_</span></li>
				        <li class="symbol"><span class="off">=</span><span class="on">+</span></li>
				        <li class="delete lastitem">delete</li>
				        <li class="tab">tab</li>
				        <li class="letter">q</li>
				        <li class="letter">w</li>
				        <li class="letter">e</li>
				        <li class="letter">r</li>
				        <li class="letter">t</li>
				        <li class="letter">y</li>
				        <li class="letter">u</li>
				        <li class="letter">i</li>
				        <li class="letter">o</li>
				        <li class="letter">p</li>
				        <li class="symbol"><span class="off">[</span><span class="on">{</span></li>
				        <li class="symbol"><span class="off">]</span><span class="on">}</span></li>
				        <li class="symbol lastitem"><span class="off">\</span><span class="on">|</span></li>
				        <li class="capslock">caps lock</li>
				        <li class="letter">a</li>
				        <li class="letter">s</li>
				        <li class="letter">d</li>
				        <li class="letter">f</li>
				        <li class="letter">g</li>
				        <li class="letter">h</li>
				        <li class="letter">j</li>
				        <li class="letter">k</li>
				        <li class="letter">l</li>
				        <li class="symbol"><span class="off">;</span><span class="on">:</span></li>
				        <li class="symbol"><span class="off">'</span><span class="on">&quot;</span></li>
				        <li class="return lastitem">return</li>
				        <li class="left-shift">shift</li>
				        <li class="letter">z</li>
				        <li class="letter">x</li>
				        <li class="letter">c</li>
				        <li class="letter">v</li>
				        <li class="letter">b</li>
				        <li class="letter">n</li>
				        <li class="letter">m</li>
				        <li class="symbol"><span class="off">,</span><span class="on">&lt;</span></li>
				        <li class="symbol"><span class="off">.</span><span class="on">&gt;</span></li>
				        <li class="symbol"><span class="off">/</span><span class="on">?</span></li>
				        <li class="right-shift lastitem">shift</li>					 
				    </ul>
				    <br/>
				</div>
			</div>
			
			

		</div>

	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/keyboard.js"></script>
</body>
</html>
