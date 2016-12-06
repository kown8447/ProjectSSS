<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="http://malsup.github.com/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"/>
<script src="${pageContext.request.contextPath}/js/admin/codemg.js"/>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript">

</script>
	<div id="opened">
		<hr>
		OPENED
		<table>
		<thead>
			<tr>
				<td>과목코드</td>
				<td>학기코드</td>
				<td>수강인원</td>SCHOLARSHIP
			</tr>
		</thead>
		<c:forEach items="${opened}" var="op" varStatus="index">
			<tbody>
				<tr>
					<td>${op.subject_name}</td>
					<td>${op.semester_name }</td>
					<td>${op.registed_seat}</td>
				</tr>
			</tbody>
		</c:forEach>
	</table>
	</div>