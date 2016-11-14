<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form action="" method="post">
	<table>
		<tr>
			<td>아이디</td>
			<td><input type="text" name="userid" id="userid"></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="pwd" id="pwd"></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" id="name"></td>
		</tr>
		<tr>
			<td>email</td>
			<td><input type="text" name="email" id="email"></td>
		</tr>
		<tr>
			<td>교수 / 학생 선택</td>
			<td><input type="radio" value="1" name="code_type">학생
				<input type="radio" value="2" name="code_type">교수
			</td>
		</tr>
		<tr>
			<td>학번 or 교수번호</td>
			<td><input type="text" name="code"></td>
		</tr>
	</table>
	<input type="submit" value="전송">
</form>

