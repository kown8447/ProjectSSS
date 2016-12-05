<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<td>구분</td>
				<td>학년</td>
				<td>과목</td>
				<td>교수</td>
				<td>상태</td>
			</tr>
		</thead>
		<c:forEach items="${oprequest}" var="oplist">
		<tbody>
			<tr>
				<td>${oplist.required_choice}</td>
				<td>${oplist.grade_limit}</td>
				<td><a href="requestDetail.htm?code=${oplist.subject_code}">${oplist.subject_name}</a></td>
				<td>${oplist.code_name}</td>
				<td>
				<c:choose>
					<c:when test="${oplist.success_check == 0}">대기</c:when>
					<c:when test="${oplist.success_check == 1}">승인</c:when>
					<c:when test="${oplist.success_check == 2}">거절</c:when>
					<c:otherwise>대기</c:otherwise>
				</c:choose>	
				</td>
			</tr>
		</tbody>
		</c:forEach>
	</table>
</body>
</html>