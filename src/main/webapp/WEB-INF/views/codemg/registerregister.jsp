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
	<div id="register">
		<hr>
		REGISTER
		<form action="insertRegister.htm" method="post">
			<table>
				<tr>
					<td>학번</td>
					<td>
					<input type="text" name="student_code" id=student_code>
					</td>
				</tr>
				<tr>
					<td>학기코드</td>
					<td>
						<select id="semester_code " name="semester_code">
							<c:forEach items="${semester}" var="sm">
								<option value="${sm.semester_code}">${sm.semester_name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>등록구분</td>
					<td>
					<input type="radio" value="0" id="register_state" name="register_state">등록안함
					<input type="radio" value="1" id="register_state" name="register_state">등록
					</td>
				</tr>
				<tr>
					<td>등록금</td>
					<td><input type="text" id="tuition" name="tuition"></td>
				</tr>
				<tr>
					<td>등록여부</td>
					<td>
					<input type="radio" value="0" id="register_type" name="register_type">일반학기
					<input type="radio" value="1" id="register_type" name="register_type">계절학기 
					<input type="radio" value="2" id="register_type" name="register_type">졸업연기
					</td>
				</tr>
			</table>
			<input type="submit" value="등록하기">
		</form>
		<a href="registerlist.htm">리스트</a>
		<hr>
		<form id="regexcelForm" action="regExcelUpload.htm" method="post">
			파일 일괄등록: <input id="regexcel" name="regexcel" type="file"
				placeholder="Text input">
			<button type="button" id="regexcelUp">등록하기</button>
		</form>

		<table>
			<tr>
				<td><a href="regexcel.htm"> 양식다운로드 </a></td>
			</tr>
		</table>
	</div>