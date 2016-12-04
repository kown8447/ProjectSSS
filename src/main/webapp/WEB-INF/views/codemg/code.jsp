<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript">
	$(function() {
		$('#codeRegisterTabs').tabs();
		
		//빌딩
		$('#bdexcelUp').click(function() {
			var file = $("#bdexcel").val();
			if (file == "" || file == null) {
				alert("파일을 선택해 주세요");
				return false;
			} else if (!checkFileType(file)) {
				alert("엑셀 파일만 업로드 해주세요");
				return false
			}
			if (confirm("업로드 하시겠습니까?")) {
				var options = {
					success : function(data) {

						if (data.result) {
							console.log("성공");
							alert("등록에 성공 하였습니다.");
							location.href = "buildingList.htm";
						} else {
							console.log("실패");
							alert("등록에 실패 하였습니다.");
						}
					},
					statusCode : {
						404 : function() {
							alert("파일 내용이 잘못되었습니다.");
							location.href = "buildingList.htm";
						}
					},

					type : "POST"
				};
				$('#bdexcelForm').ajaxSubmit(options);
			}

		});
		
		//강의실
		$('#clexcelUp').click(function() {
			var file = $("#clexcel").val();
			if (file == "" || file == null) {
				alert("파일을 선택해 주세요");
				return false;
			} else if (!checkFileType(file)) {
				alert("엑셀 파일만 업로드 해주세요");
				return false
			}
			if (confirm("업로드 하시겠습니까?")) {
				var options = {
					success : function(data) {

						if (data.result) {
							console.log("성공");
							alert("성공");
							location.href = "classroomList.htm";
						}else if(data.result==false){
							console.log("실패");
							alert("실패");
						}else{
							alert("error");
						}
					},
					statusCode : {
						404 : function() {
							alert("파일 내용이 잘못되었습니다.");
							location.href = "classroomList.htm";
						}
					},

					type : "POST"
				};
				$('#clexcelForm').ajaxSubmit(options);
			}

		});
		
		//사무실
		$('#ofexcelUp').click(function() {
			var file = $("#ofexcel").val();
			if (file == "" || file == null) {
				alert("파일을 선택해 주세요");
				return false;
			} else if (!checkFileType(file)) {
				alert("엑셀 파일만 업로드 해주세요");
				return false
			}
			if (confirm("업로드 하시겠습니까?")) {
				var options = {
					success : function(data) {

						if (data.result) {
							console.log("성공");
							alert("성공");
							location.href = "officeList.htm";
						} else {
							console.log("실패");
							alert("실패");
						}
					},
					statusCode : {
						404 : function() {
							alert("파일 내용이 잘못되었습니다.");
							location.href = "officeList.htm";
						}
					},

					type : "POST"
				};
				$('#ofexcelForm').ajaxSubmit(options);
			}

		});
		//연구실
		$('#lbexcelUp').click(function() {
			var file = $("#lbexcel").val();
			if (file == "" || file == null) {
				alert("파일을 선택해 주세요");
				return false;
			} else if (!checkFileType(file)) {
				alert("엑셀 파일만 업로드 해주세요");
				return false
			}
			if (confirm("업로드 하시겠습니까?")) {
				var options = {
					success : function(data) {

						if (data.result) {
							console.log("성공");
							alert("성공");
							location.href = "labList.htm";
						} else {
							console.log("실패");
							alert("실패");
						}
					},
					statusCode : {
						404 : function() {
							alert("파일 내용이 잘못되었습니다.");
							location.href = "labList.htm";
						}
					},

					type : "POST"
				};
				$('#lbexcelForm').ajaxSubmit(options);
			}

		});
		//장학제도
		$('#scsexcelUp').click(function() {
			var file = $("#scsexcel").val();
			if (file == "" || file == null) {
				alert("파일을 선택해 주세요");
				return false;
			} else if (!checkFileType(file)) {
				alert("엑셀 파일만 업로드 해주세요");
				return false
			}
			if (confirm("업로드 하시겠습니까?")) {
				var options = {
					success : function(data) {

						if (data.result) {
							console.log("성공");
							alert("성공");
							location.href = "scSystemList.htm";
						} else {
							console.log("실패");
							alert("실패");
						}
					},
					statusCode : {
						404 : function() {
							alert("파일 내용이 잘못되었습니다.");
							location.href = "scSystemList.htm";
						}
					},

					type : "POST"
				};
				$('#scsexcelForm').ajaxSubmit(options);
			}

		});		
		
		//장학금
		$('#sclexcelUp').click(function() {
			var file = $("#sclexcel").val();
			if (file == "" || file == null) {
				alert("파일을 선택해 주세요");
				return false;
			} else if (!checkFileType(file)) {
				alert("엑셀 파일만 업로드 해주세요");
				return false
			}
			if (confirm("업로드 하시겠습니까?")) {
				var options = {
					success : function(data) {

						if (data.result) {
							console.log("성공");
							alert("성공");
							location.href = "scholarshipList.htm";
						} else {
							console.log("실패");
							alert("실패");
						}
					},
					statusCode : {
						404 : function() {
							alert("파일 내용이 잘못되었습니다.");
							location.href = "scholarshipList.htm";
						}
					},

					type : "POST"
				};
				$('#sclexcelForm').ajaxSubmit(options);
			}

		});		
		
		//단과대학
		$('#colexcelUp').click(function() {
			var file = $("#colexcel").val();
			if (file == "" || file == null) {
				alert("파일을 선택해 주세요");
				return false;
			} else if (!checkFileType(file)) {
				alert("엑셀 파일만 업로드 해주세요");
				return false
			}
			if (confirm("업로드 하시겠습니까?")) {
				var options = {
					success : function(data) {

						if (data.result) {
							console.log("성공");
							alert("성공");
							location.href = "collegeList.htm";
						} else {
							console.log("실패");
							alert("실패");
						}
					},
					statusCode : {
						404 : function() {
							alert("파일 내용이 잘못되었습니다.");
							location.href = "collegeList.htm";
						}
					},

					type : "POST"
				};
				$('#colexcelForm').ajaxSubmit(options);
			}

		});	
		
		//학부
		$('#depexcelUp').click(function() {
			var file = $("#depexcel").val();
			if (file == "" || file == null) {
				alert("파일을 선택해 주세요");
				return false;
			} else if (!checkFileType(file)) {
				alert("엑셀 파일만 업로드 해주세요");
				return false
			}
			if (confirm("업로드 하시겠습니까?")) {
				var options = {
					success : function(data) {

						if (data.result) {
							console.log("성공");
							alert("성공");
							location.href = "departmentlist.htm";
						} else {
							console.log("실패");
							alert("실패");
						}
					},
					statusCode : {
						404 : function() {
							alert("파일 내용이 잘못되었습니다.");
							location.href = "departmentlist.htm";
						}
					},

					type : "POST"
				};
				$('#depexcelForm').ajaxSubmit(options);
			}

		});	
		
		//전공
		$('#mjexcelUp').click(function() {
			var file = $("#mjexcel").val();
			if (file == "" || file == null) {
				alert("파일을 선택해 주세요");
				return false;
			} else if (!checkFileType(file)) {
				alert("엑셀 파일만 업로드 해주세요");
				return false
			}
			if (confirm("업로드 하시겠습니까?")) {
				var options = {
					success : function(data) {

						if (data.result) {
							console.log("성공");
							alert("성공");
							location.href = "mjRecordList.htm";
						} else {
							console.log("실패");
							alert("실패");
						}
					},
					statusCode : {
						404 : function() {
							alert("파일 내용이 잘못되었습니다.");
							location.href = "mjRecordList.htm";
						}
					},

					type : "POST"
				};
				$('#mjexcelForm').ajaxSubmit(options);
			}

		});

		//등록
		$('#regexcelUp').click(function() {
			var file = $("#regexcel").val();
			if (file == "" || file == null) {
				alert("파일을 선택해 주세요");
				return false;
			} else if (!checkFileType(file)) {
				alert("엑셀 파일만 업로드 해주세요");
				return false
			}
			if (confirm("업로드 하시겠습니까?")) {
				var options = {
					success : function(data) {

						if (data.result) {
							console.log("성공");
							alert("성공");
							location.href = "mjRecordList.htm";
						} else {
							console.log("실패");
							alert("실패");
						}
					},
					statusCode : {
						404 : function() {
							alert("파일 내용이 잘못되었습니다.");
							location.href = "mjRecordList.htm";
						}
					},

					type : "POST"
				};
				$('#regexcelForm').ajaxSubmit(options);
			}

		});		
		
		$('#initBtn').click(function(){
			
			var r = confirm('학기를 초기화 하시겠습니까? 초기화 하게 되면 해당 학기 관련 수업/시간표 정보가 삭제 됩니다.')
			
			if(r==true){
				$.ajax(
						{
							url:"initSemester.htm",
							dataType:"json",
							success:function(data){
								if(data.result=='success'){
									alert('학기 초기화 성공!');
								}else{
									alert('학기 초기화 실패..');
								}
							}
						}		
					);
			}
		});
		
		
	});
	function checkFileType(filepath) {
		var fileForamt = filepath.split(".");
		if (fileForamt.indexOf("xlsx") > -1) {
			return true;
		} else {
			return false;
		}
	}

	function check() {
		var file = $("#excel").val();
		if (file == "" || file == null) {
			alert("파일을 선택해 주세요");
			return false;
		} else if (!checkFileType(file)) {
			alert("엑셀 파일만 업로드 해주세요");
			return false
		}
		if (confirm("업로드 하시겠습니까?")) {
			var options = {
				success : function(data) {

					if (data.result) {
						console.log("성공");
						alert("성공");
						location.href = "codelist.htm";
					} else {
						console.log("실패");
						alert("실패");
					}
				},
				statusCode : {
					404 : function() {
						alert("파일 내용이 잘못되었습니다.");
						location.href = "codelist.htm";
					}
				},

				type : "POST"
			};
			$('#excelForm').ajaxSubmit(options);
		}

		
	}
