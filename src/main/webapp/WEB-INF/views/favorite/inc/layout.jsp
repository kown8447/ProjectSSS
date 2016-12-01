<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/js/favorite/favorite.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/favorite/favorite.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<link href="${pageContext.request.contextPath}/css/inc/animate.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/inc/hover.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/inc/sidebar.css" rel="stylesheet">

<script type="text/javascript">
	$(function() {
		$('#preloader').fadeOut('slow');
		$('body').css({
			'overflow' : 'visible'
		});
	})
</script>

<style type="text/css">
table, td, th {
	border: 1px solid black;
}
</style>
</head>
<body style="background-image: url('${pageContext.request.contextPath}/images/back.jpg'); background-repeat: no-repeat; background-size: cover; overflow: hidden;">
	<tiles:insertAttribute name="header" />
	<div id="wrapper">
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

	<!-- Menu Toggle Script -->
	<script>
		$('#page-content-wrapper').hide();
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
	<tiles:insertAttribute name="footer" />

	<div class="modal fade" id="tableviewer">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<!-- remote ajax call이 되는영역 -->
			</div>
		</div>
	</div>
</body>
</html>