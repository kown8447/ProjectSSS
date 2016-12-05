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
	<div id="professorRegister">
		<hr>
		CODE_MG(PROFESSOR)
		<form action="professorCodeRegister.htm" method="post">
			<table>
				<tr>
					<td>코드</td>
					<td><input type="text" name="code" id="code"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="code_name" id="code_name">
					</td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td><input type="text" name="code_birth" id="code_birth">
					</td>
				</tr>
				<tr>
					<td>학과코드</td>
					<td>
						
						<select id="department_code" name="department_code">
							<c:forEach items="${department }" var="dp">
								<option value="${dp.department_code}">${dp.department_name}</option>
							</c:forEach>
						</select>
						
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="등록"></td>
				</tr>
			</table>
		</form>
		<a href="typeofcodelist.htm?code_type=1">리스트</a>
		<hr>
		<form id="professorexcelForm" action="professorExcelUpload.htm", method="post">
			파일 일괄등록: <input id="professorexcel" name="professorexcel" type="file"
				placeholder="Text input">
			<button type="button" id="professorexcelUp">등록하기</button>
		</form>

		<table>
			<tr>
				<td><a href="professorexcel.htm"> 양식다운로드 </a></td>
			</tr>
		</table>
	</div>