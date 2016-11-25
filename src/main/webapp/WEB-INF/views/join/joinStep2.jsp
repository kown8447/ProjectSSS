<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 
   @Project : InitSpring
   @File name : joinStep2.jsp
   @Author : 김영빈
   @Data : 2016.11.22
   @Desc :  회원가입 3번째 view 이메일 인증 
-->    
<h1>회원가입 Step2</h1>
<table>
	<tr>
		<td>이메일</td>
		<td><input type="text" name="member_email" id="member_email"/></td>
		<td><input type="button" id="auth_email" value="인증요청하기"/></td>
	</tr>
	<tr>
		<td>인증번호 입력</td>
		<td><input type="text" id="auth_key"/></td>
		<td><input type="button" id="step2btn" value="다음 단계"/></td>
	</tr>
</table>