<%--
@Project : InitSpring
@File name : subjectupdate.jsp
@Author : 조장현
@Data : 2016.11.23
@Desc : 과목 수정 
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="lectureEditOk.htm">
	<table border=1px>
		<tr>
		<td>구분</td>
		<td>
		 <c:choose>
		 <c:when test="${list.subject_type == '0'}">전공</c:when>
		 <c:when test="${list.subject_type == '1'}">교양</c:when>
		 </c:choose>
		 <input type="hidden" name="subject_type" value=${list.subject_type }>
			 <c:choose>
		 <c:when test="${list.required_choice == '0'}">필수</c:when>
		 <c:when test="${list.required_choice == '1'}">선택</c:when>
		 </c:choose>
		</td>
		<td>강의대상</td><td>${list.grade_limit}</td></tr>
		<tr>
		<td>선수과목</td><td colspan="3">
				<select id="before_name" name="before_name">
				<option value="0">없음</option>
				<c:forEach items="${list}" var="i">
					<option value="${i}">${i}</option>
				</c:forEach>
				</select>
				 <br> 
		<tr>
		<td>과목명</td><td colspan="3"><input type="text" name="subject_name"value="${list.subject_name}"></td></tr>
		<tr>
		<td>학점</td><td><input type="number" name="subject_credit" value="${list.subject_credit}"></td>
		<td>정원</td><td><input type="number" name="subject_seats" value="${list.subject_seats}"></td></tr>
		<input type="hidden" name="subject_code" value="${list.subject_code }">
		<tr>
		
	</table>
	<input type="submit" value="전송쓰">
	</form>
	<button id="back">돌아가기</button>  

</body>
</html>