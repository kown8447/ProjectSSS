<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
	<table>
		<thead>
			<tr>
				<td>장학수혜코드</td>
				<td>학번</td>
				<td>이름</td>
				<td>장학명</td>
				<td>학기</td>
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
					${ss.code_name }
				</td>
				<td>
					${ss.scholaship_name }
				</td>
				<td>
					${ss.semester_name  }
				</td>
				<td>
					${ss.scholarship_payday }
				</td>
		</tbody>
		</c:forEach>
	</table>
	<a href="registerscholarship.htm">되돌아가기</a>