<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="se" uri="http://www.springframework.org/security/tags" %>
<!-- 
   @Project : InitSpring
   @File name : login.jsp
   @Author : 송아름
   @Data : 2016.11.25
   @Desc : 로그인  view
-->
<div id="content">
	<div class="row" style="margin-left: 30%;">
		<img src="images/mainLogo.png">
	</div>

	<c:url value="/login" var="loginURL" />
	<form class="login-form" name="f" action="${loginURL}" method="post">
		<div class="flex-row">
			<label class="lf--label" for="username"> 
				<img src="images/idIcon.png" width="20px">
			</label> 
			<input type="text" name="member_id" id="member_id" class="lf--input" placeholder="MemberID">
		</div>
		
		<div class="flex-row">
			<label class="lf--label" for="password"> 
			<img src="images/pwIcon.png" width="27px">
			</label> 
			<input type="password" name="member_pwd" id="member_pwd" class="lf--input" placeholder="Password">
		</div>
		<input class="lf--submit" type="submit" value="LOGIN" id="loginBtn">
		<br>
		<p style="text-align: center; color: white; font-size: 10pt">
			Not a member?
			<se:authorize access="!hasAnyRole('ROLE_STUDENT','ROLE_PROFESSOR','ROLE_ADMIN')">
				<a href="${pageContext.request.contextPath}/join/join.htm">Sing UP</a>
			</se:authorize>

		</p>

		<p style="text-align: center; font-size: 8pt">
			<a href="${pageContext.request.contextPath}/login/searchID.htm" id="searchID">아이디 찾기</a> / 
			<div class="modal fade">
				<div class="modal-dialog">
   					 <div class="modal-content">
        			<!-- remote ajax call이 되는영역 -->
    				</div>
 				 </div>
			</div>
			
			<a href="${pageContext.request.contextPath}/login/searchPwd.htm" id="searchPW">비밀번호 찾기</a>
		
	</form>
	
</div>