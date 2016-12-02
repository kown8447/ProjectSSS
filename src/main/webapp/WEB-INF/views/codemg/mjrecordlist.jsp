<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<table>
		<thead>
			<tr>
				<td>코드</td>
				<td>학과코드</td>
				<td>전공구분</td>
			</tr>
		</thead>
		<c:forEach items="${mjrecord}" var="mj" varStatus="index">
			<tbody>
				<tr>
					<td>${mj.student_code}</td>
					<td>${mj.department_code}</td>
					<td>
					<c:choose>
							<c:when test="${mj.mj_type==0}">
								전공
							</c:when>
							<c:when test="${mj.mj_type==1}">
								부전공
							</c:when>
							<c:otherwise>
								코드 에러
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</tbody>
		</c:forEach>
	</table>
	<a href="code.htm">되돌아가기</a>