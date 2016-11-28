<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
  .thumb {
    height: 75px;
    border: 1px solid #000;
    margin: 10px 5px 0 0;
  }
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="${pageContext.request.contextPath}/js/join/join.js"></script>
<link href="${pageContext.request.contextPath}/css/commons.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/join.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script> 


<title>Insert title here</title>
</head>
<body>	
	<tiles:insertAttribute name="content"/>
	
	<tiles:insertAttribute name="footer"/>
</body>
</html>