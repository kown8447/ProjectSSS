<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/login" var="loginURL"/>
<form name="f" action="${loginURL}" method="post">
	<table>
		<tr>
			<td>아이디</td><td><input type="text" name="userid"></td>
		</tr>
		<tr>
			<td>비밀번호</td><td><input type="password" name="pwd"></td>
		</tr>
	</table>
	<input type="submit" value="로그인">
</form>