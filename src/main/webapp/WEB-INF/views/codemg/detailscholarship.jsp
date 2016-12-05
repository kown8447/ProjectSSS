<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="updateScholarship.htm">
<c:set value="${scholarship}" var="sl"/>
<c:set value="${scsystem}" var="sc"/>
<c:set value="${semester}" var="sm"/>    
	장학수혜코드<input type="text" id="scholarship_code" name="scholarship_code" value="${sl.scholarship_code }" readonly="readonly"><br>
	학번<input type="text" id="student_code" name="student_code" value="${sl.student_code }"><br>
	장학코드<%-- <input type="text" id="sys_code" name="sys_code" value="${sl.sys_code }"><br> --%>
	<select id="sys_code" name="sys_code">
		<c:forEach items="${scsystem}" var="sclsit">
		<option value="${sclsit.sys_code }">${sclsit.scholaship_name }</option>
		</c:forEach>
	</select><br>
	학기코드<%-- <input type="text" id="semester_code" name="semester_code" value="${sl.semester_code }"><br> --%>
	<select id="semester_code" name="semester_code">
		<c:forEach items="${semester}" var="smlsit">
		<option value="${smlsit.semester_code }">${smlsit.semester_name }</option>
		</c:forEach>
	</select><br>
	평점<input type="text" id="scholarship_rcordavg" name="scholarship_rcordavg" value="${sl.scholarship_rcordavg }"><br>
	지급일<input type="text" id="scholarship_payday" name="scholarship_payday" value="${sl.scholarship_payday }"><br>
	<input type="submit" value="수정하기">
</form>
<a href="code.htm">되돌아가기</a>