<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<h1>회원가입 Step3</h1>
<form method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td>아이디</td>
			<td><input type="text" name="member_id" id="member_id" /></td>
			<td><input type="button" id="checkID" value="아이디 중복체크" /></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" id="member_pwd" name="member_pwd" id="member_pwd"/></td>
		</tr>
		<tr>
			<td>주소</td>
			<td><input type="text" id="member_addr" name="member_addr" id="member_addr"/></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="text" id="member_phone" name="member_phone" id="member_phone"/></td>
		</tr>		
		<tr>
			<td>성별</td>
			<td>
				<input type="radio" value="0" name="member_sex" id="member_sex"/>남자
				<input type="radio" value="1" name="member_sex" id="member_sex"/>여자
			</td>
		</tr>		
		<tr>
			<td>사진</td>
			<td>
				<input type="file" name="file" id="file">
			</td>
		</tr>			
	</table>
	<input type="submit" id="complete" value="회원가입"/>
</form>
