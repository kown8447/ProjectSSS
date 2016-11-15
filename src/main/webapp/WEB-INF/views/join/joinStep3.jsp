<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<h1>회원가입 Step3</h1>
<form method="post">
	<table>
		<tr>
			<td>아이디</td>
			<td><input type="text" name="userid" id="userid" /></td>
			<td><input type="button" id="checkID" value="아이디 중복체크" /></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" id="password" name="pwd" /></td>
		</tr>
	</table>
	<input type="submit" id="complete" value="회원가입"/>
</form>
