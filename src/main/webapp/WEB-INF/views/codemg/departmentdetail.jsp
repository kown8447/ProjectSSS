<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${department}" var="dp"></c:set>
<form action="updateDepartment.htm">
	학과코드<input type="text" id="department_code" name="department_code" value="${dp.department_code}" readonly="readonly"><br>
	단대코드<input type="text" id="college_code" name="college_code" value="${dp.college_code}"><br>
	교수코드<input type="text" id="professor_code" name="professor_code" value="${dp.professor_code}"><br>
	사무실코드<input type="text" id="office_code" name="office_code" value="${dp.office_code}"><br>
	이름<input type="text" id="department_name" name="department_name" value="${dp.department_name}"><br>
	설명<input type="text" id="department_description" name="department_description" value="${dp.department_description}"><br>
	정원<input type="text" id="department_seat" name="department_seat" value="${dp.department_seat}"><br>
	졸업학점(전공,교양)<input type="text" id="graduation_credit" name="graduation_credit" value="${dp.graduation_credit}"><br>
	복수전공 가능여부<input type="text" id="double_possible" name="double_possible" value="${dp.double_possible}"><br>
	학과 존재여부<input type="text" id="department_exist" name="department_exist" value="${dp.department_exist}"><br>
	<input type="submit" value="수정하기">
</form>