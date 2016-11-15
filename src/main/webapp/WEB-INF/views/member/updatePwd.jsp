<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<h3>새로운 비밀번호로 변경합니다.</h3>
<form method="post">
	<table>
		<tr>
			<td>변경할 비밀번호 :</td>
			<td><input type="password" name="password" id="password"></td>
		</tr>
		<tr>
			<td>비밀번호 확인 :</td>
			<td><input type="password" name="password2" id="password2"></td>
		</tr>
	</table>
	<input type="submit" value="비밀번호 변경" id="updatePwdBtn"/>
</form>
