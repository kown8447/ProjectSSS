<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script src="${pageContext.request.contextPath}/js/collegeRegister/studentRecord.js"></script>
<style type="text/css">
table, td, th {
	border: 1px solid black;
}
</style>
</head>
<body>
	<tiles:insertAttribute name="header" />

	<tiles:insertAttribute name="aside" />

	<tiles:insertAttribute name="content" />

	<tiles:insertAttribute name="footer" />
</body>
</html>