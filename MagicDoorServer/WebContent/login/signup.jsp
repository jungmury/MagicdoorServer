<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.button {
	-moz-box-shadow: inset 0px 1px 0px 0px #ffffff;
	-webkit-box-shadow: inset 0px 1px 0px 0px #ffffff;
	box-shadow: inset 0px 1px 0px 0px #ffffff;
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #ededed
		), color-stop(1, #dfdfdf));
	background: -moz-linear-gradient(center top, #ededed 5%, #dfdfdf 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ededed',
		endColorstr='#dfdfdf');
	background-color: #ededed;
	-moz-border-radius: 6px;
	-webkit-border-radius: 6px;
	border-radius: 6px;
	border: 1px solid #dcdcdc;
	display: inline-block;
	color: #777777;
	font-family: arial;
	font-size: 15px;
	font-weight: bold;
	padding: 6px 24px;
	text-decoration: none;
	text-shadow: 1px 1px 0px #ffffff;
}

.button:hover {
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #dfdfdf
		), color-stop(1, #ededed));
	background: -moz-linear-gradient(center top, #dfdfdf 5%, #ededed 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#dfdfdf',
		endColorstr='#ededed');
	background-color: #dfdfdf;
}

.button:active {
	position: relative;
	top: 1px;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script language="javascript"
	src="<%=request.getContextPath()%>/js/script.js"></script>
</head>
<body>

	<h3>Sign Up Page</h3>
	<form name="signUpForm" id="signUpForm" action="SignUpAction.login"
		method="post"
		onsubmit="return checkSignUpFormBlank('divCheckDuplicateUserName', 'divCheckPasswordMatch')"
		target="topFrame">

		<div id="loginBox">
			<div id="loginHeader"></div>
			<div id="formVal">
				<div>
					User Name : <input type="text" name="userName" id="userName"
						onkeyup="return checkDuplicateUserName('signUpForm', 'divCheckDuplicateUserName')"><span
						style="font-size: 10px;"></span>
				</div>
				<div class="signUpFormAlert" id="divCheckDuplicateUserName"
					style="color: red;"></div>
				<div>
					Password: <input type="password" name="password" id="password"
						onkeyup="return checkPasswordMatch('signUpForm', 'divCheckPasswordMatch')"><span
						style="font-size: 10px;"></span>
				</div>

				<div>
					Confirm Password: <input type="password" name="confirmPassword"
						id="confirmPassword"
						onkeyup="return checkPasswordMatch('signUpForm', 'divCheckPasswordMatch')"><span
						style="font-size: 10px;"></span>
				</div>

				<div class="signUpFormAlert" id="divCheckPasswordMatch"
					style="color: red;"></div>

				<div>
					First Name: <input type="text" name="firstName" id="firstName"><span
						style="font-size: 10px;"></span>
				</div>

				<div>
					Last Name: <input type="text" name="lastName" id="lastName"><span
						style="font-size: 10px;"></span>
				</div>

				<div>
					E-Mail Address: <input type="text" name="eMailAddress"
						id="eMailAddress"><span style="font-size: 10px;"></span>
				</div>

				<!-- 		
				<div>User Type : 
				Lecturer <input type="radio" name="userType" id ="userType" value="lecturer"> &nbsp;&nbsp; 
				Student <input type="radio" name="userType" id="userType" value="student" checked>
				</div>
				 -->
				<input type="hidden" name="userType" id="userType" value="lecturer">
				<div style="clear: both; height: 0px;"></div>

				<div id="msgbox"></div>
			</div>
			<div id="loginFooter">
				<label> <br><input class="button" type="submit" name="ignUp" id="signUp"
					value="Sign Up">
				</label>
			</div>
		</div>
	</form>

</body>
</html>