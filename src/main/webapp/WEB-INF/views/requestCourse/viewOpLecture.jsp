<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>개설 강의 보기</h2>

<form class="form-inline">
	<select id="collegeList" class="form-control col-3">
		<option value="0">단과대학 선택</option>
		<c:forEach items="${colleges}" var="college">
			<option value="${college.college_code}">${college.college_name}</option>
		</c:forEach>
	</select> 
	<select id="departmentList" class="form-control col-3">
		<option value="1000">학부/학과 선택</option>
	</select> 
	<input type="button" class="btn btn-default" id="searchSubject" value="검색" />
</form>


<br>
<div id="result">
	<table class="table table-bordered">
		<tr>
			<th>구분</th>
			<th>강의 이름</th>
			<th>시간</th>
			<th>지도 교수</th>
			<th>강의 계획서</th>
			<th>모집 인원</th>
		</tr>
		<c:forEach items="${lists}" var="subject">
			<tr>
				<c:choose>
					<c:when test="${subject.subject_type=='0'}">
						<td>전공 <c:choose>
								<c:when test="${subject.required_choice=='0'}"> 필수</c:when>
								<c:otherwise> 선택</c:otherwise>
							</c:choose>
						</td>
					</c:when>
					<c:when test="${subject.subject_type=='1'}">
						<td>교양 <c:choose>
								<c:when test="${subject.required_choice=='0'}"> 필수</c:when>
								<c:otherwise> 선택</c:otherwise>
							</c:choose>
						</td>
					</c:when>
				</c:choose>
				<td>${subject.subject_name}</td>
				<td><c:forEach items="${subject.period}" var="period">
					${period.period_day}
					${period.period_start}
					${period.period_end}<br>
					</c:forEach></td>
				<td>${subject.professor_name}</td>
				<td><a href="download.htm?f=${subject.subject_filesrc}">${subject.subject_filesrc}</a></td>
				<td>${subject.subject_seats}</td>
			</tr>
		</c:forEach>
	</table>
</div>
