<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
   @Project : InitSpring
   @File name : student_Main.jsp
   @Author : 조장현, 송아름
   @Data : 2016.11.22
   @Desc : 학생 main view
-->    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@ taglib prefix="se" uri="http://www.springframework.org/security/tags"%>
<script src="${pageContext.request.contextPath}/js/main/student_main.js"></script>
<link href="css/hover.css" rel="stylesheet">
<title>Login</title>

</head>
<body>		

	<div class="row col-sm-offset-1" style="margin-top: 3%;">
				<se:authorize access="hasAnyRole('ROLE_STUDENT','ROLE_PROFESSOR','ROLE_ADMIN')">
					<input type="image" class="col-sm-offset-8" src="${pageContext.request.contextPath}/images/logout.png">
					<a href="${pageContext.request.contextPath}/logout">LOGOUT</a>
				</se:authorize>	
			
		
			<input type="image" class="col-sm-2" src="${pageContext.request.contextPath}/images/logo.png">			
			
	
		<div class = "col-sm-7 col-sm-offset-5" >
			<input type="image" style="margin-right: 2%;" id="register" class="hvr-grow" src="${pageContext.request.contextPath}/images/register.png">
			<input type="image" id="grade" class="hvr-grow" src="${pageContext.request.contextPath}/images/grade.png">
		</div>
		
		<div class = "col-sm-7 col-sm-offset-5" style="margin-top: 1%;">
			<input type="image" id="profile" style="margin-right: 2%;" class="hvr-grow" src="${pageContext.request.contextPath}/images/profile.png">
			<input type="image" id="notice" class="hvr-grow" src="${pageContext.request.contextPath}/images/notice.png">
		</div>

	</div>
</body>
</html>