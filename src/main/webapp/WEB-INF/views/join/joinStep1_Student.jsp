<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 
   @Project : InitSpring
   @File name : joinStep1_Student.jsp
   @Author : 송아름
   @Data : 2016.11.22
   @Desc : 학생 회원가입 step1 view
-->

<a href="${pageContext.request.contextPath}/login.htm"><img
	src="${pageContext.request.contextPath}/images/smLogo.png"></a>

<div class="container" style="margin-top: 5%; width: 70%;">
	<div class="row">
		<div class="stepwizard">
			<div class="stepwizard-row">
				<div class="stepwizard-step">
					<a class="btn btn-success btn-circle active-step" data-toggle="tab">1</a>
					<p>Step 1</p>
				</div>
				<div class="stepwizard-step">
					<a class="btn btn-default btn-circle" disabled="disabled"
						data-toggle="tab">2</a>
					<p>Step 2</p>
				</div>
				<div class="stepwizard-step">
					<a class="btn btn-default btn-circle" disabled="disabled"
						data-toggle="tab">3</a>
					<p>Step 3</p>
				</div>
			</div>
		</div>
	</div>
</div>

<div style="border: 1px solid green; padding: 3%; border-radius: 1em; width: 60%; margin: auto;">
	<div class="form-horizontal">
		<div class="form-group">
			<label class="col-sm-2 control-label col-sm-offset-2">이름</label>
			<div class="col-sm-6">
				<input type="text" name="code_name" id="code_name"
					class="form-control">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label col-sm-offset-2">학번</label>
			<div class="col-sm-6">
				<input type="text" name="code" id="code" class="form-control">
			</div>
		</div>

		<div class="form-group">
			<div class="form-inline">
				<label class="col-sm-2 control-label col-sm-offset-2">생년월일</label>
				<div class="col-sm-2">
					<select class="form-control" id="year" style="width: 100%;">
						<option id="year" value="0">년도</option>
						<c:forEach var="i" begin="1940" end="2017">
							<option value="${i}">${i}</option>
						</c:forEach>
					</select>
				</div>

				<div class="col-sm-2">
					<select class="form-control" id="month" style="width: 100%;">
						<option id="month" value="0">월</option>
						<c:forEach var="i" begin="1" end="12">
							<option value="${i}">${i}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-sm-2">
					<select class="form-control" id="day" style="width: 100%;">
						<option id="month" value="0">일</option>
						<c:forEach var="i" begin="1" end="31">
							<option value="${i}">${i}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
	</div>
</div>
<br>
<div align="center">
	<input type="button" value="취소" id="cancel" class="btn btn-default">
	&nbsp;&nbsp; <input type="button" value="다음 단계" id="step1btn" class="btn btn-success">
</div>
