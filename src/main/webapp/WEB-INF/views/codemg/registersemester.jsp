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
	<div id="semesterRegister">
		<hr>
		SEMESTER
		<form action="insertSemester.htm" method="post">
			<table>
				<tr>
					<td>학기코드</td>
					<td><input type="text" name="semester_code" id="semester_code"></td>
				</tr>
				<tr>
					<td>학기 이름</td>
					<td>	
						<input type="text" name="semester_name" id="semester_name">
					</td>
				</tr>
				<tr>
					<td>학기 시작일</td>
					<td>
					<input type="text" name="semester_start" id="semester_start">
					</td>
				</tr>
				<tr>
					<td>학기 종료일</td>
					<td>
					<input type="text" name="semester_end" id="semester_end">
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="등록"></td>
				</tr>
			</table>
		</form>
		<a href="semesterList.htm">리스트</a>
		<hr>
	</div>
	<input type="button" id="initBtn" value="학기 초기화" />