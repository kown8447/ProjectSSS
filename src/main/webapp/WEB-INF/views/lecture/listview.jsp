<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
	
	})

</script>
</head>
<body>
	<table id="list" border=1px">
		<tr>
			<td>구분</td><td>학년</td><td>과목명</td><td>상태</td>
		</tr>
		<c:forEach items="${subjectlist}" var="subject">
		<tr>
		<td>${subject.subject_Type}</td><td>${subject.subject_Name}</td><td>${subject.subject_state}</td>
		</tr>
		</c:forEach>
	
	</table>
	
	<a href="lectureRegister.htm">등록하기</a>
</body>
</html>