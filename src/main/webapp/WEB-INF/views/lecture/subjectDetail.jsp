<%--
@Project : InitSpring
@File name : subjectDetail.jsp
@Author : 조장현
@Data : 2016.11.23
@Desc : 과목 상세보기 
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="${pageContext.request.contextPath}/js/lecture/subjectdetail.js"></script>
<h4>▶&nbsp;과목 상세보기</h4><br><br>

<div class="container" style="width: 65%">
	<table class="table table-bordered" style="text-align: center">
		<tr>
			<th style="text-align: center;background-color: #F8F4EC;" >구분</th>
			<td name="subject_type"><c:choose>
					<c:when test="${list.subject_type == '0'}"> 전공 </c:when>
					<c:when test="${list.subject_type == '1'}"> 교양 </c:when>
				</c:choose> 
				<input type="hidden" name="subject_type" value="${list.subject_type }"> <c:choose>
					<c:when test="${list.required_choice == '0'}">필수</c:when>
					<c:when test="${list.required_choice == '1'}">선택</c:when>
				</c:choose></td>
			<th style="text-align: center;background-color: #F8F4EC;" >강의대상</th>
			<td>${list.grade_limit}</td>
		</tr>
		<tr>
			<th style="text-align: center;background-color: #F8F4EC;" >선수과목</th>
			<td>${list.before_name}</td>
			<th style="text-align: center;background-color: #F8F4EC;" >과목명</th>
			<td>${list.subject_name}</td>
		</tr>
		<tr>
			<th style="text-align: center;background-color: #F8F4EC;" >학점</th>
			<td>${list.subject_credit}</td>
			<th style="text-align: center;background-color: #F8F4EC;" >정원</th>
			<td>${list.subject_seats}</td>
		</tr>
		<tr>
			<th style="text-align: center;background-color: #F8F4EC;" >거절사유</th>
			<td colspan="3">${list.reject_reason }</td>
		</tr>

		<input type="hidden" id="subject_code" value="${list.subject_code }">
		<input type="hidden" id="sc" value=<%=request.getParameter("success_check") %>>

	</table>

	<br>
	<div align="center">
		<button id="back" class="btn btn-default">돌아가기</button>
		<button id="update" class="btn btn-primary">수정</button>
		<button id="delete" class="btn btn-danger">삭제</button>
		<button id="request" class="btn btn-success">신청</button>
		<div style="height: 150px;"></div>
	</div>
	
</div>
