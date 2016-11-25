<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="se" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 
   @Project : InitSpring
   @File name : loginFail.jsp
   @Author : 김영빈
   @Data : 2016.11.22
   @Desc : 로그인 실패  view
-->
<h3>로그인 실패 : ${failmessage}</h3><br>
<a href="${pageContext.request.contextPath}/login.htm">메인으로 가기</a><br>
