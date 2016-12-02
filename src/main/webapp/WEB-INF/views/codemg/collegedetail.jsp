<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<form action="updateColleage.htm">    
	단대코드<input type="text" id="college_code" name="college_code" value="${college.college_code}" readonly="readonly"><br>
	교수코드<input type="text" id="professor_code" name="professor_code" value="${college.professor_code}"><br>
	사무실코드<input type="text" id="office_code" name="office_code" value="${college.office_code}"><br>
	이름<input type="text" id="college_name" name="college_name" value="${college.college_name}"><br>
	설명<input type="text" id="college_description " name="college_description" value="${college.college_description}"><br>
	<input type="submit" value="수정하기"><br>
</form>