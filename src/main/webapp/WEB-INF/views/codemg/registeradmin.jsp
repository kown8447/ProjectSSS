<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!-- 
   @Project : InitSpring
   @File name : registeradmin.jsp
   @Author : 성홍모
   @Data : 2016.11.09
   @Desc : 관리자 등록 pageView
-->        
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="http://malsup.github.com/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>

 <div class="container" style="width:65%">
   <h4>▶&nbsp;관리자 등록 </h4><br><br>

		<form action="adminCodeRegister.htm" method="post" id="adminCodeRegister_form" name="adminCodeRegister_form">
		 <div class="form-horizontal">
			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-2">코드</label>
				<div class="col-sm-6">
					<input type="text" name="code" id="code" class="form-control">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-2">이름</label>
				<div class="col-sm-6">
					<input type="text" name="code_name" id="code_name" class="form-control">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-2">생년월일</label>
				<div class="col-sm-6">
					<input type="text" name="code_birth" id="code_birth" class="form-control" placeholder="ex)1993-01-15">
				</div>
			</div>
			</div>
		</form>

	<br><br>
		<div align="center">
			<a href="typeofcodelist.htm?code_type=2"class="btn btn-default">관리자 목록</a>
			<input type="button" value="등록" class="btn btn-success" id="adminreg">
		</div>
		<div style="height: 15%;"></div>
	</div>
	

