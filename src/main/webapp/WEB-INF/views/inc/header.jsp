<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="se"
	uri="http://www.springframework.org/security/tags"%>
<hr>
<h3>
	<a href="${pageContext.request.contextPath}/index.htm">헤더 입니다.(메인)</a>
</h3>
<se:authorize
	access="!hasAnyRole('ROLE_STUDENT','ROLE_PROFESSOR','ROLE_ADMIN')">
	<a href="${pageContext.request.contextPath}/join/join1.htm">회원가입</a>
</se:authorize>

<se:authorize
	access="!hasAnyRole('ROLE_STUDENT','ROLE_PROFESSOR','ROLE_ADMIN')">
	<a href="${pageContext.request.contextPath}/login/login.htm">로그인</a>
</se:authorize>

<se:authentication property="name" var="LoginUser" />

<se:authorize
	access="hasAnyRole('ROLE_STUDENT','ROLE_PROFESSOR','ROLE_ADMIN')">
	<a href="${pageContext.request.contextPath}/logout">(${LoginUser})로그아웃</a>
</se:authorize>

<se:authorize access="hasRole('ROLE_STUDENT')">
	<a
		href="${pageContext.request.contextPath}/collegeregister/viewmember.htm">MY
		Page</a>
</se:authorize>

<se:authorize access="hasRole('ROLE_STUDENT')">
	<a
		href="${pageContext.request.contextPath}/requestcourse/courseMain.htm">수강신청</a>
</se:authorize>
<se:authorize access="hasRole('ROLE_STUDENT')">
	<a
		href="${pageContext.request.contextPath}/collegeregister/viewmember.htm">학적조회</a>
</se:authorize>
<se:authorize access="hasRole('ROLE_ADMIN')">
	<a href="${pageContext.request.contextPath}/member/code.htm">코드관리</a>
	<a href="${pageContext.request.contextPath}/oprequest/list.htm">개설과목
		관리</a>
</se:authorize>

<hr>