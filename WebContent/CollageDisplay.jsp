<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="utilities.Pair"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Collage Display</title>
<link rel="stylesheet" type="text/css" href="./css/CollageDisplay.css" />
</head>
<body>
	<%
		// converts BufferedImage into Image src
		String b64 = "";
		if (!(boolean) session.getAttribute("error")) {
			BufferedImage bImage = (BufferedImage) (session.getAttribute("collage"));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bImage, "png", baos);
			baos.flush();
			byte[] imageInByteArray = baos.toByteArray();
			baos.close();
			b64 = "data:image/png;base64, " + javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);
		} else {
			b64 = "img/error.png";
		}
		// puts image src string and topic into list
		ArrayList<Pair> list = (ArrayList<Pair>) (session.getAttribute("list"));
		Pair pair = new Pair((String) (session.getAttribute("query")), b64);
		// list is appended to the back
		list.add(pair);
		// put newest item to front of list
		Pair buffer = list.get(list.size() - 1);
		list.set(list.size() - 1, list.get(0));
		list.set(0, buffer);
	%>
	<script type="text/javascript">
			function changeDisplayedImage(i) {
				currentImage = document.querySelector('#collage-image').src;
				newImage = (document.querySelectorAll('.prevs-img'))[i-1].src;
				
				document.querySelector('#collage-image').src = newImage;
				(document.querySelectorAll('.prevs-img'))[i-1].src = currentImage;
				
				currentTopic = document.querySelector('#collage-topic').innerHTML;
				newTopic = document.querySelectorAll('.prevs-topic')[i-1].innerHTML;
				
				document.querySelector('#collage-topic').innerHTML = newTopic;
				(document.querySelectorAll('.prevs-topic'))[i-1].innerHTML = currentTopic;
				
				document.querySelector('#export').href = newImage;
				
				return false;	
			}
			
			function check() {
				console.log(document.querySelector('#export').href);
				if (document.querySelector('#export').href === "http://localhost:8080/CSCI310/img/error.png") {
					return false;
				}
				else {
					return true;
				}
			}
		</script>
	<div id="outside-div">
		<div id="title">
			<h1>
				Collage for topic
				<div style="display: inline" id="collage-topic"><%=session.getAttribute("query")%></div>
			</h1>
			<!-- TODO: collage title needs to be hooked up, also need to EXPORT -->
		</div>
		<a id="export" download="collage.png" href="<%=b64%>"> <input
			id="export-button" type="submit" value="Export Collage"
			onclick="return check()">
		</a>
		<div id="collage">
			<img id="collage-image" src="<%=b64%>"
				alt="Insufficient images found." />
		</div>
		<div id="build-another">
			<form name="myform" method="POST"
				action="${pageContext.request.contextPath}/search">
				<div class="input-div">
					<input id="input" type="text" name="topic"
						placeholder="Enter topic" /><br />
				</div>
				<div class="input-div">
					<input id="build-button" type="submit" name="submit"
						value="Build Another Collage" />
				</div>
			</form>
		</div>
		<div id="previous-collages">
			<%
				for (int i = 0; i < list.size(); i++) {
					if (!b64.equals(list.get(i).getImage())) {
			%>
			<div class="prevs-div">
				<a onclick="changeDisplayedImage(<%=i%>)"><img
					class="prevs-img" src="<%=list.get(i).getImage()%>"></a>
				<p class="prevs-topic" style="display: none"><%=list.get(i).getTopic()%></p>
			</div>
			<%
				}
				}
			%>

		</div>
</body>
</html>
