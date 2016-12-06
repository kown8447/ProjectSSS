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
	<div id="colleageRegister">
		<hr>
		COLLEGE
		<form action="insertCollege.htm" method="post">
			<table>
				<tr>
					<td>사무실코드</td>
					<td>
						<select id="office_code" name="office_code">
							<c:forEach items="${officelist}" var="of">
							<option value="${of.office_code}">${of.office_name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="college_name" id="college_name"></td>
				</tr>
				<tr>
					<td>설명</td>
					<td>
						<textarea id="college_description" name="college_description"></textarea>
					</td>
				</tr>
			</table>
			<input type="submit" value="등록하기">
		</form>
		<a href="collegeList.htm">리스트</a>
		<hr>
		<form id="colexcelForm" action="colExcelUpload.htm">
			파일 일괄등록: <input id="colexcel" name="colexcel" type="file"
				placeholder="Text input">
			<button type="button" id="colexcelUp">등록하기</button>
		</form>

		<table>
			<tr>
				<td><a href="colexcel.htm"> 양식다운로드 </a></td>
			</tr>
		</table>
	</div>