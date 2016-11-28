<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
 <script src="http://malsup.github.com/jquery.form.js"></script> 
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
   href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<link
   href="${pageContext.request.contextPath}/fullcalendar-3.0.1/fullcalendar.css"
   rel="stylesheet" />
<link
   href="${pageContext.request.contextPath}/fullcalendar-3.0.1/fullcalendar.print.css"
   rel="stylesheet" media="print" />
<script type="text/javascript"
   src="${pageContext.request.contextPath}/fullcalendar-3.0.1/lib/moment.min.js"></script>
<script type="text/javascript"
   src="${pageContext.request.contextPath}/fullcalendar-3.0.1/fullcalendar.js"
   charset="UTF-8"></script>
<script type="text/javascript"
   src="${pageContext.request.contextPath}/fullcalendar-3.0.1//locale-all.js"></script>

<script src="${pageContext.request.contextPath}/js/callendar/callendar.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<tiles:insertAttribute name="header"/>
	
	<tiles:insertAttribute name="content"/>
	
	<tiles:insertAttribute name="footer"/>
</body>
</html>