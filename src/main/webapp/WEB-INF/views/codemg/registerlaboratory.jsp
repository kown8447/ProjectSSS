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
	<div id="laboratoryRegister">
		<hr>
		LABORATORY
		<form action="insertLab.htm" method="post">
			<table>
				<tr>
					<td>건물코드</td>
					<td>	
						<select id="building_code" name="building_code">
							<c:forEach items="${building}" var="bd">
							<option value="${bd.building_code}">${bd.building_name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>연구실 이름</td>
					<td>
					<input type="text" name="lab_name" id="lab_name">
					</td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td>
					<input type="text" name="lab_phone" id="lab_phone">
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="등록"></td>
				</tr>
			</table>
		</form>
		<!-- <a href="labList.htm">리스트</a> -->
		<a href="showlablist.htm">리스트</a>
		<hr>
		<form id="lbexcelForm" action="lbExcelUpload.htm">
			파일 일괄등록: <input id="lbexcel" name="lbexcel" type="file"
				placeholder="Text input">
			<button type="button" id="lbexcelUp">등록하기</button>
		</form>

		<table>
			<tr>
				<td><a href="lbexcel.htm"> 양식다운로드 </a></td>
			</tr>
		</table>
	</div>	