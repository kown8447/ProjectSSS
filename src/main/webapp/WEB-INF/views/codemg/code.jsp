<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function() {

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
				statusCode:{
					404: function() {
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
<form action="codeRegister.htm" method="post">
	<table>
		<tr>
			<td>코드</td>
			<td><input type="text" name="code" id="code"></td>
		</tr>
		<tr>
			<td>구분</td>
			<td><input type="radio" value="0">학생 <input type="radio"
				value="1">교수 <input type="radio" value="2">관리자</td>
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

<!-- //http://devofhwb.tistory.com/27 -->
<!-- 형식다운로드 -->
<!-- 업로드 버튼 누를시 확장자 확인 -->
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