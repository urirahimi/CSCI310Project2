<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="utilities.Pair"%>
<%@ page import="services.ImageSaver" %>
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
		String b64PDF="";
		if (!(boolean) session.getAttribute("error")) {
			BufferedImage bImage = (BufferedImage) (session.getAttribute("collage"));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bImage, "png", baos);
			baos.flush();
			byte[] imageInByteArray = baos.toByteArray();
			baos.close();
			b64 = "data:image/png;base64, " + javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);
			b64PDF="data:image/pdf;base64, " + javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);
		} else {
			b64 = "img/error.png";
		}
		// puts image src string and topic into list
		ArrayList<Pair> list = (ArrayList<Pair>) (session.getAttribute("list"));
		Pair pair = new Pair((String) (session.getAttribute("query")), b64);
		// list is appended to the back
		if(list==null)list = new ArrayList<Pair>();
		list.add(pair);
		// put newest item to front of list
		Pair buffer = list.get(list.size() - 1);
		list.set(list.size() - 1, list.get(0));
		list.set(0, buffer);
		session.setAttribute("pair", pair.toString());
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
 				window.alert("devin");
				console.log(document.querySelector('#export').href);
				if (document.querySelector('#export').href === "http://localhost:8080/CSCI310/img/error.png") {
					return false;
				}
				else {
					return false;
				}
			}
			function pdfDownload()
			{
				<%session.setAttribute("pdfB",true);%>
				var requeststr = "PDFDownload?";	
				var xhttp = new XMLHttpRequest();
				console.log("In save func. before open and send");
				xhttp.open("GET", requeststr, false);
				xhttp.send();
				
				xhttp.close();
			}
			function saveResult() {
				<%session.setAttribute("pdfB",false);%>
				console.log("In save func");
				var requeststr = "SaveServlet?";	
				
				/* requeststr += "save="+ ((Pair)session.getAttribute("pair")).toString(); */
				/* requeststr += "save="+ request.getAttribute("pair"); */
				var xhttp = new XMLHttpRequest();
				console.log("In save func. before open and send");
				xhttp.open("GET", requeststr, false);
				xhttp.send();
				console.log(xhttp.responseText.trim());
				if (xhttp.responseText.trim() == "VALID_LOGIN") {
					console.log("In save func 1");
					document.getElementById("login-error-message").innerHTML = "";
					console.log("Hi!");
					window.location.replace("CollageDisplay.jsp");
					console.log("In save func 3");
				}
				else if (xhttp.responseText.trim().length > 0) {
					document.getElementById("login-error-message").innerHTML = xhttp.responseText;
				}
				else{
					xhttp.close();
					// hideModal();
				}				
				return false;
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
		<form>
			<a id="export" download="collage.png" href="<%=b64%>">
			Download as PNG
			</a>
			<a id="export" download="collage.pdf" href="<%=b64PDF%>">
			Download as PDF
			</a>
			<a id="save" href="<%=b64%>" > <input id="save-button" type="button" 
				value="Save Collage" onclick="saveResult()"></a>
			<div id="collage">
				<img id="collage-image" src="<%=b64%>"
					alt="Insufficient images found." />
			</div>
		</form>
		<div id="build-another">
			<form name="myform" method="POST"
				action="${pageContext.request.contextPath}/search">
				<div class="input-div" >
					<input id="input" type="text" name="topic" placeholder="Enter topic" /><br />
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
<!-- 		<script language="javascript" type="text/javascript">
     $(window).load(function() {
     $('#loading').hide();
  });
</script> --> 
</body>
</html>
