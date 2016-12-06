<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<table>
		<thead>
			<tr>
				<td>학과코드</td>
				<td>단대코드</td>
				<td>교수코드</td>
				<td>사무실코드</td>
				<td>이름</td>
				<td>설명</td>
				<td>정원</td>
				<td>졸업학점(전공,교양)</td>
				<td>복수전공 가능여부</td>
				<td>학과 존재여부</td>
			</tr>
		</thead>
		<c:forEach items="${department}" var="dp" varStatus="index">
			<tbody>
				<tr>
					<td>${dp.department_code}</td>
					<td>${dp.college_code}</td>
					<td>${dp.professor_code}</td>
					<td>${dp.office_code}</td>
					<td>
						<a href="departmentDetail.htm?department_code=${dp.department_code}">
							${dp.department_name}
						</a>
					</td>
					<td>${dp.department_description}</td>
					<td>${dp.department_seat}</td>
					<td>${dp.graduation_credit}</td>
					<td>${dp.double_possible}</td>
					<td>${dp.department_exist}</td>
				</tr>
			</tbody>
		</c:forEach>
	</table>
	<a href="registerdepartment.htm">되돌아가기</a>