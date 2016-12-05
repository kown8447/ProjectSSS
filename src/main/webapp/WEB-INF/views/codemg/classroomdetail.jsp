<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="updateClassroom.htm">
		강의실코드<input type="text" value="${classroom.classroom_code }" readonly="readonly" id="classroom_code" name="classroom_code"><br>
		건물코드<select id="building_code" name="building_code">
			<c:forEach items="${building}" var="bd">
				<option value="${bd.building_code}">${bd.building_name}</option>
			</c:forEach>
		</select><br>
		강의실 이름<input type="text" value="${classroom.classroom_name}" id="classroom_name" name="classroom_name"><br>
		수용인원<input type="text" value="${classroom.seat}" id="seat" name="seat"><br>
		<%-- 강의실 타입<input type="text" value="${classroom.classroom_type}" id="classroom_type" name="classroom_type"><br> --%>
		일반강의실<input type="radio" value="0" id="classroom_type" name="classroom_type">
		실습실<input type="radio" value="1" id="classroom_type" name="classroom_type"><br>
		<button type="submit">수정하기</button>
	</form>
</body>
</html>