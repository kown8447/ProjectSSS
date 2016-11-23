<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<select id="searchType">
		<option value="subject_name">과목명</option>
		<option value="subject_code">과목코드</option>
	</select>
	<input type="text" id="keyword"/>
	<input type="button" id="searchBtn" value="검색">
	<span id="result">
	
	</span>


<!-- PR_MON_1 ~ PR_MON_20 -->


	<form action="" method="post">
		<table id="timetable" border="1">
			<tr>
				<th>시간</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th>
			</tr>
			<c:forEach var="i" begin="1" end="20">
				<tr>
					<td>${i}</td>
					<td id="PR_MON_${i}" class="table_ele"></td>
					<td id="PR_TUE_${i}" class="table_ele"></td>
					<td id="PR_WEN_${i}" class="table_ele"></td>
					<td id="PR_THU_${i}" class="table_ele"></td>
					<td id="PR_FRI_${i}" class="table_ele"></td>
				</tr>
			</c:forEach>
		</table>
		시간표 공유 여부 : <input type="radio" id="timetable_share" name="timetable_share" value="1" />공유함 
		<input type="radio" id="timetable_share" name="timetable_share" value="0" checked/>공유안함
		<input type="button" id="requestBtn" value="수강 신청" />
	</form>


<div id="dialog">
	<table border="1">
		<tr>
			<th>과목명</th>
			<th>과목코드</th>
			<th>교수명</th>
			<th>강의실</th>
			<th>강의 시간</th>
			<th>대상 학년</th>
			<th>필수/선택</th>
			<th>정원</th>
			<th>학점</th>
		</tr>
		<tr>
			<td id="subject_name"></td>
			<td id="subject_code"></td>
			<td id="professor_name"></td>
			<td id="classroom_name"></td>
			<td id="period"></td>
			<td id="grade_limit"></td>
			<td id="required_choice"></td>
			<td id="subject_seats"></td>
			<td id="subject_credit"></td>
		</tr>
	</table>
</div>

