/*
 * @JavaScript : aside.js
 * @Date : 2016.12.09
 * @Author : 권기엽, 최준호
 * @Desc
 * side menu를 관여하는 javascript
 * 개인정보 비동기 요청 및 시간표 출력
*/
$(function() {
	$('#viewTimetable').click(function() {
		$('#tableviewer').modal({
			remote : 'goTimetableForm.htm'
		});
	});

	$('#sidebar-btn').click(function() {
		$('#sidebar').toggleClass('visible');
	});

	$.ajax({
		url : "/initspring/member/persnalDataCall.htm",
		method : "post",
		dataType : "json",
		success : function(data) {
			if (data.userType == 'student') {
				$('#asideStuCode').append(data.student.student_code);
				$('#asideStuName').append(data.student.member_name);
				$('#asideStuGrade').append(data.student.grade);
				$('#asideStuCollege').append(data.student.college_name);
				$('#asideStuDepartMent').append(data.student.department_name);
				$('#asidePicture').attr('src','/initspring/profiles/'+data.student.member_picture);
			} else if (data.userType == 'professor') {
				$('#asideProfCode').append(data.professor.professor_code);
				$('#asideProfName').append(data.professor.member_name);
				$('#asideProfCollege').append(data.professor.college_name);
				$('#asideProfDepartMent').append(data.professor.department_name);
				$('#asidePicture').attr('src','/initspring/profiles/'+data.professor.member_picture);
			} else if (data.userType == 'admin') {
				$('#asideAdminCode').append(data.admin.admin_code);
				$('#asideAdminName').append(data.admin.member_name);
				$('#asidePicture').attr('src','/initspring/profiles/'+data.admin.member_picture);
			}

		}
	});
});

