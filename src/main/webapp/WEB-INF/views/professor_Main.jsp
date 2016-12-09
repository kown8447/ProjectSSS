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
<%@ taglib prefix="se" uri="http://www.springframework.org/security/tags"%>
<link href="css/animate.css" rel="stylesheet">
<title>Login</title>
<style>
	body {
			background-image : url("${pageContext.request.contextPath}/images/test2.jpg");
			background-repeat: no-repeat;
			background-size: cover;	
		}
</STYLE>
</head>
<body>	

				
		<div class="row">
			<se:authorize access="hasAnyRole('ROLE_STUDENT','ROLE_PROFESSOR','ROLE_ADMIN')">
					<font style="font-size: 15pt;">
						<a href="${pageContext.request.contextPath}/logout" >로그아웃</a>
					</font>
				</se:authorize>
		
		<div class="container" style="margin-top:5%">
			<input type="image" class="col-sm-3" src="${pageContext.request.contextPath}/images/center.png">			

	
		<div class = "col-sm-12 col-sm-offset-5">
			<input type="image" style="margin-right: 2%;"class="img-rounded col-sm-3 hvr-grow-shadow " src="${pageContext.request.contextPath}/images/grade.JPG">
			<input type="image" style="margin-right: 2%;" class="img-rounded col-sm-3 hvr-grow-shadow" src="${pageContext.request.contextPath}/images/rice.JPG">
		</div>
		</div>
	
	</div>

		
</body>
</html>