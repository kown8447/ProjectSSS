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
<div id="adminRegister">
		<hr>
		CODE_MG(ADMIN)
		<form action="adminCodeRegister.htm" method="post">
			<table>
				<tr>
					<td>코드</td>
					<td><input type="text" name="code" id="code"></td>
				</tr>
				<tr>
					<td>구분</td>
					<td>
						<!-- <input type="radio" value="0" name="code_type" id="code_type">학생  -->
						<input type="radio" value="2" name="code_type" id="code_type">관리자 
						<!-- <input type="radio" value="2" name="code_type" id="code_type">관리자 -->
					</td>
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
					<td><input type="submit" value="등록"></td>
				</tr>
			</table>
		</form>
		<a href="typeofcodelist.htm?code_type=2">리스트</a>
		<hr>
	</div>	