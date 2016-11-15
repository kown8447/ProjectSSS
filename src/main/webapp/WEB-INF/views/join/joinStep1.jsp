<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<h1>회원가입 Step1</h1>
<table>
	<tr>
		<td>교수 / 학생 선택</td>
		<td><input type="radio" value="1" name="code_type" id="code_type">학생
			<input type="radio" value="2" name="code_type" id="code_type">교수
		</td>
	</tr>
	<tr>
		<td>학번 or 교수번호</td>
		<td><input type="text" name="code" id="code"></td>
	</tr>
	<tr>
		<td>이름</td>
		<td><input type="text" name="code_name" id="code_name"></td>
	</tr>
	<tr>
		<td>생년월일</td>
		<td><input type="text" name="code_birth" id="code_birth"></td>
	</tr>
</table>
<input type="button" value="다음 단계" id="step1btn">