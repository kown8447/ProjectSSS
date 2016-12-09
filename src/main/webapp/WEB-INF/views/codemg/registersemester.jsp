<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!-- 
   @Project : InitSpring
   @File name : registersemester.jsp
   @Author : 성홍모
   @Data : 2016.11.09
   @Desc : 학기를 등록하는 pageView
-->       
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="http://malsup.github.com/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<div class="container" style="width:65%">
	<h4>▶&nbsp;학기 등록 </h4><br><br>
	
		<form action="insertSemester.htm" method="post" id="insertSemester_form">
			<div class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-2 control-label col-sm-offset-2">학기코드</label>
					<div class="col-sm-6">
						<input type="text" name="semester_code" id="semester_code" class="form-control">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label col-sm-offset-2">학기이름</label>
					<div class="col-sm-6">
						<input type="text" name="semester_name" id="semester_name" class="form-control">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label col-sm-offset-2">학기시작일</label>
					<div class="col-sm-6">
						<input type="text" name="semester_start" id="semester_start" class="form-control">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label col-sm-offset-2">학기종료일</label>
					<div class="col-sm-6">
						<input type="text" name="semester_end" id="semester_end" class="form-control">
					</div>
				</div>
			</div>
		</form>
		<br><br>
		<div align="center">
			<a href="semesterList.htm"class="btn btn-default">학기목록</a>
			<input type="button" value="등록" class="btn btn-success" id="semester_reg">
			<br><br><br>
			<div style="border: 1px solid red; padding: 2%; border-radius: 1em; width: 65%;">
				<p align="center" style="color:blue;font-size: 13pt;">*취 급 주 의*</p>
				<input type="button" id="initBtn" value="학기 초기화" class="btn btn-danger" align="center">
			</div>
		</div>
		
	</div>
