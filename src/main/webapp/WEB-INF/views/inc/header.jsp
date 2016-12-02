<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="se"
	uri="http://www.springframework.org/security/tags"%>
<link href="${pageContext.request.contextPath}/css/header.css"
	rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/inc/header.js"></script>


<se:authorize
	access="hasAnyRole('ROLE_STUDENT','ROLE_PROFESSOR','ROLE_ADMIN')">
	<a href="${pageContext.request.contextPath}/index.htm"><img
		src="${pageContext.request.contextPath}/images/smLogo.png"></a>
</se:authorize>

<se:authentication property="name" var="LoginUser" />
<div class="header_top"></div>
<div class="menu">
	<div class="navbar navbar-inner">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#main-menu">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<se:authorize
					access="hasAnyRole('ROLE_STUDENT','ROLE_PROFESSOR','ROLE_ADMIN')">
					<a href="${pageContext.request.contextPath}/index.htm"> <img
						src="${pageContext.request.contextPath}/images/smLogo.png"></a>
				</se:authorize>
				<se:authorize
					access="hasAnyRole('ROLE_STUDENT','ROLE_PROFESSOR','ROLE_ADMIN')">
					<font style="font-size: 9pt; color: #A1A1A1;">${LoginUser}님
						환영합니다 &nbsp;|&nbsp; <a
						href="${pageContext.request.contextPath}/logout">로그아웃</a>
					</font>
				</se:authorize>
			</div>
			<div class="collapse navbar-collapse pull-right" id="main-menu">
				<ul class="nav">
					<li class="dropdown fadeInDown animated d3"><se:authorize
							access="hasAnyRole('ROLE_STUDENT')">
							<a
								href="${pageContext.request.contextPath}/collegeregister/viewmember.htm">학적조회</a>
						</se:authorize>
						<ul class="firstlevel dropdown-menu sub-menu"
							style="display: none;">
							<li class="twolevel"><a
								href="${pageContext.request.contextPath}/collegeregister/viewstudent.htm"
								menuid="10" followlink="true">개인정보</a></li>
							<li><a
								href="${pageContext.request.contextPath}/collegeregister/record.htm"
								menuid="10" followlink="true">성적조회</a></li>
							<li class="twolevel"><a
								href="${pageContext.request.contextPath}/collegeregister/register.htm">등록/장학</a>
							</li>
							<li><a
								href="${pageContext.request.contextPath}/collegeregister/record.htm">신청</a>
							</li>
						</ul></li>


					<li class="dropdown fadeInDown animated d3"><se:authorize
							access="hasRole('ROLE_STUDENT')">
							<a
								href="${pageContext.request.contextPath}/requestcourse/courseMain.htm">수강신청</a>
						</se:authorize>
						<ul class="firstlevel dropdown-menu sub-menu"
							style="display: none;">
							<li><a
								href="${pageContext.request.contextPath}/requestcourse/courseMain.htm">수강안내</a>
							</li>
							<li><a
								href="${pageContext.request.contextPath}/requestcourse/viewOpLecture.htm">개설강의</a>
							</li>
							<li><a
								href="${pageContext.request.contextPath}/requestcourse/preRegister.htm">예비
									수강신청</a></li>
							<li><a
								href="${pageContext.request.contextPath}/requestcourse/searchOtherTimetable.htm">타학생
									예비 시간표 조회</a></li>
							<li><a
								href="${pageContext.request.contextPath}/requestcourse/realRegiser.htm">수강신청</a>
							</li>
							<li><a
								href="${pageContext.request.contextPath}/requestcourse/correctRegiser.htm">수강정정</a>
							</li>
						</ul></li>
			<li class="dropdown fadeInDown animated d3">
					<se:authorize access="hasAnyRole('ROLE_PROFESSOR')">
						<a
							href="${pageContext.request.contextPath}/lecture/lectureView.htm">교수쓰</a>
					</se:authorize>
					<se:authorize access="hasAnyRole('ROLE_PROFESSOR')">
						<a
							href="${pageContext.request.contextPath}/lecture/lectureMyclass.htm">성적입력</a>
					</se:authorize>
					</li>

					<li class="dropdown fadeInDown animated d2"><se:authorize
							access="hasAnyRole('ROLE_STUDENT','ROLE_PROFESSOR','ROLE_ADMIN')">
							<a href="${pageContext.request.contextPath}/notice/notice.htm">게시판</a>
						</se:authorize>
						<ul class="firstlevel dropdown-menu sub-menu"
							style="display: none;">
							<li><a
								href="${pageContext.request.contextPath}/notice/notice.htm">공지사항</a>
							</li>
							<li><a
								href="${pageContext.request.contextPath}/qnanotice/qnanotice.htm">Q&A게시판</a>
							</li>
							<li><a
								href="${pageContext.request.contextPath}/schedule/schedule.htm">학사일정</a>
							</li>
						</ul></li>

					<li class="dropdown fadeInDown animated d2"><se:authorize
							access="hasAnyRole('ROLE_STUDENT','ROLE_PROFESSOR','ROLE_ADMIN')">
							<a href="${pageContext.request.contextPath}/favorite/config.htm">즐겨찾기
								설정</a>
						</se:authorize>
						<ul class="firstlevel dropdown-menu sub-menu" id="favoriteList"
							style="display: none;">

						</ul></li> 
					<se:authorize
						access="hasAnyRole('ROLE_STUDENT','ROLE_PROFESSOR','ROLE_ADMIN')">
						<a href="${pageContext.request.contextPath}/logout">(${LoginUser})로그아웃</a>
					</se:authorize>

					<se:authorize access="hasRole('ROLE_STUDENT')">
						<a
							href="${pageContext.request.contextPath}/collegeregister/viewmember.htm">MY
							Page</a>
					</se:authorize>
					<se:authorize
						access="hasAnyRole('ROLE_STUDENT','ROLE_PROFESSOR','ROLE_ADMIN')">
						<a href="${pageContext.request.contextPath}/member/edit.htm">(${LoginUser})회원정보수정</a> ||
</se:authorize>
					
					<hr>
				</ul>
			</div>
		</div>
	</div>
</div>