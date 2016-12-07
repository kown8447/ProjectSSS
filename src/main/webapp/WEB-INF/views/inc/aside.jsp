<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="se"
	uri="http://www.springframework.org/security/tags"%>

<div id="sidebar">
	<ul>
		<br><br><br>
		<image id="asidePicture" class="img-circle" style="width:150px;height:150px;margin:auto;">
		<br><br><br>
		<se:authorize access="hasRole('ROLE_STUDENT')">
			<li id="asideStuCode">▷학번 :</li><br>
			<li id="asideStuName">▷이름 :</li><br>
			<li id="asideStuGrade">▷학년 :</li><br>
			<li id="asideStuCollege">▷대학 :</li><br>
			<li id="asideStuDepartMent">▷전공 :</li>
		</se:authorize> 
		<se:authorize access="hasRole('ROLE_PROFESSOR')">
			<li id="asideProfCode">▷교번 :</li><br>
			<li id="asideProfName">▷이름 :</li><br>
			<li id="asideProfCollege">▷대학 :</li><br>
			<li id="asideProfDepartMent">▷전공 :</li>
		</se:authorize> 
		<se:authorize access="hasRole('ROLE_ADMIN')">
			<li id="asideAdminCode">▷코드 :</li><br>
			<li id="asideAdminName">▷이름 :</li>
		</se:authorize> <br><br><br>
		<div style="margin: auto;">
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
	<br><br><br>
	<div id="sidebar-btn">
		<img src="${pageContext.request.contextPath}/images/side.png">
	</div>
</div>