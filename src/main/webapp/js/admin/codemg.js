/*
 * @JavaScript : codemg.js
 * @Date : 2016.11.21
 * @Author : 성홍모
 * @Desc
 * 관리자 기능의 유효성 검증 
 * 
*/

$(function() {
	
	//장학등록시 학번 유효성 검증
	var student_check = false;
	$('#check_student_code').click(function() {
		$.ajax(
			{
				url:"check_student_code.htm",
				data:{student_code : $('#student_code').val()},
				dataType:"json",
				success:function(data){
					if(data.result=='success'){
						alert('유효한 학번입니다.');
						student_check=true;
					}else{
						alert('유효하지 않은 학번입니다.');
						student_check=false;
					}
				}
			}
		);
	});
	//학생을 등록시킬시 학번 유효성 검증
	$('#check_student_code_register').click(function() {
		$.ajax(
			{
				url:"check_student_code.htm",
				data:{student_code : $('#student_code').val()},
				dataType:"json",
				success:function(data){
					if(data.result=='success'){
						alert('유효한 학번입니다.');
						student_check=true;
					}else{
						alert('유효하지 않은 학번입니다.');
						student_check=false;
					}
				}
			}
		);
	});
	
	//강의실 목록 보기에서 건물별로 강의실 목록을 출력
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
	
	//전화번호 유효성 검증
	var reg_phone = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/;
	var phoneCheck=false;
	
	//사무실 전화번호 수정 유효성 검증
	$('#office_phone').keyup(function() {
		if(!reg_phone.test($('#office_phone').val())){
			$("#officePoneCheck").html(
			"<div style='color:red'>유효한 전화번호가 아닙니다</div>");
			phoneCheck=false;
		}else{
			$("#officePoneCheck").empty();
			phoneCheck=true;
		}
	});
	$('#office_phone').change(function() {
		if(!reg_phone.test($('#office_phone').val())){
			$("#officePoneCheck").html(
			"<div style='color:red'>유효한 전화번호가 아닙니다</div>");
			phoneCheck=false;
		}else{
			$("#officePoneCheck").empty();
			phoneCheck=true;
		}
	});
	//사무실 전화번호 등록 유효성 검증
	$('#office_phone_insert').keyup(function() {
		if(!reg_phone.test($('#office_phone_insert').val())){
			$("#officeInsertPhoneCheck").html(
			"<div style='color:red'>유효한 전화번호가 아닙니다</div>");
			phoneCheck=false;
		}else{
			$("#officeInsertPhoneCheck").empty();
			phoneCheck=true;
		}
	});
	$('#office_phone_insert').change(function() {
		if(!reg_phone.test($('#office_phone_insert').val())){
			$("#officeInsertPhoneCheck").html(
			"<div style='color:red'>유효한 전화번호가 아닙니다</div>");
			phoneCheck=false;
		}else{
			$("#officeInsertPhoneCheck").empty();
			phoneCheck=true;
		}
	});
	//연구실 전화번호 등록 유효성 검증
	$('#lab_phone_insert').keyup(function() {
		if(!reg_phone.test($('#lab_phone_insert').val())){
			$("#labInsertPhoneCheck").html(
			"<div style='color:red'>유효한 전화번호가 아닙니다</div>");
			phoneCheck=false;
		}else{
			$("#labInsertPhoneCheck").empty();
			phoneCheck=true;
		}
	});
	$('#lab_phone_insert').change(function() {
		if(!reg_phone.test($('#lab_phone_insert').val())){
			$("#labInsertPhoneCheck").html(
			"<div style='color:red'>유효한 전화번호가 아닙니다</div>");
			phoneCheck=false;
		}else{
			$("#labInsertPhoneCheck").empty();
			phoneCheck=true;
		}
	});

	//연구실 전화번호 수정 유효성 검증
	$('#lab_phone').keyup(function() {
		if(!reg_phone.test($('#lab_phone').val())){
			$("#labPhoneCheck").html(
			"<div style='color:red'>유효한 전화번호가 아닙니다</div>");
			phoneCheck=false;
		}else{
			$("#labPhoneCheck").empty();
			phoneCheck=true;
		}
	});
	$('#lab_phone').change(function() {
		if(!reg_phone.test($('#lab_phone').val())){
			$("#labPhoneCheck").html(
			"<div style='color:red'>유효한 전화번호가 아닙니다</div>");
			phoneCheck=false;
		}else{
			$("#labPhoneCheck").empty();
			phoneCheck=true;
		}
	});
	
	//데이트 피커
	$("#code_birth, #scholarship_payday, #semester_start, #semester_end")
			.datepicker(
					{
						prevText : '이전달',
						nextText : '다음달',
						currentText : '오늘',
						monthNames : [ '1월(JAN)', '2월(FEB)', '3월(MAR)',
								'4월(APR)', '5월(MAY)', '6월(JUN)', '7월(JUL)',
								'8월(AUG)', '9월(SEP)', '10월(OCT)', '11월(NOV)',
								'12월(DEC)' ],
						monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월',
								'7월', '8월', '9월', '10월', '11월', '12월' ],
						dayNames : [ '일', '월', '화', '수', '목', '금', '토' ],
						dayNamesShort : [ '일', '월', '화', '수', '목', '금', '토' ],
						dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
						weekHeader : 'Wk',
						dateFormat : 'yy-mm-dd',
						firstDay : 0,
						isRTL : false,
						showMonthAfterYear : true,
						yearSuffix : '',
						changeMonth : true,
						changeYear : true,
						yearRange : '1950:2020'
					});

	//건물 등록시 유효성 검증
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

	//강의실 등록시 유효성 검증
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
	//사무실 등록시 유효성 검증
	$('#officereg').click(function() {
		if ($('#office_name').val().trim() == '') {
			alert('사무실 이름을 입력하세요');
			$('#office_name').focus();
			return false;
		}
		if ($('#office_phone_insert').val().trim() == '') {
			alert('전화번호를 입력하세요');
			$('#office_phone_insert').focus();
			return false;
		}
		if(!phoneCheck){
			alert('유효한 전화번호가 아닙니다');
			$('#office_phone_insert').focus();
			return false;
		}
		$('#insertOffice_form').submit();
	});
	//연구실 등록시 유효성 검증
	$('#labreg').click(function() {
		if ($('#lab_name').val().trim() == '') {
			alert('연구실 이름을 입력하세요');
			$('#lab_name').focus();
			return false;
		}
		if ($('#lab_phone_insert').val().trim() == '') {
			alert('연구실 전화번호를 입력하세요');
			$('#lab_phone_insert').focus();
			return false;
		}
		if(!phoneCheck){
			alert('유효한 전화번호가 아닙니다');
			$('#labInsertPhoneCheck').focus();
			return false;
		}
		$('#insertLab_form').submit();
	});
	//단과대학 등록시 유효성 검증
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
	//학과 등록시 유효성 검증
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
	//관리자 등록시 유효성 검증
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
	//장학제도 등록시 유효성 검증
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
	//장학생 등록시 유효성 검증
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
		if(student_check==false){
			alert('학번 유효성 검증을 진행해 주세요.');
			return false;
		}
		$('#insertScholarship_form').submit();
	});
	//학생 등록시 유효성 검증
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
	//학기 등록시 유효성 검증
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
	//교수 등록시 유효성 검증
	$('#professor_reg').click(function() {

		if ($('#code').val().trim() == "") {
			alert('코드를 입력하세요');
			$('#code').focus();
			return false;
		}
		if ($('#department_code').val().trim() == "") {
			alert('학부를 입력하세요');
			$('#department_code').focus();
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
		$('#professorCodeRegister_form').submit();
	});

	//등록 등록시 유효성 검증
	$('#register_reg').click(function() {

		if ($('#student_code').val().trim() == "") {
			alert('코드를 입력하세요');
			$('#student_code').focus();
			return false;
		}
		if ($('#tuition').val().trim() == "") {
			alert('등록금을 입력하세요');
			$('#tuition').focus();
			return false;
		}
		if ($('#register_state').val().trim() == "") {
			alert('등록구분을 입력하세요');
			$('#register_state').focus();
			return false;
		}
		$('#insertRegister_form').submit();
	});

	//건물 수정시 유효성 검증
	$('#edit_building').click(function() {
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
		$('#updateBuilbilding_form').submit();
	});
	//학기 수정시 유효성 검증
	$('#edit_semester').click(function() {
		if ($('#semester_name').val().trim() == '') {
			alert("학기 이름을 입력해주세요");
			$('#semester_name').focus();
			return false;
		}
		if ($('#semester_start').val().trim() == '') {
			alert("학기 시작일을	 입력해주세요");
			$('#semester_start').focus();
			return false;
		}
		if ($('#semester_end').val().trim() == '') {
			alert("학기 종료일을 입력해주세요");
			$('#semester_end').focus();
			return false;
		}
		$('#updateSemester_form').submit();
	});
	//강의실 수정시 유효성 검증
	$('#edit_classroom').click(function() {
		if ($('#classroom_name').val().trim() == '') {
			alert("강의실 이름을 입력해주세요");
			$('#classroom_name').focus();
			return false;
		} else if ($('#seat').val().trim() == '') {
			alert("수용인원을 입력해주세요");
			$('#seat').focus();
			return false;
		}
		$('#updateClassroom_form').submit();
	});
	//사무실 수정시 유효성 검증
	$('#edit_office').click(function() {
		if ($('#office_name').val().trim() == '') {
			alert('사무실 이름을 입력하세요');
			$('#office_name').focus();
			return false;
		}
		if ($('#office_phone').val().trim() == '') {
			alert('전화번호를 입력하세요');
			$('#office_phone').focus();
			return false;
		}
		if(!phoneCheck){
			alert('유효한 전화번호가 아닙니다');
			$('#office_phone').focus();
			return false;
		}
		$('#updateOffice_form').submit();
	});
	//연구실 수정시 유효성 검증
	$('#edit_lab').click(function() {
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
		if(!phoneCheck){
			alert('유효한 전화번호가 아닙니다');
			$('#lab_phone').focus();
			return false;
		}
		$('#updateLab_form').submit();
	});
	//단대 수정시 유효성 검증
	$('#edit_college').click(function() {
		if ($('#college_name').val().trim() == '') {
			alert('대학 이름을 입력하세요');
			$('#college_name').focus();
			return false;
		}
		if ($('#college_description').val().trim() == '') {
			alert('부연 설명을 입력하세요');
			$('#college_description').focus();
			return false;
		}
		$('#updateColleage_form').submit();
	});
	//학과 수정시 유효성 검증
	$('#edit_depart').click(function() {
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
		$('#updateDepartment_form').submit();
	});
	//장학생 수정시 유효성 검증
	$('#edit_scholarship').click(function() {
		if ($('#scholarship_payday').val().trim() == "") {
			alert('지급일을 입력하세요');
			$('#scholarship_payday').focus();
			return false;
		}
		$('#updateScholarship_from').submit();
	});
	//학생 수정
	$('#edit_student').click(function() {
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
		$('#updateCode_form').submit();
	});
	//장학제도 수정시 유효성 검증
	$('#edit_scsystem').click(function() {
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
		$('#scsytemUpdate_form').submit();
	});
	//신청 거절시 유효성 검증
	$('#rejectbtn').click(function() {
		if ($('#rejectReason').val().trim() == "") {
			alert('거절사유를 입력하세요');
			$('#rejectReason').focus();
			return false;
		}
		$('#rejectForm').submit();
	});

	//빌딩 수정시 유효성 검증
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

	//강의실 엘셀파일 등록시 유효성 검증
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

	//사무실 엘셀파일 등록시 유효성 검증
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
	//연구실 엘셀파일 등록시 유효성 검증
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
	//장학제도 엘셀파일 등록시 유효성 검증
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

	//장학금 엘셀파일 등록시 유효성 검증
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
	//단과대학 엘셀파일 등록시 유효성 검증
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
	//학부 엘셀파일 등록시 유효성 검증
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
	//전공 엘셀파일 등록시 유효성 검증
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
	//등록 엘셀파일 등록시 유효성 검증
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
	//교수등록 엘셀파일 등록시 유효성 검증
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
	//학기 초기화 버튼 클릭
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
	//교수 검색기능
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
	//학과장 등록시 유효성 검증
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
/*
 * @JavaScript : codemg.js
 * @Date : 2016.11.21
 * @Author : 성홍모
 * @Desc
 * 앨셀등록시 파일형식 유효성 검증
 * 
*/
function checkFileType(filepath) {
	var fileForamt = filepath.split(".");
	if (fileForamt.indexOf("xlsx") > -1) {
		return true;
	} else {
		return false;
	}
}
/*
 * @JavaScript : codemg.js
 * @Date : 2016.11.21
 * @Author : 성홍모
 * @Desc
 * 학생코드 엑셀파일 일괄등록시 유효성 검증
 * 
*/
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
 * @JavaScript : codemg
 * @Date : 2016.12.06
 * @Author : 김영빈
 * @Desc
 * 파일 업로드 할 경우 파일 경로와 이름 표시 
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