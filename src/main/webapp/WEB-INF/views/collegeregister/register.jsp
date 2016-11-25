<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
등록/장학

<div id="studentRegisterTabs">
	<ul>
		<li><a href="#studentRegister">등록</a></li>
		<li><a href="#studentSemesterState">재학</a></li>
		<li><a href="#studentScholaship">장학</a></li>
		<li><a href="#studentAbsence">휴학</a></li>
	</ul>
	<div id="studentRegister">
		<table>
			<tr>
				<th>년도</th>
				<th>분기</th>
				<th>기간</th>
				<th>등록유형</th>
				<th>등록금액</th>
				<th>등록상태</th>
			</tr>
			<c:forEach var="regist" items="${registerList}">
				<tr>
					<td>${regist.semesterYear}</td>
					<td>${regist.semesterType}</td>
					<td>${regist.semester_start}~${regist.semester_end}</td>
					<td><c:choose>
							<c:when test="${regist.register_type==1}">
				 계절학기
				</c:when>
							<c:when test="${regist.register_type==2}">
				졸업연기
				</c:when>
							<c:otherwise>
				일반
				</c:otherwise>
						</c:choose></td>
					<td>${regist.tuition}</td>
					<td><c:choose>
							<c:when test="${regist.register_state==1}">
				 등록
				</c:when>

							<c:otherwise>
				미납
				</c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div id="studentSemesterState">
		<table>
			<tr>
				<th>년도</th>
				<th>분기</th>
				<th>학년</th>
				<th>학기</th>
				<th>해당학기 신청학점</th>
				<th>해당학기 이수학점</th>
			</tr>
			<c:forEach var="semester" items="${studentSemesterList}">
				<tr>
					<td>${semester.semesterYear}</td>
					<td>${semester.semesterType}</td>
					<td>${semester.student_grade}</td>
					<td>${semester.student_semester}</td>
					<td>${semester.request_credit}</td>
					<td>${semester.get_credit}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div id="studentScholaship">
		<table>
			<tr>
				<th>번호</th>
				<th>년도</th>
				<th>분기</th>
				<th>장학명</th>
				<th>장학금액</th>
				<th>선발기준</th>
			</tr>
			<c:forEach var="scholarship" items="${scholarshipList}">
				<tr>
					<td>${scholarship.scholashipIndex}</td>
					<td>${scholarship.semesterYear}</td>
					<td>${scholarship.semesterType}</td>
					<td>${scholarship.scholaship_name}</td>
					<td>${scholarship.scholaship_amount}</td>
					<td>${scholarship.scholaship_standard}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div id="studentAbsence">
		<table>
			<tr>
				<th>번호</th>
				<th>휴학신청일</th>
				<th>휴학기간(학기)</th>
				<th>휴학사유</th>
				<th>복학예정일</th>
			</tr>
			<c:forEach var="absence" items="${absenceList}">
				<tr>
					<td>${absence.absenceIndex}</td>
					<td>${absence.absence_date}</td>
					<td>${absence.absence_term}</td>
					<td>${absence.absence_reason}</td>
					<td>${absence.return_date}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
