<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<div class="row  col-sm-offset-2">
<h4>▶&nbsp;개설신청 현황 </h4><br><br>

 <div class="col-sm-10">
		<table class="table table-bordered" style="text-align: center" >
		<thead>
			<tr class="active">
				<th style="text-align: center">과목코드</th>
				<th style="text-align: center">학기코드</th>
				<th style="text-align: center">수강인원</th>
			</tr>
		</thead>
		<c:if test="${empty opened}">
				<tr>
					<td colspan="3" style="text-align: center">등록된 정보가 없습니다.</td>
				</tr>
			</c:if>
		<c:forEach items="${opened}" var="op" varStatus="index">
			<tbody>
				<tr>
					<td>${op.subject_name}</td>
					<td>${op.semester_name }</td>
					<td>${op.registed_seat}</td>
				</tr>
			</tbody>
		</c:forEach>
	</table>
	</div>
</div>