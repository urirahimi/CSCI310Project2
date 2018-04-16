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
		if (document.myform.topic.value.length <= 0 && document.myform.shape.value.length <= 0) {
			document.myform.submitButton.disabled = true;
		}
		else {
			document.myform.submitButton.disabled = false;
		}
	}
	// Callback function when the form is submitted
	function submitTopic() {
	        sessionStorage.setItem('topic', document.myform.topic.value);
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
		document.getElementById("collage-options-dropdown-options").classList.toggle("show");
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
<script>
	/**
	 * Helper function for POST method.
	 *
	 * @author Rakesh Pai
	 * @since September 25, 2008
	 * @url https://stackoverflow.com/questions/133925/javascript-post-request-like-a-form-submit
	 */
	function post(path, params, method) {
	    method = method || "post"; // Set method to post by default if not specified.
	
	    // The rest of this code assumes you are not using a library.
	    // It can be made less wordy if you use one.
	    var form = document.createElement("form");
	    form.setAttribute("method", method);
	    form.setAttribute("action", path);
	
	    for(var key in params) {
	        if(params.hasOwnProperty(key)) {
	            var hiddenField = document.createElement("input");
	            hiddenField.setAttribute("type", "hidden");
	            hiddenField.setAttribute("name", key);
	            hiddenField.setAttribute("value", params[key]);
	
	            form.appendChild(hiddenField);
	        }
	    }
	
	    document.body.appendChild(form);
	    form.submit();
	}
	
	function validateShapeInput ()
	{
		var isValidShape = false;
		shape = document.getElementById("shape-input").value.toLowerCase();
		
		if (shape.length == 0) { // empty shape
			isValidShape = true;
		}
		else if (shape.length == 1 && "abcdefghijklmnopqrstuvwxyz".includes(shape)) {
			isValidShape = true;
		}
		
		var errorMessage = document.getElementById("invalid-shape-input-error-message");
		if (!isValidShape) {
			errorMessage.innerHTML = "Invalid shape input. Must be 1 of the 26 letters in the alphabet.";
		}
		else {
			 document.getElementById("loading").style.display="block";
			errorMessage.innerHTML = "";
			post("${pageContext.request.contextPath}/search", {
				topic: document.myform.topic.value,
				shape: document.myform.shape.value,
				filter: document.myform.filter.value
			});
		}
		
		return false;
	}
</script>
</head>
<body>
<div id="loading" style="display: none;">
  <img id="loading-image" src="./images/ajax-loader.gif" alt="Loading..." />
</div>
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
			</label> 
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
	<form name="myform" onsubmit="return validateShapeInput();">
		<div class="input-div">
			<input id="topic-input" type="text" name="topic"
				placeholder="Enter Topic"
				onkeyup="if(this.value.length != 0) {submitButton.disabled = false} else {submitButton.disabled = true}" /><br />
		</div>
		<br>
		<div class="input-div">
			<input id="shape-input" type="text" name="shape"
				placeholder="Enter Shape"
				onkeyup="if(this.value.length != 0) {submitButton.disabled = false} else {submitButton.disabled = true}" /><br />
		</div>
		<div class="input-div">
			<label for="contactChoice1">Sepia</label><input id="filter-input" type="radio" name="filter" value="sepia"/><label for="contactChoice1">Grayscale</label> <input id="filter-input" type="radio" name="filter" value="grayscale"/><label for="contactChoice1">None</label><input id="filter-input" type="radio" name="filter" value="none"/> <br />
		</div>
		<br />
		<br />
		<div class="input-div">
			<input type="submit" name="submitButton" id="topicSubmit" value="Build Collage">
		</div>
	</form>

	<!-- Error message, in case shape input is invalid -->
	<br />
	<br />
	<span id="invalid-shape-input-error-message" style="color: red;"></span>

</body>
</html>