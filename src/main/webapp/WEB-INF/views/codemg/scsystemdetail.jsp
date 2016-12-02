<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${scsystem}" var="sc"/>
<form action="scsytemUpdate.htm">
	장학코드<input type="text" value="${sc.sys_code }" id="sys_code" name="sys_code" readonly="readonly"><br>
	장학명<input type="text" value="${sc.scholaship_name }" id="scholaship_name" name="scholaship_name"><br>
	선발기준<input type="text" value="${sc.scholaship_standard }" id="scholaship_standard" name="scholaship_standard"><br>
	수혜인원<input type="text" value="${sc.scholaship_member }" id="scholaship_member" name="scholaship_member"><br>
	장학금액<input type="text" value="${sc.scholaship_amount }" id="scholaship_amount" name="scholaship_amount"><br>
	비고<input type="text" value="${sc.scholaship_note }" id="scholaship_note" name="scholaship_note"><br>
	시행<input type="text" value="${sc.scholaship_use }" id="scholaship_use" name="scholaship_use"><br>
	<input type="submit" value="수정하기">
</form>

