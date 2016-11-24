<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>

<body>
	
	<form action="lectureRegister.htm" method="post">
	구분 : <select id="subject_type" name="subject_type">
			<option value=0>전공</option>
			<option value=1>교양</option>
		 </select> 
        <select id="required_choidce" name="required_choice">
			<option value=0>필수</option>
			<option value=1>선택</option>
	</select>	 
	<br>
	과목코드 : <input type="text" id="subject_code" name="subject_code"><br>		 
	선수과목 : <select id="before_code" name="before_code">
			<option value="0">없음</option>
			<c:forEach items="${before}" var="i">
				<option value="${i}">${i}</option>
			</c:forEach>
		</select>	

		<br>
		
	과목명 : <input type="text" id="subject_name" name="subject_name"><br>
	정원 : <input type="number" id="subject_seats" name="subject_seats"><br>
	학점 : <input type="number" id="subject_credit" name="subject_credit"><br>
	수강대상 : <input type="number" id="grade_limit" name="grade_limit"><br>
	 
  
    <input type="submit" value="등록하기">
	</form>
	
</body>
</html>