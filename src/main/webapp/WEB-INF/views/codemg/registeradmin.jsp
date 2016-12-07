<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="http://malsup.github.com/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>

<div class="container" style="width:50%">
   <h4>▶&nbsp;관리자 등록 </h4>
      <br><br>
      <div  style="width:90%; margin: auto;">
		<form action="adminCodeRegister.htm" method="post" id="adminCodeRegister_form" name="adminCodeRegister_form">
			<table class="table">
				<tr>
					<td>코드</td>
					<td>
						<div class="col-sm-6 ">
							<input type="text" name="code" id="code" class="form-control">
						</div>
					</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>
						<div class="col-sm-6 ">
							<input type="text" name="code_name" id="code_name" class="form-control">
						</div>
					</td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td>
						<div class="col-sm-6 ">
							<input type="text" name="code_birth" id="code_birth" class="form-control">
						</div>
					</td>
				</tr>
				
			</table>
		</form>
		</div>
		<div align="center">
			<a href="typeofcodelist.htm?code_type=2"><button class="btn btn-default">관리자 목록</button></a>
			<input type="button" value="등록" class="btn btn-success" id="adminreg">
		</div>
		<hr>
	</div>		

