<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<table>
		<thead>
			<tr>
				<td>학번</td>
				<td>학기코드</td>
				<td>등록구분</td>
				<td>등록금</td>
				<td>등록여부</td>
			</tr>
		</thead>
		<c:forEach items="${register}" var="reg" varStatus="index">
			<tbody>
				<tr>
					<td>${reg.student_code}</td>
					<td>${reg.semester_code}</td>
					<td>
						<c:choose>
							<c:when test="${reg.register_type==0}">
								일반등록
							</c:when>
							<c:when test="${reg.register_type==1}">
								일반등록
							</c:when>
							<c:otherwise>
								졸업연기 등록
							</c:otherwise>
						</c:choose>
					</td>
					<td>${reg.tuition}</td>
					<td>
						<c:choose>
							<c:when test="${reg.register_state==0}">
								등록안함
							</c:when>
							<c:when test="${reg.register_state==1}">
								등록
							</c:when>
							<c:otherwise>
								에러
							</c:otherwise>
						</c:choose>
							
					</td>
				</tr>
			</tbody>
		</c:forEach>
	</table>
	<a href="code.htm">되돌아가기</a>