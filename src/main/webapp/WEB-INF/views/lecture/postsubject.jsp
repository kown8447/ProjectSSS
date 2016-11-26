<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script type="text/javascript">
	$(function(){
		$("#building").select(function(){
			alert("빌딩고르셧셈");

			
		})
		
	})
	

</script>
</head>
<body>
	<table border=1px>
		<tr>
		<td>구분</td><td>선수과목></td><td>과목명</td><td>학점</td><td>정원</td><td>수강대상</td>
		</tr>
		<tr>
		<td>	
		<c:choose>
		<c:when test = "${list.subject_type == '0'}"> 전공 </c:when>
		<c:when test = "${list.subject_type == '1'}"> 교양 </c:when>
		</c:choose>
		<c:choose>
		<c:when test = "${list.required_choice == '0' }">필수</c:when>
		<c:when test = "${list.required_choice == '1' }">선택</c:when>
		</c:choose>
		</td>
		<td>${list.before_name}</td>
		<td>${list.subject_name }</td>
		<td>${list.subject_credit}</td>
		<td>${list.subject_seats}</td>
		<td>${list.grade_limit }</td>
		</tr>
		</table>
		<br>
		
		<!-- 건물  -->
		<select id="building" name="building">
			<option id="B_001" name="B_001">본부동</option>
			<option id="B_002" name="B_002">장현동(아직없음)</option>
		</select>
		
		<p id="jjg"></p>
		
		
	
	<button id="back">돌아가기</button>  
	<a href="lecturePost.htm">신청하기</a>
</body>
</html>