<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	구분 : <select name="value">
			<option value=0>교양</option>
			<option value=1>전공</option>
		</select> <br>
      선수과목 : <input type="text" id="before_Code" name="before_Code"><br>		
	과목명 : <input type="text" id="subject_Name" name="subject_Name"><br>
	정원 : <input type="text" id="subject_seats" name="subject_seats"><br>
	학점 : <input type="text" id="subject_Credit" name="subject_Credit"><br>
	수강대상 : <input type="text" id="grade_Limit" name="grade_Limit"><br>
	
	<a href="lectureRegister.htm">등록하기</a>
    	
</body>
</html>