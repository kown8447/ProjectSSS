<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
   @Project : InitSpring
   @File name : student_Main.jsp
   @Author : 김영빈
   @Data : 2016.11.22
   @Desc : 학생 main view
-->    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="css/hover.css" rel="stylesheet">
<link href="css/animate.css" rel="stylesheet">
<title>Login</title>
<style>
	body {
			background-image : url("${pageContext.request.contextPath}/images/mainback.jpg");
			background-repeat: no-repeat;
			background-size: cover;			
		}
</STYLE>
</head>
<body>	
	<div class="row">
		<div class="container" style="margin-top:5%">
			<input type="image" class="col-sm-6" src="${pageContext.request.contextPath}/images/center.png">		
		</div>
	
	
		<div class="container" style="margin-top:2%">
		<div class = "col-sm-12">
			<input type="image" class="img-rounded col-sm-3 hvr-grow-shadow " src="${pageContext.request.contextPath}/images/grade.JPG">
			<input type="image" class="img-rounded col-sm-3 hvr-grow-shadow" src="${pageContext.request.contextPath}/images/rice.JPG">
		</div>
		</div>
		<div class= "container" style="margin-top:2%">
		<div class = "col-sm-12">
			<input type="image" class="img-rounded col-sm-3 hvr-grow-shadow" src="${pageContext.request.contextPath}/images/profile.JPG">
			<input type="image" class="img-rounded col-sm-3 hvr-grow-shadow" src="${pageContext.request.contextPath}/images/sookang.JPG">
		</div>
		</div>
		
	</div>
</body>
</html>