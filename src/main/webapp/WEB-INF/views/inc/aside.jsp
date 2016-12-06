<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="se"
	uri="http://www.springframework.org/security/tags"%>

<div id="sidebar">
	<ul>
		<image id="asidePicture" class="img-circle" width="150" height="150">
		<se:authorize access="hasRole('ROLE_STUDENT')">
			<li id="asideStuCode">학번 :</li>
			<li id="asideStuName">이름 :</li>
			<li id="asideStuGrade">학년 :</li>
			<li id="asideStuCollege">대학 :</li>
			<li id="asideStuDepartMent">전공 :</li>
		</se:authorize> 
		<se:authorize access="hasRole('ROLE_PROFESSOR')">
			<li id="asideProfCode">교번 :</li>
			<li id="asideProfName">이름 :</li>
			<li id="asideProfCollege">대학 :</li>
			<li id="asideProfDepartMent">전공 :</li>
		</se:authorize> 
		<se:authorize access="hasRole('ROLE_ADMIN')">
			<li id="asideAdminCode">코드 :</li>
			<li id="asideAdminName">이름 :</li>
		</se:authorize> <br>
		<div>
			<se:authorize access="hasAnyRole('ROLE_STUDENT','ROLE_PROFESSOR','ROLE_ADMIN')">
				<a href="${pageContext.request.contextPath}/member/edit.htm"> <input
					type="button" class="btn btn-sm btn-warning" value="회원정보수정">
				</a>
			</se:authorize>
			<se:authorize access="hasRole('ROLE_STUDENT')">
				<button class="btn btn-success btn-sm" id="viewTimetable"
					data-toggle="modal">시간표조회</button>
			</se:authorize>
		</div>
	</ul>

	<div id="sidebar-btn">
		<span></span> <span></span> <span></span>
	</div>
</div>