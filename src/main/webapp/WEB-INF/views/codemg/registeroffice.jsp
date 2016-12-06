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
	<div id="officeRegister">
		<hr>
		OFFICE
		<form action="insertOffice.htm" method="post">
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
					<td>전화번호</td>
					<td>
					<input type="text" name="office_phone" id="office_phone">
					</td>
				</tr>
				<tr>
					<td>사무실 이름</td>
					<td>
					<input type="text" name="office_name" id="office_name">
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="등록"></td>
				</tr>
			</table>
		</form>
		<!-- <a href="officeList.htm">리스트</a> -->
		<a href="showofficelist.htm">리스트</a>
		<hr>
		<form id="ofexcelForm" action="ofExcelUpload.htm">
			파일 일괄등록: <input id="ofexcel" name="ofexcel" type="file"
				placeholder="Text input">
			<button type="button" id="ofexcelUp" name="ofexcelUp">등록하기</button>
		</form>

		<table>
			<tr>
				<td><a href="ofexcel.htm"> 양식다운로드 </a></td>
			</tr>
		</table>
	</div>