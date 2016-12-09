/*
 * @JavaScript : searchOtherTimetable.js
 * @Date : 2016.11.23
 * @Author : 권기엽
 * @Desc
 * 타학생 시간표 조회 제어 JS.
*/

$(function(){
	$('#timetable_2').hide();
	$('#searchScBtn').click(function() {
		//사용자의 시간표 공유 여부 확인
		$.ajax(
			{
				url:"checkTimetableShare.htm",
				dataType:"json",
				success:function(data){
					if(data.share=='yes'){
						validCheckStudentCode($('#search_student_code').val());
					}else{
						alert('타학생 시간표 조회를 위해서는 자신의 시간표 공유를 허용해야 합니다.');
						return false;
					}
				}
			}
		);
	});
});

function validCheckStudentCode(e){
	var student_code = e;
	$.ajax(
		{
			url:"checkStudentCode.htm",
			data:{student_code:student_code},
			dataType:"json",
			success:function(data){
				if(data.check_code=='yes'){
					checkOthersShare(student_code);
				}else{
					alert('조회하고자 하는 학번이 없습니다.');
					return false;
				}
			}
		}
	);
}

function checkOthersShare(e){
	var student_code = e;
	
	$.ajax(
		{
			url:"checkOthersShare.htm",
			data:{student_code:student_code},
			dataType:"json",
			success:function(data){
				if(data.check_share=='approve'){
					loadOtherTimetable(student_code);
				}else{
					alert('조회대상이 시간표 공유를 허용하지 않았습니다.');
					return false;
				}
			}
		}
	);
}

function loadOtherTimetable(e){
	var student_code = e;
	
	$.ajax(
		{
			url:"loadOtherTimetable.htm",
			data:{student_code:student_code},
			dataType:"json",
			success:function(data){
				$.each(data.periodList, function(i, elt) {
					$('#PERIOD_START_'+(i+1)+'_2').html(elt.period_start);
				});
				
				$('#timetable_2').show();
				$.each(data.otherTimetables, function(i, elt) {
					var color="skyblue";
					var text=elt.subject_code+"<br>"+elt.subject_name+"<br>"+elt.professor_name+"<br>";

					$.each(elt.period, function(i, obj) {
						$('#'+obj.period_code+"_2").html(text);
						$('#'+obj.period_code+"_2").attr('style','background-color:'+color);
					});
				});
			}
		}
	);
}