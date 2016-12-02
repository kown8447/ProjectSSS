<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${academic}" var="ac"/>

<form action="updateAcademic.htm">
	일정번호<input type="text" id="calendar_code" name="calendar_code" value="${ac.calendar_code }" readonly="readonly"><br>
	제목<input type="text" id="calendar_title" name="calendar_title" value="${ac.calendar_title }"><br>
	내용<textarea id="calendar_content" name="calendar_content">${ac.calendar_content }</textarea><br>
	시작일<input type="text" id="calendar_start" name="calendar_start" value="${ac.calendar_start }"><br>
	종료일<input type="text" id="calendar_end" name="calendar_end" value="${ac.calendar_end }"><br>
	<input type="submit" value="수정하기">	
</form>
<a href="code.htm">되돌아가기</a>