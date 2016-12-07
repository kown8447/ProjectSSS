<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="http://malsup.github.com/jquery.form.js"></script>

<div class="container" style="width:50%">
   <h4>▶&nbsp;관리자 등록 </h4>
      <br><br>
      <div  style="width:90%; margin: auto;">
		<form action="adminCodeRegister.htm" method="post" id="adminCodeRegister_form" name="adminCodeRegister_form">
			<table>
				<tr>
					<td>코드</td>
					<td>
					<div class="col-sm-6 ">
					<input type="text" name="code" id="code">
					</div>
					</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>
					<div class="col-sm-6 ">
					<input type="text" name="code_name" id="code_name">
					</div>
					</td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td>
					<div class="col-sm-6 ">
						<input type="text" name="code_birth" id="code_birth">
					</div>
					</td>
				</tr>
				<input type="hidden" value="2" name="code_type" id="code_type">
			</table>
		</form>
		</div>
		<div align="center">
			<input type="button" value="등록" class="btn btn-success" id="adminreg">
			<a href="typeofcodelist.htm?code_type=2"><button class="btn btn-default">관리자 목록</button></a>
		</div>
		<hr>
	</div>		

