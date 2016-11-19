<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
	
		
	})
	

</script>
</head>
<body>
	
	<table style="border:1em">
		<tr>
		<td>구분</td><td>${subject_Type }</td><td>강의대상</td><td>${grade_Limit }</td></tr>
		<tr>
		<td>선수과목</td><td colspan="3">${before_Code }</td></tr>
		<tr>
		<td>과목명</td><td colspan="3">${subject_Name }</td></tr>
		<tr>
		<td>학점</td><td>${subject_Credit }</td><td>정원</td><td>${subject_seats }</td></tr>
		<tr>
		<td>건물명</td><td>${buliding_Name }</td><td>강의실</td><td>${classroom_Name }</td></tr>
	
	</table>
	
	<button id="back">돌아가기</button>  
	<a href="lectureRequest.htm">개설신청</a>
</body>
</html>