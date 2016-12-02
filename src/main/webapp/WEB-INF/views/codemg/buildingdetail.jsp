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
	<form action="updateBuilbilding.htm">
		빌딩코드<input type="text" id="building_code" name="building_code" readonly="readonly" value="${building.building_code }"><br>
		건물명<input type="text" id="building_name" name="building_name" value="${building.building_name }"><br>
		주소<input type="text" id="building_addr" name="building_addr" value="${building.building_addr }">
		<button type="submit">수정하기</button>
	</form>
</body>
</html>