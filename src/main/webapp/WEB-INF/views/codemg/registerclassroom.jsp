<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="http://malsup.github.com/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"/>
<script src="${pageContext.request.contextPath}/js/admin/codemg.js"/>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript">

</script>
	<div id="classroomRegister">
		<hr>
		CLASSROOM
		<form action="registerClassroom.htm" method="post">
			<table>
				<tr>
					<td>건물</td>
					<td>	
						<select id="building_code" name="building_code">
							<c:forEach items="${building}" var="bd">
							<option value="${bd.building_code}">${bd.building_name}/code= ${bd.building_code}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>강의실 이름</td>
					<td><input type="text" name="classroom_name" id="classroom_name">
					</td>
				</tr>
				<tr>
					<td>수용인원</td>
					<td><input type="text" name="seat" id="seat">
					</td>
				</tr>
				<tr>
					<td>강의실 타입</td>
					<td>
						일반강의실<input type="radio" value="0" id="classroom_type" name="classroom_type"><br>
						실습실<input type="radio" value="1" id="classroom_type" name="classroom_type">
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="등록"></td>
				</tr>
			</table>
		</form>
		<!-- <a href="classroomList.htm">리스트</a> -->
		<a href="showclasslist.htm">리스트</a>
		<form id="clexcelForm" action="classroomExcelUpload.htm">
			파일 일괄등록: <input id="clexcel" name="clexcel" type="file"
				placeholder="Excel input">
			<button type="button" id="clexcelUp">등록하기</button>
		</form>
		<a href="classroomexcel.htm">파일 양식다운로드</a><br>
	</div>	