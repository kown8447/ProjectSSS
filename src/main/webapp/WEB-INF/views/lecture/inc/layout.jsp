<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/requestCourse/layout.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/requestCourse/preRegisterCourse.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<tiles:insertAttribute name="header"/>
	
		<div id="wrapper" style="width:10%">
		<div id="sidebar-wrapper" style="display: block;">
			<tiles:insertAttribute name="aside" />
		</div>
		<div id="page-content-wrapper" style="display: block;">
			<div>
				<div class="row">
					<div class="col-lg-12">
						<a href="#menu-toggle" class="btn btn-default" id="menu-toggle">=</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	

	<div class="row">
		<div class="col-sm-2">
			<div class="sidebar-nav">
				<div class="navbar navbar-default" role="navigation">
					<div class="navbar-header"></div>
						<tiles:insertAttribute name="visual" />
					<!--/.nav-collapse -->
				</div>
			</div>
		</div>
		<div class="col-sm-10">
			<tiles:insertAttribute name="content" />
		</div>
	</div>

	<tiles:insertAttribute name="footer" />
	
	<!-- Menu Toggle Script -->
	<script>
		$("#menu-toggle").click(function(e) {
			e.preventDefault();
			$('#page-content-wrapper').hide();
			$("#wrapper").toggleClass("toggled");
		});
	</script>


	<div class="modal fade" id="tableviewer">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<!-- remote ajax call이 되는영역 -->
			</div>
		</div>
	</div>
</body>
</html>