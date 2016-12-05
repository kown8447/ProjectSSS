<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="updateLab.htm">
	연구실코드<input type="text" value="${lab.lab_code }" id="lab_code" name="lab_code" readonly="readonly"><br>
	건물코드<%-- <input type="text" value="${lab.building_code }" id="building_code" name="building_code"><br> --%>
		<select id="building_code" name="building_code">
			<c:forEach items="${building}" var="bd">
				<option value="${bd.building_code}">${bd.building_name}</option>
			</c:forEach>
		</select><br>
	연구실 이름<input type="text" value="${lab.lab_name }" id="lab_name" name="lab_name"><br>
	전화번호<input type="text" value="${lab.lab_phone }" id="lab_phone" name="lab_phone"><br>
	사용가능 여부<input type="text" value="${lab.lab_possible }" id="lab_possible" name="lab_possible"><br>

<input type="submit" value="수정하기">
</form>
<a href="code.htm">되돌아가기</a>
