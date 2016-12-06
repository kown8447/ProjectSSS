<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/js/member/excel.js"></script>  
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>  
<script src="${pageContext.request.contextPath}/js/admin/codemg.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link href="${pageContext.request.contextPath}/css/inc/aside.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/inc/aside.js"></script>

<head>
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