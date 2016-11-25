<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 
   @Project : InitSpring
   @File name : login.jsp
   @Author : 김영빈
   @Data : 2016.11.22
   @Desc : 로그인  view
-->
<c:url value="/login" var="loginURL"/>
<form name="f" action="${loginURL}" method="post">
	<table>
		<tr>
			<td>아이디</td><td><input type="text" name="member_id"></td>
		</tr>
		<tr>
			<td>비밀번호</td><td><input type="password" name="member_pwd"></td>
		</tr>
	</table>
	<input type="submit" value="로그인">
</form><br>
<a href="${pageContext.request.contextPath}/login/searchID.htm">아이디 찾기</a> || 
<a href="${pageContext.request.contextPath}/login/searchPwd.htm">비밀번호 찾기</a> || 