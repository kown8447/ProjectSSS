<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<form action="lectureRegister.htm" method="post" enctype="multipart/form-data">
	구분 : <select id="subject_type" name="subject_type">
			<option value=0>교양</option>
			<option value=1>전공</option>
		 </select> <br>
	과목코드 : <input type="text" id="subject_code" name="subject_code"><br>		 
	선수과목 : <input type="text" id="before_code" name="before_code"><br>		
	과목명 : <input type="text" id="subject_name" name="subject_name"><br>
	정원 : <input type="text" id="subject_seats" name="subject_seats"><br>
	학점 : <input type="text" id="subject_credit" name="subject_credit"><br>
	수강대상 : <input type="text" id="grade_limit" name="grade_limit"><br>
	강의계획서 : <input type="file" id="subject_filesrc" name="subject_filesrc">
 
    <input type="submit" value="등록하기">
	</form>
	
</body>
</html>