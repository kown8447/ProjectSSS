<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table>
		<thead>
			<tr>
				<td>장학코드</td>
				<td>장학명</td>
				<td>선발기준</td>
				<td>수혜인원</td>
				<td>장학금액</td>
				<td>비고</td>
				<td>시행</td>
			</tr>
		</thead>
		<c:forEach items="${scsystemlist}" var="sc" varStatus="index">
			<tbody>
				<tr>
					<td>${sc.sys_code}</td>
					<td>
						<a href="detailScSystem.htm?sys_code=${sc.sys_code}">
							${sc.scholaship_name}
						</a>
					
					</td>
					<td>${sc.scholaship_standard}</td>
					<td>${sc.scholaship_member}</td>
					<td>${sc.scholaship_amount}</td>
					<td>${sc.scholaship_note}</td>
					<td>
					<c:choose>
						<c:when test="${sc.scholaship_use ==0 }">
						시행중
						</c:when>
						<c:when test="${sc.scholaship_use ==1 }">
						미시행중
						</c:when>
						<c:otherwise>에러</c:otherwise>	
					</c:choose>
					</td>
				</tr>
			</tbody>
		</c:forEach>
	</table>
	<a href="registerscsystem.htm">되돌아가기</a>