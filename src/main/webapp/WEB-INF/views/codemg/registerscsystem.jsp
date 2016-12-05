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
	<div id="scSystemRegister">
		<hr>
		SC_SYSTEM
		<form action="insertScSystem.htm" method="post">
			<table>
				<tr>
					<td>장학명</td>
					<td>	
						<input type="text" name="scholaship_name" id="scholaship_name">
					</td>
				</tr>
				<tr>
					<td>선발기준</td>
					<td>
					<input type="text" name="scholaship_standard" id="scholaship_standard">
					</td>
				</tr>
				<tr>
					<td>수혜인원</td>
					<td>
					<input type="text" name="scholaship_member" id="scholaship_member">
					</td>
				</tr>
				<tr>
					<td>장학금액</td>
					<td>
					<input type="text" name="scholaship_amount" id="scholaship_amount">
					</td>
				</tr>
				<tr>
					<td>비고</td>
					<td>
						<textarea name="scholaship_note" id="scholaship_note">-</textarea>
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="등록"></td>
				</tr>
			</table>
		</form>
		<a href="scSystemList.htm">리스트</a>
		<hr>
		<form id="scsexcelForm" action="scsExcelUpload.htm">
			파일 일괄등록: <input id="scsexcel" name="scsexcel" type="file"
				placeholder="Text input">
			<button type="button" id="scsexcelUp">등록하기</button>
		</form>

		<table>
			<tr>
				<td><a href="scsexcel.htm"> 양식다운로드 </a></td>
			</tr>
		</table>
	</div>