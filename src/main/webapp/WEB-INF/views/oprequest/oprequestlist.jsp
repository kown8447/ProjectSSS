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
		<c:forEach items="${oprequestlist}" var="oplist">
		<tbody>
			<tr>
				<td>${oplist.subject_type} </td>
				<td>${oplist.grade_limit}</td>
				<td>${oplist.subject_name }</td>
				<td>${oplist.code_name}</td>
				<td>${oplist.success_check}</td>
			</tr>
		</tbody>
		</c:forEach>
	</table>
</body>
</html>