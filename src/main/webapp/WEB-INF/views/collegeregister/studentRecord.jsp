<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 
   @Project : InitSpring
   @File name : studentRecord.jsp
   @Author : 최준호
   @Data : 2016.11.30
   @Desc : 학생성적열람페이지
-->
<script src="${pageContext.request.contextPath}/js/collegeRegister/studentRecord.js"></script>
<h4>▶&nbsp;성적조회</h4><br><br>

<div class="row" style="width: 80%; margin: auto;">
	<div class="form-inline">
		<select class="form-control recordSelect" id="recordGrade">
			<option value="default">학년</option>
			<c:forEach begin="1" end="${state.grade}" varStatus="status">
				<option value="${status.count}">${status.count}</option>
			</c:forEach>
		</select> <select class="form-control recordSelect" id="recordSemester">
			<option value="default">학기</option>
		</select> 
		
		<input type="hidden" id="maxGrade" value="${state.grade}"> 
		<input type="hidden" id="maxSemester" value="${state.personal_semester}">

		<a id="pdfDownLoader" href="recordPdfRequest.htm">
			<button class="btn btn-danger" style="float: right;" id="pdfDownLoader">PDF 파일 다운로드</button>
		</a>
	</div>
	<br>

	<table id="recordView" class="table table-bordered" style="text-align: center;">
		<thead>
			<tr>
				<th style="text-align: center; width: 15%;">구분</th>
				<th style="text-align: center; width: 15%;">과목코드</th>
				<th style="text-align: center; width: 20%;">과목명</th>
				<th style="text-align: center; width: 10%;">학점</th>
				<th style="text-align: center; width: 20%;">성적</th>
				<th style="text-align: center">재수강</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty recordList}">
				<tr>
					<td colspan="6" style="text-align: center">등록된 정보가 없습니다.</td>
				</tr>
			</c:if>
			<c:forEach var="record" items="${recordList}">
				<tr>
					<td>${record.stringtype}</td>
					<td>${record.subject_code}</td>
					<td>${record.subject_name}</td>
					<td>${record.subject_credit}</td>
					<td>${record.record_level}</td>
					<td><c:choose>
							<c:when test="${record.retake_check==1}">재수강(학점 무효)</c:when>
							<c:otherwise></c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="6">전공: ${majorCredit}&nbsp;&nbsp;&nbsp; 
								교양: ${liberalCredit}&nbsp;&nbsp;&nbsp; 
								복수전공: ${doubleCredit}&nbsp;&nbsp;&nbsp; 
								총 이수학점:${totalCredit}&nbsp;&nbsp;&nbsp;
								평점(F포함 계산):${inF}&nbsp;&nbsp;&nbsp; 
								평점(F제외):${outF}</td>
			</tr>
		</tbody>
	</table>
</div>