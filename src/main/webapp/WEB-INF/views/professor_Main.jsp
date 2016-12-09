<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="se" uri="http://www.springframework.org/security/tags"%>
<!-- 
   @File name : Professor_Main.jsp
   @Author : 송아름 , 조장현
   @Data : 2016.12.9
   @Desc : 교수 메인  view
-->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/js/main/professor_main.js"></script>
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/hover.css" rel="stylesheet">

</head>
<body>		

	<div class="row col-sm-offset-1" style="margin-top: 3%;">
				<se:authorize access="hasAnyRole('ROLE_STUDENT','ROLE_PROFESSOR','ROLE_ADMIN')">
					<input type="image" class="col-sm-offset-8" src="${pageContext.request.contextPath}/images/logout.png">
					<a href="${pageContext.request.contextPath}/logout">LOGOUT</a>
				</se:authorize>	
			
		
			<input type="image" class="col-sm-2" src="${pageContext.request.contextPath}/images/logo.png">			
			
	
		<div class = "col-sm-7 col-sm-offset-5" >
			<input type="image" id="lecture" style="margin-right: 2%;"class="hvr-grow" src="${pageContext.request.contextPath}/images/lecture.png">
			<input type="image" id="student" class="hvr-grow" src="${pageContext.request.contextPath}/images/student.png">
		</div>
		
		<div class = "col-sm-7 col-sm-offset-5">
			<input type="image" id="schedule" style="margin-right: 2%;" class="hvr-grow" src="${pageContext.request.contextPath}/images/schedule.png">
			<input type="image" id="notice" class="hvr-grow" src="${pageContext.request.contextPath}/images/notice.png">
		</div>

	</div>
</body>
</html>