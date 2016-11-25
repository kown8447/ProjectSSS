<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
성적조회
<div>
	<select class="recordSelect" id="recordGrade"><option
			value="default">학년</option>
		<c:forEach begin="1" end="${state.grade}" varStatus="status">
			<option value="${status.count}">${status.count}</option>
		</c:forEach>
	</select> <select class="recordSelect" id="recordSemester">
		<option value="default">학기</option>
	</select> <input type="hidden" id="maxGrade" value="${state.grade}"> <input
		type="hidden" id="maxSemester" value="${state.personal_semester}">
</div>
<div>
	<table id="recordView">
		<tr>
			<th>구분</th>
			<th>과목코드</th>
			<th>과목명</th>
			<th>학점</th>
			<th>성적</th>
			<th>재수강</th>
		</tr>
		<c:forEach var="record" items="${recordList}">
			<tr>
				<td>${record.stringtype}</td>
				<td>${record.subject_code}</td>
				<td>${record.subject_name}</td>
				<td>${record.subject_credit}</td>
				<td>${record.record_level}</td>
				<td><c:choose>
						<c:when test="${record.retake_check==1}">재수강</c:when>
						<c:otherwise></c:otherwise>
					</c:choose></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="4">전공: ${majorCredit}&nbsp; &nbsp; 교양:
				${liberalCredit}&nbsp; &nbsp; 복수전공: ${doubleCredit}&nbsp; &nbsp; 총
				이수학점:${totalCredit}&nbsp;</td>
			<td>평점(F포함 계산):${inF}</td>
			<td>평점(F제외):${outF}</td>
		</tr>
	</table>
</div>