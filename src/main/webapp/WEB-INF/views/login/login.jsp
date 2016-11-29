<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="se"
	uri="http://www.springframework.org/security/tags"%>
<!-- 
   @Project : InitSpring
   @File name : login.jsp
   @Author : 송아름
   @Data : 2016.11.25
   @Desc : 로그인  view
-->

<div class="container">
	<div align="center" style="margin-top: 5%;">
		<img src="${pageContext.request.contextPath}/images/mainLogo.png" class="img-responsive">
	</div>


	<c:url value="/login" var="loginURL" />
	<div class="wrapper">
		<form class="form-signin" name="f" action="${loginURL}" method="post">
			<div class="input-group">
				<span class="input-group-addon" id="sizing-addon1"> 
					<i class="fa fa-user"></i>
				</span> 
				<input type="text" name="member_id" id="member_id" class="form-control f-input" placeholder="MemberID">
			</div>

			<div class="input-group">
				<span class="input-group-addon" id="sizing-addon1"> 
					<i class="fa fa-lock"></i>
				</span> 
				<input type="password" name="member_pwd" id="member_pwd" class="form-control f-input" placeholder="Password">
			</div>

			<input class="btn btn-block" type="submit" value="LOGIN" id="loginBtn"
				style="background: linear-gradient(to right, #35c3c1, #47C83E)">
			<br>

			<p style="text-align: center; font-size: 10pt">
				Not a member?
				<se:authorize
					access="!hasAnyRole('ROLE_STUDENT','ROLE_PROFESSOR','ROLE_ADMIN')">
					<a href="${pageContext.request.contextPath}/join/join.htm">Sing UP</a>
				</se:authorize>
			</p>

			<p style="text-align: center; font-size: 8pt">
				<a href="#" id="searchID" data-toggle="modal">아이디 찾기</a> / 
				<a href="#" id="searchPW" data-toggle="modal">비밀번호 찾기</a>
			</p>
		</form>
	</div>
</div>

<div class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- remote ajax call이 되는영역 -->
		</div>
	</div>
</div>
