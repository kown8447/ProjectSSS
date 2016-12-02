<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set value="${semester}" var="sm"/>
<form action="updateSemester.htm">
	학기코드<input type="text" id="semester_code" name="semester_code" value="${sm.semester_code }" readonly="readonly"><br>
	학기 이름<input type="text" id="semester_name" name="semester_name" value="${sm.semester_name }"><br>
	학기 시작일<input type="text" id="semester_start" name="semester_start" value="${sm.semester_start }"><br>
	학기 종료일<input type="text" id="semester_end" name="semester_end" value="${sm.semester_end }"><br>
	<input type="submit" value="수정하기">
</form>
<a href="code.htm">되돌아가기</a>