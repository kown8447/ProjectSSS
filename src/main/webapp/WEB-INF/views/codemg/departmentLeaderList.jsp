<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<div>
<table>
		<thead>
			<tr>
				<td>교수이름</td>
				<td>학과이름</td>
				<td>단과대학</td>
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