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
	<div id="buildingRegister">
		<hr>
		BUILDING
		<form action="registerBuilding.htm" method="post">
			<table>
				<tr>
					<td>건물명</td>
					<td><input type="text" name="building_name" id=building_name>
					</td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="building_addr" id="building_addr">
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="등록"></td>
				</tr>
			</table>
		</form>
		<a href="buildingList.htm">리스트</a>
		<form id="bdexcelForm" action="buildingExcelUpload.htm">
			파일 일괄등록: <input id="bdexcel" name="bdexcel" type="file"
				placeholder="Excel input">
			<button type="button" id="bdexcelUp">등록하기</button>
		</form>
		<a href="buildingexcel.htm">파일 양식다운로드</a>
	</div>