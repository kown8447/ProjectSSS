<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="se" uri="http://www.springframework.org/security/tags" %>

<se:authorize access="hasAnyRole('ROLE_STUDENT','ROLE_PROFESSOR','ROLE_ADMIN')">
<a href="${pageContext.request.contextPath}/index.htm"><img src="${pageContext.request.contextPath}/images/smLogo.png"></a>
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
<se:authorize access="hasAnyRole('ROLE_STUDENT','ROLE_PROFESSOR','ROLE_ADMIN')">
	<a href="${pageContext.request.contextPath}/member/edit.htm">(${LoginUser})회원정보수정</a> ||
</se:authorize>
<se:authorize access="hasAnyRole('ROLE_STUDENT')">
<a href="${pageContext.request.contextPath}/collegeregister/viewmember.htm">학적조회</a>
</se:authorize>

<se:authorize access="hasRole('ROLE_STUDENT')">
	<a href="${pageContext.request.contextPath}/requestcourse/courseMain.htm">수강신청</a>
</se:authorize>
<a href="${pageContext.request.contextPath}/collegeregister/viewmember.htm">학적조회</a> ||

<se:authorize access="hasAnyRole('ROLE_STUDENT','ROLE_PROFESSOR','ROLE_ADMIN')">
	<a href="${pageContext.request.contextPath}/notice/notice.htm">게시판</a>
</se:authorize>
<hr>