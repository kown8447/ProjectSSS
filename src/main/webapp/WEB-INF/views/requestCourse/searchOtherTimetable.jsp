<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${pageContext.request.contextPath}/js/requestCourse/searchOtherTimetable.js"></script>
<h4 style="margin-left: 10%">
	<span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>&nbsp;타학생 시간표 조회</h4>
<br><br>

<form class="form-inline" onSubmit="return false">
	<div class="form-group" style="margin-left:20%; ">
		<input type="text" class="form-control" name="subject_code" id="search_student_code" placeholder="조회하실 학생의 학번을 입력해 주세요.">
		<input type="button" class="btn btn-default" id="searchScBtn" value="조회"/>
	</div>
	
	<div class="row" style="margin-top:30px" align="center" id="result">
		<table id="timetable_2" class="table table-hover" style="table-layout: fixed;" cellpadding="5" cellspacing="5" align="center">
			<tr>
				<th style="text-align: center">시간</th>
				<th style="text-align: center">월</th>
				<th style="text-align: center">화</th>
				<th style="text-align: center">수</th>
				<th style="text-align: center">목</th>
				<th style="text-align: center">금</th>
			</tr>
			<c:forEach var="i" begin="1" end="20">
				<tr>
					<td id="PERIOD_START_${i}_2" style="word-break: break-all;"></td>
					<td id="PR_MON_${i}_2" class="table_ele_2" height="auto" style="word-break: break-all;"></td>
					<td id="PR_TUE_${i}_2" class="table_ele_2" height="auto" style="word-break: break-all;"></td>
					<td id="PR_WEN_${i}_2" class="table_ele_2" height="auto" style="word-break: break-all;"></td>
					<td id="PR_THU_${i}_2" class="table_ele_2" height="auto" style="word-break: break-all;"></td>
					<td id="PR_FRI_${i}_2" class="table_ele_2" height="auto" style="word-break: break-all;"></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</form>