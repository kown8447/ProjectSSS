<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<script src="${pageContext.request.contextPath}/js/inc/aside.js"></script>


<ul class="sidebar-nav" style="text-align: center; display: block">
	<div align="right"><button class="btn btn-default" id="menu_toggle2">X</button></div>
	<li class="sidebar-brand"></li>
	<image class="img-circle" width="150" height="150" src="${pageContext.request.contextPath}/profiles/eunji.jpg">
	<li>학번 : 201132368</li>
	<li>이름 : 정은지</li>
	<li>학년 : 2</li>
	<li>학과 : 실용음악과</li>
	<li>전공 : 보컬</li>
	<div>
		<se:authorize access="hasAnyRole('ROLE_STUDENT','ROLE_PROFESSOR','ROLE_ADMIN')">
			<a href="${pageContext.request.contextPath}/member/edit.htm">
				<input type="button" class="btn btn-primary" value="정보 수정">
			</a>
		</se:authorize>
		<se:authorize access="hasRole('ROLE_STUDENT')">
			<button class="btn btn-success" id="viewTimetable" data-toggle="modal">시간표조회</button>
		</se:authorize>
	</div>
</ul>

	
