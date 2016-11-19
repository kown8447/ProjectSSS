<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<form action="codeRegister.htm" method="post">   
<table>
	<tr>
		<td>코드</td>
		<td>
			<input type="text" name="code" id="code">
		</td>
	</tr>
	<tr>
		<td>구분</td>
		<td>
			<input type="radio" value="0">학생
			<input type="radio" value="1">교수
			<input type="radio" value="2">관리자
		</td>
	</tr>
	<tr>
		<td>이름</td>
		<td>
			<input type="text" name="code_name" id="code_name">
		</td>
	</tr>
	<tr>
		<td>생년월일</td>
		<td>
			<input type="text" name="code_birth" id="code_birth">
		</td>
	</tr>
</table>
<input type="submit" value="등록">
</form>
<a href="codelist.htm">리스트</a>