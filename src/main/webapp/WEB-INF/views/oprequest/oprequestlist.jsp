<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<div class="container" style="width:75%">
<h4>▶&nbsp;개설과목 신청 현황 리스트 </h4>
<br><br>
	<div class="container" style="width:95%">
	<table class="table" style="text-align: center">
		<thead>
			<tr>
				<td>구분</td>
				<td>학년</td>
				<td>과목</td>
				<td>교수</td>
				<td>상태</td>
			</tr>
		</thead>
		<c:forEach items="${oprequest}" var="oplist">
		<tbody>
			<tr>
				<td>${oplist.required_choice}</td>
				<td>${oplist.grade_limit}</td>
				<td><a href="requestDetail.htm?code=${oplist.subject_code}">${oplist.subject_name}</a></td>
				<td>${oplist.code_name}</td>
				<td>
				<c:choose>
					<c:when test="${oplist.success_check == 0}">대기</c:when>
					<c:when test="${oplist.success_check == 1}">승인</c:when>
					<c:when test="${oplist.success_check == 2}">거절</c:when>
					<c:otherwise>대기</c:otherwise>
				</c:choose>	
				</td>
			</tr>
		</tbody>
		</c:forEach>
	</table>
	</div>
</div>	
