<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="http://malsup.github.com/jquery.form.js"></script>

<div class="container" style="width:50%">
	<h4>▶&nbsp;학기 등록 </h4>
      <br><br>
      <div  style="width:90%; margin: auto;">
		<form action="insertSemester.htm" method="post" id="insertSemester_form">
			<table>
				<tr>
					<td>학기코드</td>
					<td><input type="text" name="semester_code" id="semester_code" class="form-control"></td>
				</tr>
				<tr>
					<td>학기 이름</td>
					<td>	
						<input type="text" name="semester_name" id="semester_name" class="form-control">
					</td>
				</tr>
				<tr>
					<td>학기 시작일</td>
					<td>
					<input type="text" name="semester_start" id="semester_start" class="form-control">
					</td>
				</tr>
				<tr>
					<td>학기 종료일</td>
					<td>
					<input type="text" name="semester_end" id="semester_end" class="form-control">
					</td>
				</tr>
			</table>
		</form>
		<div align="center">
			<a href="semesterList.htm"><button class="btn btn-default">학기목록</button></a>
			<input type="button" id="initBtn" value="학기 초기화" class="btn btn-default"/>
			<input type="button" value="등록" class="btn btn-success" id="semester_reg">
		</div>
		<hr>
	</div>
</div>
