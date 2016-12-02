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
	<table>
		<thead>
			<tr>
				<td>사무실코드</td>
				<td>건물코드</td>
				<td>전화번호</td>
				<td>사무실 이름</td>
				<td>사용가능 여부</td>
			</tr>
		</thead>
		<c:forEach items="${officelist}" var="office" varStatus="index">
			<tbody>
				<tr>
					<td>${office.office_code}</td>
					<td>${office.building_name}</td>
					<td>${office.office_phone}</td>
					<td>
						<a href="selectOffice.htm?office_code=${office.office_code}">
							${office.office_name}	
						</a>
					</td>
					<td>
						<c:choose>
							<c:when test="${office.office_possible == 0}">
								사용가능
							</c:when>
							<c:otherwise>
								사용불가능
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</tbody>
		</c:forEach>
	</table>
	<a href="code.htm">되돌아가기</a>
</body>
</html>