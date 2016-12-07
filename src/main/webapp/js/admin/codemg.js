$(function() {

	$('#classroomInbuildings').change(function() {
		$.ajax({
			url : "classroomBuildingSelect.htm",
			method : "post",
			dataType : "json",
			data: {buildingCode :  $('#classroomInbuildings').val()},
			success : function(data) {
				$('#classRooms').empty();
				$(data.classroomList).each(function(idx,classroom) {
					var room='<tr><td>'+classroom.classroom_code+'</td><td>'+classroom.building_name
					+'</td><td><a href="classroomUpdate.htm?classroom_code='+classroom.classroom_code
					+'">'+classroom.classroom_name+'</a></td><td>'+classroom.seat
					+'</td><td>';
					if(classroom.classroom_type==0){
						room+="일반강의실";
					}else if(classroom.classroom_type==1){
						room+="실습실";
					}else{
						room+="에러데이터";
					}
					room+='</td></tr>'
					$('#classRooms').append(room);
				});
			}
		});
	});
	
	
	
	// 빌딩 등록
	$('#buildingreg').click(function() {
		if ($('#building_name').val().trim() == '') {
			alert("빌딩 이름을 입력해주세요");
			$('#building_name').focus();
			return false;
		}
		if ($('#building_addr').val().trim() == '') {
			alert("빌딩 주소를 입력해주세요");
			$('#building_addr').focus();
			return false;
		}
		$('#registerBuilding_form').submit();
	});

	// 강의실 등록
	$('#classreg').click(function() {
		if ($('#classroom_name').val().trim() == '') {
			alert("강의실 이름을 입력해주세요");
			$('#classroom_name').focus();
			return false;
		} else if ($('#seat').val().trim() == '') {
			alert("수용인원을 입력해주세요");
			$('#seat').focus();
			return false;
		} else if ($('#classroom_type').val().trim() == '') {
			alert("강의실 타입을 정해주세요");
			$('#classroom_type').focus();
			return false;
		} else {
			$('#registerClassroom_form').submit();
		}
	});
	// 사무실 등록
	$('#officereg').click(function() {
		if ($('#office_phone').val().trim() == '') {
			alert('전화번호를 입력하세요');
			$('#office_phone').focus();
			return false;
		}
		if ($('#office_name').val().trim() == '') {
			alert('사무실 이름을 입력하세요');
			$('#office_name').focus();
			return false;
		}
		$('#insertOffice_form').submit();
	});
	// 연구실 등록
	$('#labreg').click(function() {
		if ($('#lab_name').val().trim() == '') {
			alert('연구실 이름을 입력하세요');
			$('#lab_name').focus();
			return false;
		}
		if ($('#lab_phone').val().trim() == '') {
			alert('연구실 전화번호를 입력하세요');
			$('#lab_phone').focus();
			return false;
		}
		$('#insertLab_form').submit();
	});
	// 단과대학
	$('#college_reg').click(function() {
		if ($('#college_name').val().trim() == "") {
			alert('대학 이름을 입력하세요');
			$('#college_name').focus();
			return false;
		}
		if ($('#college_description').val().trim() == "") {
			alert('설명을 입력하세요');
			$('#college_description').focus();
			return false;
		}
		$('#insertCollege_form').submit();
	});
	// 학과등록
	$('#departmentreg').click(function() {
		if ($('#department_name').val().trim() == "") {
			alert('학과명을 입력하세요');
			$('#department_name').focus();
			return false;
		}
		if ($('#department_seat').val().trim() == "") {
			alert('정원을 입력하세요');
			$('#department_seat').focus();
			return false;
		}
		if ($('#graduation_credit').val().trim() == "") {
			alert('졸업 학점을 입력하세요');
			$('#graduation_credit').focus();
			return false;
		}
		if ($('#department_description').val().trim() == "") {
			alert('설명을 입력하세요');
			$('#department_description').focus();
			return false;
		}
		$('#insertDepartment_form').submit();
	});
	// 관리자 등록
	$('#adminreg').click(function() {
		if ($('#code').val().trim() == "") {
			alert('코드를 입력하세요');
			$('#code').focus();
			return false;
		}
		if ($('#code_name').val().trim() == "") {
			alert('이름을 입력하세요');
			$('#code_name').focus();
			return false;
		}
		if ($('#code_birth').val().trim() == "") {
			alert('생년월일을 입력하세요');
			$('#code_birth').focus();
			return false;
		}
		$('#adminCodeRegister_form').submit();
	});
	// 장학제도 등록
	$('#sc_reg').click(function() {
		if ($('#scholaship_name').val().trim() == "") {
			alert('장학 제도 이름을 입력하세요');
			$('#scholaship_name').focus();
			return false;
		}
		if ($('#scholaship_standard').val().trim() == "") {
			alert('선발기준을 입력하세요');
			$('#scholaship_standard').focus();
			return false;
		}
		if ($('#scholaship_member').val().trim() == "") {
			alert('수혜인원을 입력하세요');
			$('#scholaship_member').focus();
			return false;
		}
		if ($('#scholaship_amount').val().trim() == "") {
			alert('장학 금액을 입력하세요');
			$('#scholaship_amount').focus();
			return false;
		}
		$('#insertScSystem_form').submit();
	});
	// 장학생 등록
	$('#scholar_reg').click(function() {
		if ($('#student_code').val().trim() == "") {
			alert('학생코드를 입력하세요');
			$('#student_code').focus();
			return false;
		}
		if ($('#scholarship_payday').val().trim() == "") {
			alert('지급일을 입력하세요');
			$('#scholarship_payday').focus();
			return false;
		}
		$('#insertScholarship_form').submit();
	});
	// 학생 등록
	$('#student_reg').click(function() {
		if ($('#code').val().trim() == "") {
			alert('코드를 입력하세요');
			$('#code').focus();
			return false;
		}
		if ($('#code_name').val().trim() == "") {
			alert('이름을 입력하세요');
			$('#code_name').focus();
			return false;
		}
		if ($('#code_birth').val().trim() == "") {
			alert('생년월일을 입력하세요');
			$('#code_birth').focus();
			return false;
		}
		$('#studentRegister_form').submit();
	});
	// 학기 등록
	$('#semester_reg').click(function() {
		if ($('#semester_code').val().trim() == "") {
			alert('학기 코드를 입력하세요');
			$('#semester_code').focus();
			return false;
		}
		if ($('#semester_name').val().trim() == "") {
			alert('학기 이름을 입력하세요');
			$('#semester_name').focus();
			return false;
		}
		if ($('#semester_start').val().trim() == "") {
			alert('시작일을 입력하세요');
			$('#semester_start').focus();
			return false;
		}
		if ($('#semester_end').val().trim() == "") {
			alert('종료일을 입력하세요');
			$('#semester_end').focus();
			return false;
		}
		$('#insertSemester_form').submit();
	});

	$('#edit_building').click(function() {
		$('#updateBuilbilding_form').submit();
	});

	$('#edit_classroom').click(function() {
		$('#updateClassroom_form').submit();
	});
	$('#edit_office').click(function() {
		$('#updateOffice_form').submit();
	});
	$('#edit_lab').click(function() {
		$('#updateLab_form').submit();
	});
	$('#edit_college').click(function() {
		$('#updateColleage_form').submit();
	});
	$('#edit_depart').click(function() {
		$('#updateDepartment_form').submit();
	});

	// 빌딩
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

	// 강의실
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
					} else if (data.result == false) {
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

	// 사무실
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
	// 연구실
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
	// 장학제도
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

	// 장학금
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

	// 단과대학
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

	// 학부
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

	// 전공
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

	// 등록
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

	// 교수등록
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

	$('#initBtn')
			.click(
					function() {

						var r = confirm('학기를 초기화 하시겠습니까? 초기화 하게 되면 해당 학기 관련 수업/시간표 정보가 삭제 됩니다.')

						if (r == true) {
							$.ajax({
								url : "initSemester.htm",
								dataType : "json",
								success : function(data) {
									if (data.result == 'success') {
										alert('학기 초기화 성공!');
									} else {
										alert('학기 초기화 실패..');
									}
								}
							});
						}
					});

	$('#levelChangeTargetDepartment').change(
			function() {
				$.ajax({
					url : "getProfessorList.htm",
					data : {
						department_code : $('#levelChangeTargetDepartment')
								.val()
					},
					dataType : "json",
					success : function(data) {
						$('#levelChangeTargetProfessor').empty();
						$.each(data.pf_list, function(index, elt) {
							var text = "<option value='" + elt.code + "'>"
									+ elt.code_name + "</option>";
							$('#levelChangeTargetProfessor').append(text);
						});
					}
				});
			});

	$('#departmentLeaderButton').click(
			function() {

				var department = $('#levelChangeTargetDepartment').val();
				var professor = $('#levelChangeTargetProfessor').val();
				if (department == null || professor == null || department == ""
						|| professor == "") {
					alert("선택은 하고 눌러라?");
				} else {
					$('#departmentLeaderRegist').submit();
				}

			})

});
function checkFileType(filepath) {
	var fileForamt = filepath.split(".");
	if (fileForamt.indexOf("xlsx") > -1) {
		return true;
	} else {
		return false;
	}
}

// 학생코드
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

/*
 * @JavaScript : codemg @Date : 2016.12.06 @Author : 김영빈 @Desc 파일 업로드 할 경우 파일
 * 경로와 이름 표시
 */
(function(global, $) {

	$(makeObjFile);

	function makeObjFile() {
		var inputFile = CustomFiletype();
		inputFile.init($('.filetype'));
	}

	function CustomFiletype() {
		if (this === window)
			return new CustomFiletype;
		this.$fileBox = null;
		this.$fileUpload = null;
	}

	CustomFiletype.prototype = {

		'init' : function(fileClass) {
			this.$fileBox = fileClass || $('.filetype');
			this.initEvent();
		},

		'initEvent' : function() {
			this.fileUpload();
		},

		'fileUpload' : function() {
			var _self = this;

			$.each(_self.$fileBox, function(idx, item) {

				var _$fileBox = _self.$fileBox.eq(idx), _$fileType = _$fileBox
						.find('input[type=file]'), _$fileText = _$fileBox
						.find('input[type=text]');
				_$fileText.attr('disabled', 'disabled');

				_$fileType.on('change', function() {
					var filePath = $(this).val();
					_$fileText.val(filePath);
				})

			})
		}

	}

})(window, jQuery);
