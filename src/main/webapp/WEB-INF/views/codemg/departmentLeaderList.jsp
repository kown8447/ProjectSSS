<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<div>
<table class="table table-bordered" style="text-align: center" >
		<thead>
			<tr class="active">
				<th style="text-align: center">교수이름</th>
				<th style="text-align: center">학과이름</th>
				<th style="text-align: center">단과대학</th>
			</tr>
		</thead>
		<c:forEach items="${departmentLeaderList}" var="dp" varStatus="index">
			<tbody>
				<tr>
					<td>${dp.member_name}</td>
					<td>${dp.department_name}</td>
					<td>${dp.college_name}</td>
				</tr>
			</tbody>
		</c:forEach>
	</table>
	<a href="code.htm">되돌아가기</a>

</div>