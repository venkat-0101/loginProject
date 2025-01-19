<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width"> 
<title>Login/Register</title>
<link rel="stylesheet" href="welcomePageStyle.css">
<body>
	<div class="main">  	
		<input type="checkbox" id="chk" aria-hidden="true">

			<div class="register">
				<form name="registrationform" action="<%= request.getContextPath()%>/Home?action=register" method="post">
					<label for="chk" aria-hidden="true">Register</label>
					<input type="text" name="user_name" placeholder="User name" required>
					<input type="email" name="user_email" placeholder="Email" required>
					<input type="password" name="user_password" placeholder="Password" required>
					<input type="password" name="user_retypepassword" placeholder="Retype Password" required>
					<button>Submit</button>
				</form>
			</div>

			<div class="login">
				<form name="loginform" action="<%= request.getContextPath()%>/Home?action=login" method="post">
					<label for="chk" aria-hidden="true">Login</label>
					<input type="email" name="user_email" placeholder="Email" required>
					<input type="password" name="user_password" placeholder="Password" required>
					<button>Login</button>
				</form>
			</div>
	</div>
</body>
</html>