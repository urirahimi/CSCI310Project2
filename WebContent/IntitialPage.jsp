<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Initial Page</title>
		<link rel="stylesheet" type="text/css" href="./MainPageCSS.css"/>
		<script>
				// Enables and disables the button depending on whether text is entered
				function enableButton() {
					if (document.topicForm.topicInput.value.length > 0) {
						document.topicForm.submitButton.disabled = false;
					}
					else {
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
					function dropFunc() {
					    document.getElementById("myDropdown").classList.toggle("show");
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
					/* When the user clicks on the button, 
					toggle between hiding and showing the dropdown content */
					function collageOptionsFunction() {
					    document.getElementById("myDropdown2").classList.toggle("show");
					    console.log("devin");
					}
					
					// Close the dropdown if the user clicks outside of it
					window.onclick = function(event) {
					  if (!event.target.matches('.dropbtn') ) {
					
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
<h1 id="title"> <center>BroGrammers! </center> </h1>
		<div id="container">
			
			<div class="dropdown"> 
				<button onclick="dropFunc()" class="dropbtn">Select Shape</button>
				  <div id="myDropdown" class="dropdown-content">
				    <a >Shape 1</a>
				    <a >Shape 2</a>
				    <a >Shape 3</a>
				  </div>
				<button onclick="collageOptionsFunction()" class="dropbtn">Collage Options</button>
				  <div id="myDropdown2" class="dropdown-content">
				    <a>Photo Rotation</a>
				    		<label class="switch">
						  <input type="checkbox">
						  <span class="slider round"></span>
						</label>
				    <a>Photo Borders</a>
				    		<label class="switch">
						  <input type="checkbox">
						  <span class="slider round"></span>
						</label>
					<a>Sepia</a>
						<label class="switch">
						  <input type="checkbox">
						  <span class="slider round"></span>
						</label>
					<a>Grayscale</a>
						<label class="switch">
						  <input type="checkbox">
						  <span class="slider round"></span>
						</label>
					<a>Rainbow</a>
						<label class="switch">
						  <input type="checkbox">
						  <span class="slider round"></span>
						</label>		
				</div>
	  		</div>
			<br>
			<br>
			<!--  The form that contains the two input elements -->
			<form name="myform" method="POST" action="${pageContext.request.contextPath}/search">
				<div class="input-div"> 
 				<input id="input" type="text" name="topic" placeholder="Enter topic" onkeyup="if(this.value.length != 0) {submit.disabled = false} else {submit.disabled = true}"/><br/> 
 			</div>  
			<div class="input-div"> 
<!--  				<input class="submitButton" id="submitButton" type="submit" name="submit" value="Build Collage" disabled/> -->
 				<input type="submit" name="submitButton" id="topicSubmit" value="Build Collage">
			</div>
			</form>
			<br>
			<br>
			<button type="button" name="saveButton" id="saveButton" onclick="alert('This should save the collage I guess')">Save Collage</button>	
		

		</div>
<!-- 	<div id="form-div"> -->
<%-- 		<form name="myform" method="POST" action="${pageContext.request.contextPath}/search"> --%>
<!-- 			<div class="input-div"> -->
<!-- 				<input id="input" type="text" name="topic" placeholder="Enter topic" onkeyup="if(this.value.length != 0) {submit.disabled = false} else {submit.disabled = true}"/><br/> -->
<!-- 			</div>  -->
<!-- 			<div class="input-div"> -->
<!-- 				<input id="build-button" type="submit" name="submit" value="Build Collage" disabled/> -->
<!-- 			</div> -->
<!-- 		</form> -->
<!-- 	</div> -->

</body>
</html>
