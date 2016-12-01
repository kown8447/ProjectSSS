<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<title>Insert title here</title>
<script type="text/javascript">


</script>
</head>
<body>
	<div class="container">
	<table id="list" border="1px" class="table">
		<tr>
			<td>구분</td><td>학년</td><td>학생명</td><td>성적</td>
		</tr>

		<c:forEach items="${student}" var="stu">
		<tr>
		<td>${stu.student_code }</td>
		<td>${stu.member_email}</td>
	
		
		</tr>
		</c:forEach>
		
	</table>
		</div>

</body>
</html>