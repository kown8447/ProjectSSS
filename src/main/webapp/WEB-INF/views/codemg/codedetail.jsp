<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${code}" var="c"></c:set>
<form action="updateCode.htm" method="post">
 코드 번호:<input type="text" value="${c.code }" readonly="readonly" name="code" id="code"><br>
 구분:<%-- <input type="text" value="${c.code_type }" name="code_type" id="code_type"><br> --%>
 	<input type="radio" value="0" name="code_type" id="code_type">학생 
	<input type="radio" value="1" name="code_type" id="code_type">교수 
	<input type="radio" value="2" name="code_type" id="code_type">관리자<br>
 이름:<input type="text" value="${c.code_name }" name="code_name" id="code_name"><br>
 생년월일:<input type="text" value="${c.code_birth }" name="code_birth" id="code_birth"><br>
 <input type="submit" value="수정하기">
 </form>