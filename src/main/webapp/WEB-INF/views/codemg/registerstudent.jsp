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
	<div id="studentRegister">
		<hr>
		CODE_MG(STUDENT)
		<form action="studentRegister.htm" method="post">
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
								<option value="${dp.department_code}">${dp.department_name}/code= ${dp.department_code}</option>
							</c:forEach>
						</select>
						
					</td>
				</tr>
			</table>
			<input type="submit" value="등록">
		</form>
		
		<a href="typeofcodelist.htm?code_type=0">리스트</a>
		<hr>
		<form id="excelForm" action="compExcelUpload.htm", method="post">
			파일 일괄등록: <input id="excel" name="excel" type="file"
				placeholder="Text input">
			<button type="button" id="excelUp" onclick="check()">등록하기</button>
		</form>

		<table>
			<tr>
				<td><a href="excel.htm"> 양식다운로드 </a></td>
			</tr>
		</table>
	</div>