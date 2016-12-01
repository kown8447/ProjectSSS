<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${pageContext.request.contextPath}/css/commons.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<link href="${pageContext.request.contextPath}/css/inc/animate.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/inc/hover.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/inc/sidebar.css" rel="stylesheet">

<script type="text/javascript">
	$(function() {
		$("#wrapper").toggleClass("toggled");
		$('#preloader').fadeOut('slow');
		$('body').css({
			'overflow' : 'visible'
		});
	})
</script>

<title>Insert title here</title>
</head>
<body>

	<tiles:insertAttribute name="header"/>
	<div id="wrapper" style="width:10%">
		<div id="sidebar-wrapper" style="display: block;">
			<tiles:insertAttribute name="aside" />
		</div>
		<div id="page-content-wrapper" class="col-md-1" style="display: block;">
			<div width="10%">
				<div class="row">
					<div class="col-lg-1">
						<a href="#menu-toggle" class="btn btn-default" id="menu-toggle">=</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Menu Toggle Script -->
	<script>
		$("#menu-toggle").click(function(e) {
			e.preventDefault();
			$('#page-content-wrapper').hide();
			$("#wrapper").toggleClass("toggled");
		});
	</script>

	<div id="preloader">
		<div id="loader"> </div>
	</div>
	<div class="row container">
		<tiles:insertAttribute name="content" />
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