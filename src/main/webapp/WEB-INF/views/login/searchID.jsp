<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 
   @Project : InitSpring
   @File name : searchID.jsp
   @Author : 김영빈
   @Data : 2016.11.22
   @Desc : 아이디 찾기 view
-->	

<div>
	<table>
		<tr>
			<td>이름</td>
			<td><input type="text" name="member_name" id="member_name" /></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="member_email" id="member_email" /></td>
		</tr>
	</table>
	<input type="button" id="searchIdBtn" value="아이디찾기">
</div>
<div id="result"></div>
