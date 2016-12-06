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
	<div id="mjRecordRegister">
		<hr>
		MJ_RECORD
		<form action="insertMjRecord.htm" method="post">
			<table>
				<tr>
					<td>학번코드</td>
					<td><input type="text" name="student_code" id="student_code"></td>
				</tr>
				<tr>
					<td>학과코드</td>
					<td>
						<select name="department_code" id="department_code">
							<c:forEach items="${department}" var="dp">
								<option value="${dp.department_code}">${dp.department_name}/code= ${dp.department_code}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<input type="hidden" name="mj_type" id="mj_type" value="1">
			</table>
			<input type="submit" value="등록하기">
		</form>
		<a href="mjRecordList.htm">리스트</a>
		<hr>
		<form id="mjexcelForm" action="mjExcelUpload.htm">
			파일 일괄등록: <input id="mjexcel" name="mjexcel" type="file"
				placeholder="Text input">
			<button type="button" id="mjexcelUp">등록하기</button>
		</form>

		<table>
			<tr>
				<td><a href="mjexcel.htm"> 양식다운로드 </a></td>
			</tr>
		</table>
	</div>