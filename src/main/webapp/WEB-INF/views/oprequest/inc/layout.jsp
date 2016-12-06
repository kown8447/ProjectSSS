<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>   

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/css/commons.css" rel="stylesheet">

<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

<link href="${pageContext.request.contextPath}/css/inc/aside.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/inc/aside.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<tiles:insertAttribute name="header"/>
	<tiles:insertAttribute name="aside" />
	<tiles:insertAttribute name="content" />
	<tiles:insertAttribute name="footer"/>
	<div class="modal fade" id="tableviewer">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<!-- remote ajax call이 되는영역 -->
			</div>
		</div>
	</div>
</body>
</html>