<%--
@Project : InitSpring
@File name : subjectlist.jsp
@Author : 조장현
@Data : 2016.11.23
@Desc : 과목 리스트  
--%>

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
		<td>${subject.grade_limit}</td>
		<td><a href="lectureDetail.htm?subject_code=${subject.subject_code}&success_check=${subject.success_check }">
		${subject.subject_name}</td></a>
		<td><c:choose>
		<c:when test = "${subject.success_check == 0}"> 대기 </c:when>
		<c:when test = "${subject.success_check == 1}"> 승인 </c:when>
		<c:when test = "${subject.success_check == 2}"> 거절 </c:when>
		</c:choose>
		</td>
		
		</tr>
		</c:forEach>
		
	</table>
		</div>
		
	<a href="lectureRegister.htm">등록하기</a>
</body>
</html>