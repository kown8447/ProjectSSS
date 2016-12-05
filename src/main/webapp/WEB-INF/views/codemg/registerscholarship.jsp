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
	<div id="scholarshipRegister">
		<hr>
		SCHOLARSHIP
		<form action="insertScholarship.htm" method="post">
			<table>
				<tr>
					<td>학번</td>
					<td>	
						<input type="text" name="student_code" id="student_code">
					</td>
				</tr>
				<tr>
					<td>장학코드</td>
					<td>
						<select id="sys_code" name="sys_code">
							<c:forEach items="${sc}" var="sc">
							<option value="${sc.sys_code}">${sc.scholaship_name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>학기코드</td>
					<td>
						<select id="semester_code " name="semester_code">
							<c:forEach items="${semester}" var="sm">
								<option value="${sm.semester_code}">${sm.semester_name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>평점</td>
					<td>
					<input type="text" name="scholarship_rcordavg" id="scholarship_rcordavg">
					</td>
				</tr>
				<tr>
					<td>지급일</td>
					<td>
					<input type="text" name="scholarship_payday" id="scholarship_payday">
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="등록"></td>
				</tr>
			</table>
		</form>
		<a href="scholarshipList.htm">리스트</a>
		<hr>
		<form id="sclexcelForm" action="sclExcelUpload.htm">
			파일 일괄등록: <input id="sclexcel" name="sclexcel" type="file"
				placeholder="Text input">
			<button type="button" id="sclexcelUp">등록하기</button>
		</form>

		<table>
			<tr>
				<td><a href="sclexcel.htm"> 양식다운로드 </a></td>
			</tr>
		</table>
	</div>