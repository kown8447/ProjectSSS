<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<table>
		<thead>
			<tr>
				<td>일정번호</td>
				<td>제목</td>
				<td>내용</td>
				<td>시작일</td>
				<td>종료일</td>
			</tr>
		</thead>
		<c:forEach items="${academic}" var="ac" varStatus="index">
			<tbody>
				<tr>
					<td>${ac.calendar_code}</td>
					<td>
						<a href="academicCalendarDetail.htm?calendar_code=${ac.calendar_code}">${ac.calendar_title}</a>
					</td>
					<td>${ac.calendar_content}</td>
					<td>${ac.calendar_start}</td>
					<td>${ac.calendar_end}</td>
				</tr>
			</tbody>
		</c:forEach>
	</table>
	<a href="code.htm">되돌아가기</a>