</script>
<div id="codeRegisterTabs">
	<ul>
		<li><a href="#codeMgRegister">코드</a></li>
		<li><a href="#buildingRegister">건물</a></li>
		<li><a href="#classroomRegister">강의실</a></li>
		<li><a href="#officeRegister">사무실</a></li>
		<li><a href="#laboratoryRegister">연구실</a></li>
		<li><a href="#scSystemRegister">장학제도</a></li>	
		<li><a href="#scholarshipRegister">장학</a></li>
		<li><a href="#semesterRegister">학기</a></li>
		<li><a href="#academicCalendarRegister">학사일정</a></li>
		<li><a href="#colleageRegister">단과대학</a></li>
		<li><a href="#departmentRegister">학부</a></li>
		<li><a href="#mjRecordRegister">학생전공</a></li>
		<li><a href="#opened">개설강의현황</a></li>
		<li><a href="#register">등록</a></li>
		<li><a href="#initSemester">학기 초기화</a></li>
	</ul>
	<div id="codeMgRegister">
		<hr>
		CODE_MG
		<form action="codeRegister.htm" method="post">
			<table>
				<tr>
					<td>코드</td>
					<td><input type="text" name="code" id="code"></td>
				</tr>
				<tr>
					<td>구분</td>
					<td>
						<input type="radio" value="0" name="code_type" id="code_type">학생 
						<input type="radio" value="1" name="code_type" id="code_type">교수 
						<input type="radio" value="2" name="code_type" id="code_type">관리자
					</td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="code_name" id="code_name">
					</td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td><input type="text" name="code_birth" id="code_birth">
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="등록"></td>
				</tr>
			</table>
		</form>
		<a href="codelist.htm">리스트</a>
		<hr>
		<form id="excelForm" action="compExcelUpload.htm">
			파일 일괄등록: <input id="excel" name="excel" type="file"
				placeholder="Text input">
			<button type="button" id="excelUp" onclick="check()">등록하기</button>
		</form>

		<table>
			<tr>
				<td><a href="excel.htm"> 양식다운로드 </a></td>
			</tr>
		</table>
	</div>
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
	<div id="semesterRegister">
		<hr>
		SEMESTER
		<form action="insertSemester.htm" method="post">
			<table>
				<tr>
					<td>학기코드</td>
					<td><input type="text" name="semester_code" id="semester_code"></td>
				</tr>
				<tr>
					<td>학기 이름</td>
					<td>	
						<input type="text" name="semester_name" id="semester_name">
					</td>
				</tr>
				<tr>
					<td>학기 시작일</td>
					<td>
					<input type="text" name="semester_start" id="semester_start">
					</td>
				</tr>
				<tr>
					<td>학기 종료일</td>
					<td>
					<input type="text" name="semester_end" id="semester_end">
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="등록"></td>
				</tr>
			</table>
		</form>
		<a href="semesterList.htm">리스트</a>
		<hr>
		<form id="excelForm" action="compExcelUpload.htm">
			파일 일괄등록: <input id="excel" name="excel" type="file"
				placeholder="Text input">
			<button type="button" id="excelUp">등록하기</button>
		</form>

		<table>
			<tr>
				<td><a href="excel.htm"> 양식다운로드 </a></td>
			</tr>
		</table>
	</div>
	<div id="academicCalendarRegister">
		<hr>
		ACADEMIC_CALENDAR
		<form action="insertAcademicCalendar.htm" method="post">
			<table>
				<tr>
					<td>제목</td>
					<td>	
						<input type="text" name="calendar_title" id="calendar_title">
					</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
					<textarea id="calendar_content" name="calendar_content"></textarea>
					</td>
				</tr>
				<tr>
					<td>시작일</td>
					<td>
					<input type="text" name="calendar_start" id="calendar_start">
					</td>
				</tr>
				<tr>
					<td>종료일</td>
					<td>
					<input type="text" name="calendar_end" id="calendar_end">
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="등록"></td>
				</tr>
			</table>
		</form>
		<a href="academicCalendarList.htm">리스트</a>
		<hr>
		<form id="excelForm" action="compExcelUpload.htm">
			파일 일괄등록: <input id="excel" name="excel" type="file"
				placeholder="Text input">
			<button type="button" id="excelUp">등록하기</button>
		</form>

		<table>
			<tr>
				<td><a href="excel.htm"> 양식다운로드 </a></td>
			</tr>
		</table>
	</div>
	<div id="colleageRegister">
		<hr>
		COLLEGE
		<form action="insertCollege.htm" method="post">
			<table>
				<tr>
					<td>교수코드</td>
					<td>	
						<input type="text" name="professor_code" id="professor_code">
					</td>
				</tr>
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
							<option value="${cl.college_code}">${cl.college_name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>교수코드</td>
					<td>	
						<input type="text" name="professor_code" id="professor_code">
					</td>
				</tr>
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
	<div id="mjRecordRegister">
		<hr>
		MJ_RECORD
		<form action="insertMjRecord.htm" method="post">
			<table>
				<tr>
					<td>학번코드</td>
					<td><input type="text" name="student_code" id="student_code"></td>
				</tr>
				<tr>
					<td>학과코드</td>
					<td>
						<select name="department_code" id="department_code">
							<c:forEach items="${department}" var="dp">
								<option value="${dp.department_code}">${dp.department_name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>전공구분</td>
					<td>	
						<input type="radio" name="mj_type" id="mj_type" value="0">전공
						<input type="radio" name="mj_type" id="mj_type" value="1">부전공
					</td>
				</tr>
			</table>
			<input type="submit" value="등록하기">
		</form>
		<a href="mjRecordList.htm">리스트</a>
		<hr>
		<form id="mjexcelForm" action="mjExcelUpload.htm">
			파일 일괄등록: <input id="mjexcel" name="mjexcel" type="file"
				placeholder="Text input">
			<button type="button" id="mjexcelUp">등록하기</button>
		</form>

		<table>
			<tr>
				<td><a href="mjexcel.htm"> 양식다운로드 </a></td>
			</tr>
		</table>
	</div>
	<div id="opened">
		<hr>
		OPENED
		<table>
		<thead>
			<tr>
				<td>과목코드</td>
				<td>학기코드</td>
				<td>수강인원</td>SCHOLARSHIP
			</tr>
		</thead>
		<c:forEach items="${opened}" var="op" varStatus="index">
			<tbody>
				<tr>
					<td>${op.subject_name}</td>
					<td>${op.semester_name }</td>
					<td>${op.registed_seat}</td>
				</tr>
			</tbody>
		</c:forEach>
	</table>
	</div>
	<div id="register">
		<hr>
		REGISTER
		<table>
			<form action="regExcelUpload.htm" , method="post">
				<tr>
					<td>학번</td>
					<td>
					<input type="text" name="student_code" id=student_code>
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
					<td>등록구분</td>
					<td>
					<input type="radio" value="0" id="register_type" name="register_type">등록안함
					<input type="radio" value="1" id="register_type" name="register_type">등록
					</td>
				</tr>
				<tr>
					<td>등록금</td>
					<td><input type="text" id="tuition" name="tuition"></td>
				</tr>
				<tr>
					<td>등록여부</td>
					<td>
					<input type="radio" value="0" id="register_state" name="register_state">일반학기
					<input type="radio" value="1" id="register_state" name="register_state">계절학기 
					<input type="radio" value="2" id="register_state" name="register_state">졸업연기
					</td>
				</tr>
			</table>
			<input type="submit" value="등록하기">
		</form>
		<a href="registerlist.htm">리스트</a>
		<hr>
		<form id="regexcelForm" action="regExcelUpload.htm" method="post">
			파일 일괄등록: <input id="regexcel" name="regexcel" type="file"
				placeholder="Text input">
			<button type="button" id="regexcelUp">등록하기</button>
		</form>

		<table>
			<tr>
				<td><a href="regexcel.htm"> 양식다운로드 </a></td>
			</tr>
		</table>
	</div>
	
	<div id="initSemester">
		<input type="button" id="initBtn" value="학기 초기화" />
	</div>
</div>