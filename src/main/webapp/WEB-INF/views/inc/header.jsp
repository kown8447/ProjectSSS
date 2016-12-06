<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="se" uri="http://www.springframework.org/security/tags"%>
<link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/inc/header.js"></script>

<se:authentication property="name" var="LoginUser" />
<div class="header_top"></div>
<div class="menu">
	<div class="navbar navbar-inner">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#main-menu">
					<span class="sr-only">Toggle navigation</span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
				</button>
				<se:authorize access="hasAnyRole('ROLE_STUDENT','ROLE_PROFESSOR','ROLE_ADMIN')">
					<a href="${pageContext.request.contextPath}/index.htm"> 
					<img src="${pageContext.request.contextPath}/images/smLogo.png"></a>
				</se:authorize>
				<se:authorize access="hasAnyRole('ROLE_STUDENT','ROLE_PROFESSOR','ROLE_ADMIN')">
					<font style="font-size: 9pt; color: #A1A1A1;">${LoginUser}님
						환영합니다 &nbsp;|&nbsp; 
						<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
					</font>
				</se:authorize>
			</div>
			<div class="collapse navbar-collapse pull-right" id="main-menu">
				<ul class="nav">
					<li class="dropdown fadeInDown animated d3">
						<se:authorize access="hasAnyRole('ROLE_STUDENT')">
							<a href="#">학적조회</a>
						</se:authorize>
						<ul class="firstlevel dropdown-menu sub-menu" style="display: none;">
							<li class="twolevel">
								<a href="${pageContext.request.contextPath}/collegeregister/viewstudent.htm" menuid="10" followlink="true">개인정보</a></li>
							<li>
								<a href="${pageContext.request.contextPath}/collegeregister/record.htm" menuid="10" followlink="true">성적조회</a></li>
							<li class="twolevel">
								<a href="${pageContext.request.contextPath}/collegeregister/register.htm">등록/장학</a></li>
						</ul>
					</li>


					<li class="dropdown fadeInDown animated d3">
						<se:authorize access="hasRole('ROLE_STUDENT')">
							<a href="#">수강신청</a>
						</se:authorize>
						<ul class="firstlevel dropdown-menu sub-menu" style="display: none;">
							<li><a href="${pageContext.request.contextPath}/requestcourse/courseMain.htm">수강안내</a></li>
							<li><a href="${pageContext.request.contextPath}/requestcourse/viewOpLecture.htm">개설강의</a></li>
							<li><a href="${pageContext.request.contextPath}/requestcourse/preRegister.htm">예비수강신청</a></li>
							<li><a href="${pageContext.request.contextPath}/requestcourse/searchOtherTimetable.htm">타학생 예비 시간표 조회</a></li>
							<li><a href="${pageContext.request.contextPath}/requestcourse/realRegiser.htm">수강신청</a></li>
							<li><a href="${pageContext.request.contextPath}/requestcourse/correctRegiser.htm">수강정정</a></li>
						</ul>
					</li>
						
					<li class="dropdown fadeInDown animated d3">
						<se:authorize access="hasRole('ROLE_PROFESSOR')">
							<a href="#">교수 메뉴</a>
						</se:authorize>
						<ul class="firstlevel dropdown-menu sub-menu" style="display: none;">
							<li>
								<a href="${pageContext.request.contextPath}/lecture/lectureView.htm">강의 등록</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/lecture/lectureMyclass.htm">성적입력</a>
							</li>
						</ul>
					</li>
					
					<se:authorize access="hasRole('ROLE_ADMIN')">
						<%-- <li class="dropdown fadeInDown animated d2">
							<a href="#">관리자 메뉴</a>
							<ul class="firstlevel dropdown-menu sub-menu" id="adminMenu" style="display: none;">
								<li><a href="${pageContext.request.contextPath}/member/code.htm">코드관리</a></li>
								<li><a href="${pageContext.request.contextPath}/oprequest/list.htm">개설과목 관리</a></li>
								<li><a href="${pageContext.request.contextPath}/member/registerstructure.htm">건물 관리</a></li>
							</ul>
						</li> --%>
						<li class="dropdown fadeInDown animated d2">
							<a href="#">건물관리</a>
							<ul class="firstlevel dropdown-menu sub-menu" id="adminMenu" style="display: none;">
								<li><a href="${pageContext.request.contextPath}/member/registerbuilding.htm">건물 관리</a></li>
								<li><a href="${pageContext.request.contextPath}/member/registerclassroom.htm">강의실 관리</a></li>
								<li><a href="${pageContext.request.contextPath}/member/registeroffice.htm">사무실 관리</a></li>
								<li><a href="${pageContext.request.contextPath}/member/registerlaboratory.htm">연구실 관리</a></li>
							</ul>
						</li>
						<li class="dropdown fadeInDown animated d2">
							<a href="#">학생관리</a>
							<ul class="firstlevel dropdown-menu sub-menu" id="adminMenu" style="display: none;">
								<li><a href="${pageContext.request.contextPath}/member/registerstudent.htm">학생등록</a></li>
								<li><a href="${pageContext.request.contextPath}/member/registerregister.htm">등록</a></li>
								<li><a href="${pageContext.request.contextPath}/member/registercollege.htm">단대 등록</a></li>
								<li><a href="${pageContext.request.contextPath}/member/registerdepartment.htm">학부 등록</a></li>
								<li><a href="${pageContext.request.contextPath}/member/registerscsystem.htm">장학제도 등록</a></li>
								<li><a href="${pageContext.request.contextPath}/member/registerscholarship.htm">장학 등록</a></li>
								<li><a href="${pageContext.request.contextPath}/member/registermjrecord.htm">부전공 등록</a></li>
							</ul>
						</li>
						<li class="dropdown fadeInDown animated d2">
							<a href="#">교수관리</a>
							<ul class="firstlevel dropdown-menu sub-menu" id="adminMenu" style="display: none;">
								<li><a href="${pageContext.request.contextPath}/member/registerprofessor.htm">교수등록</a></li>
								<li><a href="${pageContext.request.contextPath}/oprequest/list.htm">개설신청 과목 승인</a></li>
								<li><a href="${pageContext.request.contextPath}/member/registeropen.htm">개설신청 현황</a></li>
							</ul>
						</li>
						<li class="dropdown fadeInDown animated d2">
							<a href="#">기타</a>
							<ul class="firstlevel dropdown-menu sub-menu" id="adminMenu" style="display: none;">
								<li><a href="${pageContext.request.contextPath}/member/registeradmin.htm">관리자 등록</a></li>
								<li><a href="${pageContext.request.contextPath}/member/registersemester.htm">학기 관리</a></li>
							</ul>
						</li>
					</se:authorize>

					<li class="dropdown fadeInDown animated d2">
						<se:authorize access="hasAnyRole('ROLE_STUDENT','ROLE_PROFESSOR','ROLE_ADMIN')">
							<a href="#">게시판</a>
						</se:authorize>
						
						<ul class="firstlevel dropdown-menu sub-menu" style="display: none;">
							<li><a href="${pageContext.request.contextPath}/notice/notice.htm">공지사항</a></li>
							<li><a href="${pageContext.request.contextPath}/qnanotice/qnanotice.htm">Q&A게시판</a></li>
							<li><a href="${pageContext.request.contextPath}/schedule/schedule.htm">학사일정</a></li>
						</ul>
					</li>

					<li class="dropdown fadeInDown animated d2">
						<se:authorize access="hasAnyRole('ROLE_STUDENT','ROLE_PROFESSOR','ROLE_ADMIN')">
							<a href="${pageContext.request.contextPath}/favorite/config.htm">즐겨찾기</a>
						</se:authorize>
						<ul class="firstlevel dropdown-menu sub-menu" id="favoriteList" style="display: none;">
							
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>

					