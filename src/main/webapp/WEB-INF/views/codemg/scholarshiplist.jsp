<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
	<table>
		<thead>
			<tr>
				<td>장학수혜코드</td>
				<td>학번</td>
				<td>장학코드</td>
				<td>학기코드</td>
				<td>평점</td>
				<td>지급일</td>
			</tr>
		</thead>
		<c:forEach items="${scholarshipList}" var="ss" varStatus="index">
		<tbody>
			<tr>
				<td>
					<a href="detailScholarship.htm?scholarship_code=${ss.scholarship_code}">
						${ss.scholarship_code }
					</a>
				</td>
				<td>
					${ss.student_code }
				</td>
				<td>
					${ss.sys_code }
				</td>
				<td>
					${ss.semester_code  }
				</td>
				<td>
					${ss.scholarship_rcordavg }
				</td>
				<td>
					${ss.scholarship_payday }
				</td>
		</tbody>
		</c:forEach>
	</table>
	<a href="code.htm">되돌아가기</a>