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
							location.href = "showclasslist.htm";
						}else if(data.result==false){
							console.log("실패");
							alert("실패");
						}
					},
					statusCode : {
						404 : function() {
							alert("파일 내용이 잘못되었습니다.");
							location.href = "showclasslist.htm";
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
							location.href = "showofficelist.htm";
						} else {
							console.log("실패");
							alert("실패");
						}
					},
					statusCode : {
						404 : function() {
							alert("파일 내용이 잘못되었습니다.");
							location.href = "showofficelist.htm";
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
							location.href = "showlablist.htm";
						} else {
							console.log("실패");
							alert("실패");
						}
					},
					statusCode : {
						404 : function() {
							alert("파일 내용이 잘못되었습니다.");
							location.href = "showlablist.htm";
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

		//교수등록
		$('#professorexcelUp').click(function() {
			var file = $("#professorexcel").val();
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
							location.href = "typeofcodelist.htm?code_type=1";
						} else {
							console.log("실패");
							alert("실패");
						}
					},
					statusCode : {
						404 : function() {
							alert("파일 내용이 잘못되었습니다.");
							location.href = "typeofcodelist.htm?code_type=1";
						}
					},

					type : "POST"
				};
				$('#professorexcelForm').ajaxSubmit(options);
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
	
	//학생코드
	function check(){
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
						location.href = "typeofcodelist.htm?code_type=0";
					} else {
						console.log("실패");
						alert("실패");
					}
				},
				statusCode : {
					404 : function() {
						alert("파일 내용이 잘못되었습니다.");
						location.href = "typeofcodelist.htm?code_type=0";
					}
				},

				type : "POST"
			};
			$('#excelForm').ajaxSubmit(options);
		}

		
	}