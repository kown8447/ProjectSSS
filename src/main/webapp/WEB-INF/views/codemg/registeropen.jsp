<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<div class="container" style="width:75%">
<h4>▶&nbsp;개설신청 현황 </h4>
<br><br>
 <div class="container" style="width:95%">
		<table class="table" style="text-align: center">
		<thead>
			<tr>
				<td>과목코드</td>
				<td>학기코드</td>
				<td>수강인원</td>
			</tr>
		</thead>
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