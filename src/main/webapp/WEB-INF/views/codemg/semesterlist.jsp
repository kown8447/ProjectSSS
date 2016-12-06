<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<table>
		<thead>
			<tr>
				<td>학기코드</td>
				<td>학기 이름</td>
				<td>학기 시작일</td>
				<td>학기 종료일</td>
			</tr>
		</thead>
		<c:forEach items="${semesterlist}" var="sm" varStatus="index">
		<tbody>
			<tr>
				<td>
				${sm.semester_code }
				</td>
				<td>
					<a href="detailSemester.htm?semester_code=${sm.semester_code}">
						${sm.semester_name }
					</a>
				</td>
				<td>
					${sm.semester_start }
				</td>
				<td>
					${sm.semester_end  }
				</td>
		</tbody>
		</c:forEach>
	</table>
<a href="registersemester.htm">되돌아가기</a>	