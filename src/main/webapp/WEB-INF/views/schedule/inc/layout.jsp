<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/callendar/callendar.js"></script>
<script  src="${pageContext.request.contextPath}/js/bootstrap-datepicker.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-datepicker.css">
<link href="${pageContext.request.contextPath}/fullcalendar-3.0.1/fullcalendar.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/callendar.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/fullcalendar-3.0.1/fullcalendar.print.css" rel="stylesheet" media="print" />
<script src="${pageContext.request.contextPath}/fullcalendar-3.0.1/lib/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/fullcalendar-3.0.1/fullcalendar.js"></script>
<script src="${pageContext.request.contextPath}/fullcalendar-3.0.1/locale-all.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script> 
<title>Insert title here</title>	
</head>
<body>
   <tiles:insertAttribute name="header"/>
   
   <tiles:insertAttribute name="content"/>
   
   <tiles:insertAttribute name="footer"/>
</body>
</html>