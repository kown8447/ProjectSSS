<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form action="updateOffice.htm">
	사무실코드<input type="text" id="office_code" name="office_code" value="${office.office_code }" readonly="readonly"><br>
	건물코드<%-- <input type="text" id="building_code" name="building_code" value="${office.building_code }"><br> --%>
		<select id="building_code" name="building_code">
			<c:forEach items="${building}" var="bd">
				<option value="${bd.building_code}">${bd.building_name}</option>
			</c:forEach>
		</select><br>
	
	전화번호<input type="text" id="office_phone" name="office_phone" value="${office.office_phone }"><br>
	사무실 이름<input type="text" id="office_name" name="office_name" value="${office.office_name }"><br>
	<input type="submit" value="수정하기">
</form>
