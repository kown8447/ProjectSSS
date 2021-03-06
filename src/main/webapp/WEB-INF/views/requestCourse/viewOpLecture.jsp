<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 
   @Project : InitSpring
   @File name : viewOtherTimetable.jsp
   @Author : 권기엽, 송아름
   @Data : 2016.12.09
   @Desc : 개설강의 정보 확인 view page.
-->
<script src="${pageContext.request.contextPath}/js/requestCourse/viewOpLecture.js"></script>
<h4>▶&nbsp;개설강의</h4><br><br>

<div class="row" style="width: 80%; margin: auto;">
	<form class="form-inline">
		<select id="collegeList" class="form-control col-3">
			<option value="0">단과대학 선택</option>
			<c:forEach items="${colleges}" var="college">
				<option value="${college.college_code}">${college.college_name}</option>
			</c:forEach>
		</select> <select id="departmentList" class="form-control col-3">
			<option value="1000">학부/학과 선택</option>
		</select> 
		<input type="button" class="btn btn-success" id="searchSubject" value="검색" />
	</form>
</div>
<br>
<div class="row" id="result" style="width: 80%;margin: auto;">
	<table class="table table-hover" style="text-align: center;">
		<tr style="text-align: center;" class="active">
			<th style="text-align: center">구분</th>
			<th style="text-align: center" id="order_subject_name" class="asc">
				강의이름<img src="${pageContext.request.contextPath}/images/mouse.png"></th>
			<th style="text-align: center">시간</th>
			<th style="text-align: center" id="order_professor_name" class="asc">
				지도 교수<img src="${pageContext.request.contextPath}/images/mouse.png"></th>
			<th style="text-align: center">강의 계획서</th>
			<th style="text-align: center">모집 인원</th>
		</tr>
			<c:if test="${empty lists}">
				<tr>
					<td colspan="6" style="text-align: center">등록된 강의가 없습니다.</td>
				</tr>
			</c:if>
		
		<c:forEach items="${lists}" var="subject">
			<tr>
				<c:choose>
					<c:when test="${subject.subject_type=='0'}">
						<td style="text-align: center">전공 <c:choose>
								<c:when test="${subject.required_choice=='0'}"> 필수</c:when>
								<c:otherwise> 선택</c:otherwise>
							</c:choose>
						</td>
					</c:when>
					<c:when test="${subject.subject_type=='1'}">
						<td style="text-align: center">교양 <c:choose>
								<c:when test="${subject.required_choice=='0'}"> 필수</c:when>
								<c:otherwise> 선택</c:otherwise>
							</c:choose>
						</td>
					</c:when>
				</c:choose>
				<td style="text-align: center">${subject.subject_name}</td>
				<td style="text-align: center"><c:forEach
						items="${subject.period}" var="period">
					${period.period_day}
					${period.period_start}
					${period.period_end}<br>
					</c:forEach></td>
				<td style="text-align: center">${subject.professor_name}</td>
				<td style="text-align: center">
					<a href="download.htm?f=${subject.subject_filesrc}">${subject.subject_filesrc}</a></td>
				<td style="text-align: center">${subject.subject_seats}</td>
			</tr>
		</c:forEach>
	</table>
</div>
