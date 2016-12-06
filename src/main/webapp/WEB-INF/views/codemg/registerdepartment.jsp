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
	<div id="departmentRegister">
		<hr>
		DEPARTMENT
		<form action="insertDepartment.htm" method="post">
			<table>
				<tr>
					<td>단대코드</td>
					<td>
						<select name="college_code" id="college_code">
							<c:forEach items="${college}" var="cl">
							<option value="${cl.college_code}">${cl.college_name}/code= ${cl.college_code}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>사무실코드</td>
					<td>
						<select id="office_code" name="office_code">
							<c:forEach items="${officelist}" var="of">
							<option value="${of.office_code}">${of.office_name}/code= ${of.office_code}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="department_name" id="department_name"></td>
				</tr>
				<tr>
					<td>설명</td>
					<td>
						<textarea id="department_description " name="department_description"></textarea>
					</td>
				</tr>
				<tr>
					<td>정원</td>
					<td>
						<input type="text" id="department_seat " name="department_seat">
					</td>
				</tr>
				<tr>
					<td>졸업학점(전공,교양)</td>
					<td>
						<input type="text" id="graduation_credit " name="graduation_credit">
					</td>
				</tr>
				<tr>
					<td>복수전공 가능여부</td>
					<td>
						불가능<input type="radio" id="double_possible " name="double_possible" value="0">
						가능<input type="radio" id="double_possible " name="double_possible" value="1"><br>
					</td>
				</tr>
			</table>
			<input type="submit" value="등록하기">
		</form>
		<a href="departmentlist.htm">리스트</a>
		<hr>
		<form id="depexcelForm" action="depExcelUpload.htm">
			파일 일괄등록: <input id="depexcel" name="depexcel" type="file"
				placeholder="Text input">
			<button type="button" id="depexcelUp">등록하기</button>
		</form>

		<table>
			<tr>
				<td><a href="depexcel.htm"> 양식다운로드 </a></td>
			</tr>
		</table>
	</div>