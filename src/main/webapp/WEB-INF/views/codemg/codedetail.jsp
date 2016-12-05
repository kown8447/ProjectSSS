<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${code}" var="c"></c:set>
<form action="updateCode.htm" method="post">
 코드 번호:<input type="text" value="${c.code }" readonly="readonly" name="code" id="code"><br>
 구분:<%-- <input type="text" value="${c.code_type }" name="code_type" id="code_type"><br> --%>
 
 	<c:choose>
 		<c:when test="${c.code_type==0 }">
 			학생
 		</c:when>
 		<c:when test="${c.code_type==1 }">
 			교수
 		</c:when>
 		<c:when test="${c.code_type==0 }">
 			관리자
 		</c:when>
 	</c:choose><br>
 이름:<input type="text" value="${c.code_name }" name="code_name" id="code_name"><br>
 생년월일:<input type="text" value="${c.code_birth }" name="code_birth" id="code_birth"><br>
 <input type="submit" value="수정하기">
 </form>