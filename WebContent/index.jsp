<%@page import="jdbc.db_connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="./css/signin.css" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="./js/backend.js"></script>
<script type="text/javascript">
const LOGIN_MODAL_ID = "login-modal";

function displayModal ()
{
	document.getElementById("login-modal").style.display = 'inline';
}

function hideModal ()
{
	document.getElementById("login-modal").style.display = 'none';
}
</script>
<script type="text/javascript">
function onLoginFormSubmit()
{
	var requeststr = "Validation?";
	requeststr += "&email="+ document.getElementById("login-modal-email-input").value;
	requeststr += "&password="+ document.getElementById("login-modal-password-input").value;
	//console.log(requeststr);
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET", requeststr, false);
	xhttp.send();
	if(xhttp.responseText.trim().length > 0){
		document.getElementById("err_message").innerHTML = xhttp.responseText;
		return false;
	}
	else{
		xhttp.close();
		//hideModal();
	}
}
function onRegisterFormSubmit()
{
	console.log("Gets Heres");
	var requeststr = "Registration?";
	requeststr += "&email="+ document.getElementById("register-modal-email-input").value;
	requeststr += "&password="+ document.getElementById("register-modal-password-input").value;
	//console.log(requeststr);
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET", requeststr, false);
	xhttp.send();
	if(xhttp.responseText.trim().length > 0){
		document.getElementById("err_message").innerHTML = xhttp.responseText;
		return false;
	}
	else{
		xhttp.close();
	}
}
</script>
</head>
<body>
<p style="color: red;font-weight:bold" id = "err_message"></p>
	<h1 class="header-text">Collage Generation Application</h1>
	
	<!-- Login Modal -->
	<form class="modal-content animate" id="login-modal-form" onsubmit="return onLoginFormSubmit();">
		<div class="container">
			<!-- Email field -->
			<label for="email"><b>Email</b></label>
			<input class="login-modal-text-input" id="login-modal-email-input" type="text" placeholder="Enter email" name="email" required>

			<!-- Password field -->
			<label for="password"><b>Password</b></label>
			<input class="login-modal-text-input" id="login-modal-password-input" type="password" placeholder="Enter Password" name="password" required>

			<!-- Login button -->
			<button type="submit" id="login-modal-submission">Login</button>
			<!-- <button type="submit" id="register-modal-submission">Register</button> -->
			<!-- Any errors when logging in will be displayed here -->
			<p id="login-error-message"><p>
		</div>
	</form>
		<button onclick="document.getElementById('id01').style.display='block'" style="width:100%;">Register</button>
	<div id="id01" class="modal">
	  <form class="modal-content animate" onsubmit="return onRegisterFormSubmit();">
	    <div class="container">
	      <label for="uname"><b>Username</b></label>
	      <input type="text" id="register-modal-email-input" placeholder="Enter Username" name="uname" required>
	      <label for="psw"><b>Password</b></label>
	      <input type="password" id="register-modal-password-input" placeholder="Enter Password" name="psw" required>  
	      <button type="submit">Register</button>

	    </div>
	
	    <div class="container" style="background-color:#f1f1f1">
	      <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
	    </div>
	  </form>
</div>
	
	<!-- When the user clicks anywhere outside of the modal, close it -->
	<script type="text/javascript">
		// Get the modal
		var modal = document.getElementById('login-modal');
		
		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function (event) {
			if (event.target == modal) {
				modal.style.display = "none";
			}
		}
	</script>
	
</body>
</html>
