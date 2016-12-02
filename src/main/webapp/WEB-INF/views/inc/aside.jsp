<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="se"
	uri="http://www.springframework.org/security/tags"%>
<script src="${pageContext.request.contextPath}/js/inc/aside.js"></script>


<ul class="sidebar-nav" style="text-align: center; display: block">
	<div align="right">
		<button class="btn btn-default" id="menu_toggle2">X</button>
	</div>
	<li class="sidebar-brand"></li>
	<image id="asidePicture" class="img-circle" width="150" height="150"
		src="${pageContext.request.contextPath}/profiles/eunji.jpg">
	 <se:authorize
		access="hasRole('ROLE_STUDENT')">
		<li id="asideStuCode">학번 : </li>
		<li id="asideStuName">이름 : </li>
		<li id="asideStuGrade">학년 : </li>
		<li id="asideStuCollege">대학 : </li>
		<li id="asideStuDepartMent">전공 : </li>
	</se:authorize>
	 <se:authorize
		access="hasRole('ROLE_PROFESSOR')">
		<li id="asideProfCode">교번 : </li>
		<li id="asideProfName">이름 : </li>
		<li id="asideProfCollege">대학 : </li>
		<li id="asideProfDepartMent">전공 : </li>
		
	</se:authorize>
	 <se:authorize
		access="hasRole('ROLE_ADMIN')">
		<li id="asideAdminCode">코드 : </li>
		<li id="asideAdminName">이름 : </li>
	</se:authorize>
	<div>
		<se:authorize
			access="hasAnyRole('ROLE_STUDENT','ROLE_PROFESSOR','ROLE_ADMIN')">
			<a href="${pageContext.request.contextPath}/member/edit.htm"> <input
				type="button" class="btn btn-primary" value="정보 수정">
			</a>
		</se:authorize>
		<se:authorize access="hasRole('ROLE_STUDENT')">
			<button class="btn btn-success" id="viewTimetable"
				data-toggle="modal">시간표조회</button>
		</se:authorize>
	</div>
</ul>


