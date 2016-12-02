<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/inc/aside.js"></script>
<script src="${pageContext.request.contextPath}/js/callendar/callendar.js"></script>
<script  src="${pageContext.request.contextPath}/js/bootstrap-datepicker.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/css/visual.css" rel="stylesheet">
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
   <div class="row">
		<div class="col-sm-2">
			<div class="sidebar-nav">
				<div class="navbar navbar-default" role="navigation">
					<div class="navbar-header"></div>
					<div class="navbar-collapse collapse sidebar-navbar-collapse">
						<tiles:insertAttribute name="visual" />
					</div>
					<!--/.nav-collapse -->
				</div>
			</div>
		</div>
		<div class="col-sm-10">
			<tiles:insertAttribute name="content" />
		</div>
	</div>
   
   <tiles:insertAttribute name="footer"/>
</body>
</html>