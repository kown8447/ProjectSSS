<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
   <!-- 
   @Project : InitSpring
   @File name : joinStep3.jsp
   @Author : 김영빈
   @Data : 2016.11.22
   @Desc : 회원가입 마지막 단계 나머지정보 입력 
-->	
<h1>회원가입 Step3</h1>
<form  method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td>아이디</td>
			<td><input type="text" name="member_id" id="member_id" /></td>
			<td><input type="button" id="checkID" value="아이디 중복체크" /></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" id="member_pwd" name="member_pwd" /></td>
		</tr>
		<tr>
			<td>비밀번호확인</td>
			<td><input type="password" name="member_pwd_confirm"
				id="member_pwd_confirm" /></td>
		</tr>
		<tr>
			<td>주소</td>
			<td><input type="text" id="sample6_postcode" name="addr_num" placeholder="우편번호" readonly>
				<input type="button" onclick="sample6_execDaumPostcode()"value="우편번호 찾기"><br>
				<input type="text" id="sample6_address" name="member_addr" placeholder="주소" readonly>
				<input type="text" id="sample6_address2" name="member_addr_detail" placeholder="상세주소"></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="text" id="member_phone" name="member_phone"/></td>
		</tr>
		<tr>
			<td>성별</td>
			<td><input type="radio" value="0" name="member_sex"
				id="member_sex" />남자
				 <input type="radio" value="1" name="member_sex"
				id="member_sex" />여자</td>
		</tr>
		<tr>
			 <td>사진</td>
			 <td>
<input type="file" id="files" name="file"  accept=".gif, .jpg, .png"/>
<div id="list"></div>

</td>
			 
		<td>
		</tr>
		
	</table>
																																		
	<input type="submit" id="complete" value="회원가입" />
</form>





