<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${pageContext.request.contextPath}/js/requestCourse/searchOtherTimetable.js"></script>
<form class="form-inline" onSubmit="return false">
	<div class="form-group">
		<label for="searchStudent_code">조회할 학생 학번 : </label> 
		<input type="text" class="form-control" name="subject_code" id="search_student_code" placeholder="학번을 입력해 주세요.">
		<input type="button" class="btn btn-default" id="searchScBtn" value="조회"/>
	</div>
	
	<div class="row" style="margin-top:30px" align="center" id="result">
		<table id="timetable_2" class="table table-hover" style="table-layout: fixed;" cellpadding="5" cellspacing="5" align="center">
			<tr>
				<th>시간</th>
				<th>월</th>
				<th>화</th>
				<th>수</th>
				<th>목</th>
				<th>금</th>
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