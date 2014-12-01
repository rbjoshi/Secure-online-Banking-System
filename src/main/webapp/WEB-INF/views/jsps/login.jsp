<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/bootstrapValidator.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/assets/js/login.js"></script>
	<link href="<%=request.getContextPath()%>/resources/assets/css/keyboard_style.css" rel="stylesheet" />
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h1>Welcome to Devil's Bank!</h1>
			</div>
		</div>
		
		<br/>
		<br/>
		
		<div class="row">
			<div class="col-md-4 col-md-4">
				<p>
					If you forget your password then there is not to worry. Click the link available below to change your password. 
				</p>
				<a class="btn btn-danger" href="forgetpassword">Forget password ? </a>
			</div>
			
			<div class="col-md-8 col-xs-8">
				<p>We provide you the best quality services. To get connected,
					you need to enter your details to enroll with us. Or login to
					access your details, if you are already enrolled.</p>

				<c:if test="${not empty error}">
					<div class="error">${error}</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
				</c:if>

				<form id="login-form" role="form" class="form" method="post" autocomplete="off" action="<c:url value='/j_spring_security_check' />">
                    <input type="hidden" name='username' value="${username}" readonly="readonly" />
					
					<div class="row" >
						<div class="form-group col-md-8">
							Passkey : <b>${passKey}</b>
						</div>
					</div>
					
					<div class="row" >
						<div class="form-group col-md-4">
							<input class="form-control" type="password" id="password" name="password" placeholder="Password" required><br />
						</div>
					</div>
					
					<div class="form-group">
						<input type="submit" class="btn btn-success" value="LogIn"
							id="login" />
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
