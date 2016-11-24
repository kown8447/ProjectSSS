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
	<c:forEach items="${list}" var="list">
	
	<table border=1px>
		<tr>
		<td>구분</td><td><input type="text" value="${list.subject_type}"></td>
		<td>강의대상</td><td><input type="text" value="${list.record_grade}"></td></tr>
		<tr>
		<td>선수과목</td><td colspan="3"><input type="text" value="${list.subject_state}"></td></tr>
		<tr>
		<td>과목명</td><td colspan="3"><input type="text" value="${list.subject_name}"></td></tr>
		<tr>
		<td>학점</td><td><input type="text" value="${list.subject_credit}"></td>
		<td>정원</td><td><input type="text" value="${list.subject_seats}"></td></tr>
		<tr>
		</c:forEach>
	</table>
	
	<button id="back">돌아가기</button>  
	<a href="lectureEdit.htm">수정하기</a>
</body>
</html>