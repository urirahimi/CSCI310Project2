<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Initial Page</title>
<link rel="stylesheet" type="text/css" href="./css/landing.css" />
<script>
	// Enables and disables the button depending on whether text is entered
	function enableButton() {
		if (document.topicForm.topicInput.value.length > 0) {
			document.topicForm.submitButton.disabled = false;
		} else {
			document.topicForm.submitButton.disabled = true;
		}
	}
	// Callback function when the form is submitted
	function submitTopic() {
		sessionStorage.setItem('topic', document.topicForm.topicInput.value);
	}
	// Call enableButton() after page loads, in case the user decides to go back
	window.addEventListener("DOMContentLoaded", function() {
		enableButton();
	}, false);
</script>
<script>
	/* When the user clicks on the button, 
	toggle between hiding and showing the dropdown content */
	function collageOptionsFunction() {
		document.getElementById("collage-options-dropdown-options").classList
				.toggle("show");
	}

	// Close the dropdown if the user clicks outside of it
	window.onclick = function(event) {
		if (!event.target.matches('.dropbtn')) {

			var dropdowns = document.getElementsByClassName("dropdown-content");
			var i;
			for (i = 0; i < dropdowns.length; i++) {
				var openDropdown = dropdowns[i];
				if (openDropdown.classList.contains('show')) {
					openDropdown.classList.remove('show');
				}
			}
		}
	}
</script>
</head>
<body>
	<h1 id="title">
		<center>Collage Generator</center>
	</h1>
	<div id="container">
		<button onclick="collageOptionsFunction()" class="dropbtn"
			id="collage-options-dropdown-menu">Collage Options</button>
		<div id="collage-options-dropdown-options" class="dropdown-content">
			<a>Photo Rotation</a> <label class="switch"> <input
				type="checkbox"> <span class="slider round"></span>
			</label> <a>Photo Borders</a> <label class="switch"> <input
				type="checkbox"> <span class="slider round"></span>
			</label> <a>Sepia</a> <label class="switch"> <input type="checkbox">
				<span class="slider round"></span>
			</label> <a>Grayscale</a> <label class="switch"> <input
				type="checkbox"> <span class="slider round"></span>
			</label> <a>Rainbow</a> <label class="switch"> <input type="checkbox">
				<span class="slider round"></span>
			</label>
		</div>
	</div>
	<br>
	<br>
	<div class="Heightdropdown">
		Enter Height: <select>
			<option value="50">50</option>
			<option value="100">100</option>
			<option value="150">150</option>
			<option value="200">200</option>
			<option value="300">300</option>
			<option value="500">500</option>
			<option value="1000">1000</option>
			<option value="1500">1500</option>
			<option value="2000">2000</option>
			<option value="2500">2500</option>
			<option value="3000">3000</option>
		</select>
	</div>
	<br>
	<div class="Widthdropdown">
		Enter Width: <select>
			<option value="50">50</option>
			<option value="100">100</option>
			<option value="150">150</option>
			<option value="200">200</option>
			<option value="300">300</option>
			<option value="500">500</option>
			<option value="1000">1000</option>
			<option value="1500">1500</option>
			<option value="2000">2000</option>
			<option value="2500">2500</option>
			<option value="3000">3000</option>
		</select>
	</div>
	<br>
	<br>

	<!--  The form that contains the two input elements -->
	<form name="myform" method="POST"
		action="${pageContext.request.contextPath}/search">
		<div class="input-div">
			<input id="topic-input" type="text" name="topic"
				placeholder="Enter Topic"
				onkeyup="if(this.value.length != 0) {submit.disabled = false} else {submit.disabled = true}" /><br />
		</div>
		<br>
		<div class="input-div">
			<input id="shape-input" type="text" name="shape"
				placeholder="Enter Shape"
				onkeyup="if(this.value.length != 0) {submit.disabled = false} else {submit.disabled = true}" /><br />
		</div>
		<br> <br>
		<div class="input-div">
			<!--  				<input class="submitButton" id="submitButton" type="submit" name="submit" value="Build Collage" disabled/> -->
			<input type="submit" name="submitButton" id="topicSubmit"
				value="Build Collage">
		</div>
	</form>
	<br>
	<br>

	</div>

</body>
</html>
