<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/visual.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/requestCourse/layout.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<link href="${pageContext.request.contextPath}/css/inc/aside.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/inc/aside.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<tiles:insertAttribute name="header"/>
	<tiles:insertAttribute name="aside" />
	<div class="row">
		<div class="col-sm-2">
			<tiles:insertAttribute name="visual" />
		</div>
		<div class="col-sm-10">
			<tiles:insertAttribute name="content" />
		</div>
	</div>
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