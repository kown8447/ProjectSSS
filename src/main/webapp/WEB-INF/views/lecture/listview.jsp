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

	$(function(){
		
	})

</script>
</head>
<body>
	
	<table id="list" border="1px">
		<tr>
			<td>구분</td><td>학년</td><td>과목명</td><td>상태</td>
		</tr>
		
		<c:forEach items="${subjectlist}" var="subject">
		<tr>
		<td><c:choose>
		<c:when test = "${subject.subject_type == '0'}"> 전공 </c:when>
		<c:when test = "${subject.subject_type == '1'}"> 교양 </c:when>
		</c:choose>
		<c:choose>
		<c:when test = "${subject.required_choice == '0' }">필수</c:when>
		<c:when test = "${subject.required_choice == '1' }">선택</c:when>
		</c:choose>

		</td>
		<td>${subject.record_grade}</td>
		<td><a href="lectureDetail.htm?subject_name=${subject.subject_name}">${subject.subject_name}</td></a>
		<td><c:choose>
		<c:when test = "${subject.subject_state == 0}"> 등록 </c:when>
		<c:when test = "${subject.subject_state == 1}"> 개설 </c:when>
		<c:when test = "${subject.subject_state == 2}"> 개설신청 </c:when>
		</c:choose>
		</td>
		
		</tr>
		</c:forEach>
		
	</table>
		
		
	<a href="lectureRegister.htm">등록하기</a>
</body>
</html>