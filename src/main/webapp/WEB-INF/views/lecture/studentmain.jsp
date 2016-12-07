<%--
@Project : InitSpring
@File name : studentmain.jsp
@Author : 조장현
@Data : 2016.12.02
@Desc : 학생 조회페이지 
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script src="${pageContext.request.contextPath}/js/lecture/studentmain.js"></script>
<title>Insert title here</title>

</head>
<body>
<h4>▶&nbsp;학생조회</h4><br><br>
	<select id="subject" class="form-control" >
		<option value="0">선택하세요</option>
		<c:forEach items="${myclass}" var="i">
			<option value="${i.subject_code }">${i.subject_name }</option>		
		</c:forEach>
	</select>
	
	<c:forEach items="${myclass }" var="y">
		<input type="hidden" id="d" value="${y.subject_code }">
		<input type="hidden" id="f" value="${y.student_code }">
	</c:forEach>
	
	
		<div class="container">
	<table id="list" border="1px" class="table">
	<tr><th>학번</th><th>학생명</th><th>학생이메일</th><th>성적</th></tr>
	
		</table>
		</div>


</body>
</html>