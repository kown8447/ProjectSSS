<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h5>학생 개인정보</h5>
<div>
	<img
		src="${pageContext.request.contextPath}/profiles/${student.member_picture}">
</div>
<div>
	<table></table>
	<table>
		<tr>
			<th>학번</th>
			<td>${student.student_code}</td>
			<th>성명</th>
			<td>${student.member_name}</td>
		</tr>
		<tr>
			<th>생년월일</th>
			<td>${student.member_birth}</td>
			<th>주소</th>
			<td>${student.member_addr}</td>
		</tr>
		<tr>
			<th>e-mail</th>
			<td>${student.member_email}</td>
			<th>연락처</th>
			<td>${student.member_phone}</td>
		</tr>
	</table>
	<table></table>
	<table>
		<tr>
			<th>대학</th>
			<td>${mainMajor.college_name}</td>
			<th>입학년도</th>
			<td>${student.enterYear}</td>
		</tr>
		<tr>
			<th>학부/학과</th>
			<td>${mainMajor.department_name}</td>
			<th>학적상태</th>
			<td>${student.code_name}</td>
		</tr>
		<tr>
			<th>휴학학기</th>
			<td>${absenceCount}</td>
			<th>잔여 휴학학기</th>
			<td>${8-absenceCount}</td>
		</tr>
		<tr>
			<th>총 이수학점</th>
			<td>${state.total_credit}</td>
			<th>이수/예정학기</th>
			<td>${semesterCount}/8</td>
		</tr>
		<tr>
			<th>복수전공</th>
			<td colspan="3"><c:if test="${doubleMajor!=null}">${doubleMajor.department_name}</c:if> </td>
		</tr>
	</table>
</div>