<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="http://malsup.github.com/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript">
</script>
<div id="codeRegisterTabs">
	<ul>
		<li><a href="#buildingRegister">건물</a></li>
		<li><a href="#classroomRegister">강의실</a></li>
		<li><a href="#officeRegister">사무실</a></li>
		<li><a href="#laboratoryRegister">연구실</a></li>
	</ul>
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
	<div id="classroomRegister">
		<hr>
		CLASSROOM
		<form action="registerClassroom.htm" method="post">
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
	<div id="officeRegister">
		<hr>
		OFFICE
		<form action="insertOffice.htm" method="post">
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
</div>