<%--
@Project : InitSpring
@File name : subjectupdate.jsp
@Author : 조장현
@Data : 2016.11.23
@Desc : 과목 수정 
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/js/lecture/subjectupdate.js"></script>
<title>Insert title here</title>
</head>
<body>
	<h4>▶&nbsp;과목수정</h4><br><br>
	
	<form action="lectureEditOk.htm">
		<div class="row" style="width: 65%; margin: auto;">
			  <table class="table table-bordered" style="text-align: center;">
				<tr>
					<th style="text-align: center;background-color: #F8F4EC;">구분</th>
					<td><c:choose>
							<c:when test="${list.subject_type == '0'}">전공</c:when>
							<c:when test="${list.subject_type == '1'}">교양</c:when>
						</c:choose> 
						<input type="hidden" name="subject_type" value=${list.subject_type }> 
						<c:choose>
							<c:when test="${list.required_choice == '0'}">필수</c:when>
							<c:when test="${list.required_choice == '1'}">선택</c:when>
						</c:choose></td>
					<th style="text-align: center;background-color: #F8F4EC;">강의대상</th>
					<td>${list.grade_limit}</td>
				</tr>
				<input type="hidden" name="grade_limit" value="${list.grade_limit}">
				<tr>
					<input type="hidden" value="${list.before_name }">
					<th style="text-align: center;background-color: #F8F4EC;">선수과목</th>
					<td>
						<select id="before_code" name="before_name" class="form-control">
							<option value="0">없음</option>
							<c:forEach items="${before}" var="i">
								<c:choose>
									<c:when test="${i == list.before_name}">
										<option value="${i}" selected>${i}</option>
									</c:when>
									<c:otherwise>
										<option value="${i}">${i}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
					<th style="text-align: center;background-color: #F8F4EC;">과목명</th>
					<td>
						<input type="text" name="subject_name" value="${list.subject_name}" class="form-control">
					</td>
				</tr>
				<tr>
					<th style="text-align: center;background-color: #F8F4EC;">학점</th>
					<td>
						<input type="number" id="subject_credit" name="subject_credit" value="${list.subject_credit}" class="form-control"></td>
					<th style="text-align: center;background-color: #F8F4EC;">정원</th>
					<td>
						<input type="number" name="subject_seats" value="${list.subject_seats}" class="form-control"></td>
				</tr>
					<input type="hidden" name="subject_code" value="${list.subject_code }">
				<tr>
			</table>
		</div>
	
	<br>
	<div align="center">
		<button type="submit" id="submit" class="btn btn-success">수정하기</button>
		<button id="back" class="btn btn-danger">돌아가기</button>
	</div>
	<div style="height: 15%;"></div>
</form>
	
	

</body>
</html>