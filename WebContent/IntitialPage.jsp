<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Initial Page</title>
		<link rel="stylesheet" type="text/css" href="./css/InitialPage.css"/>
	</head>
<body>
	<div id="form-div">
		<form name="myform" method="POST" action="${pageContext.request.contextPath}/search">
			<div class="input-div">
				<input id="input" type="text" name="topic" placeholder="Enter topic" onkeyup="if(this.value.length != 0) {submit.disabled = false} else {submit.disabled = true}"/><br/>
			</div> 
			<div class="input-div">
				<input id="build-button" type="submit" name="submit" value="Build Collage" disabled/>
			</div>
		</form>
	</div>

</body>
</html>