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
	
	
	<table border=1px>
		<tr>
		<td>구분</td><td>
		<c:choose>
		<c:when test = "${list.subject_type == '0'}"> 전공 </c:when>
		<c:when test = "${list.subject_type == '1'}"> 교양 </c:when>
		</c:choose>
		<c:choose>
		<c:when test = "${list.required_choice == '0'}">필수</c:when>
		<c:when test = "${list.required_choice == '1'}">선택</c:when>
		</c:choose>
		</td><td>강의대상</td><td>${list.grade_limit}</td></tr>
		<tr>
		<td>선수과목</td><td colspan="3">${list.before_name}</td></tr>
		<tr>
		<td>과목명</td><td colspan="3">${list.subject_name}</td></tr>
		<tr>
		<td>학점</td><td>${list.subject_credit}</td><td>정원</td><td>${list.subject_seats}</td></tr>
		
	</table>
	
	<button id="back">돌아가기</button>  
	<a href="lectureEdit.htm?subject_code=${list.subject_code}">수정하기</a>
	<a href="lectureDelete.htm">삭제하기</a>
	<a href="lecturePost.htm?subject_code=${list.subject_code}">신청하기</a>
</body>
</html>