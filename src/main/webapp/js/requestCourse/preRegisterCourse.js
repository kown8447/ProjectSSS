/*
 * @JavaScript : viewOpLecture.js
 * @Date : 2016.11.18
 * @Author : 권기엽
 * @Desc
 * 개설과목을 조건에 맞게 보여주는 곳
 * 단과대학 / 학과 또는 학부를 입력받아 조건값에 맞게 화면에 출력
*/

var gradeSum=0;

$(function(){
	$('#dialog').hide();
	$('#searchBtn').click(function() {
		$.ajax(
				{
					url:"searchBykeword.htm",
					data:{
						searchType:$('#searchType').val(),
						keyword:$('#keyword').val()
					},
					dataType:"json",
					success:function(data){
						console.log(data);
						var text="<table border='1'><tr><th>과목코드</th><th>과목명</th><th>정원</th><th>정보</th><th>등록</th><tr>"
						$('#result').empty();
						$.each(data.lists, function(i, elt) {
							text+="<tr><td>"+elt.subject_code+"</td><td>"+elt.subject_name+"</td><td>"+elt.registed_seat+"/"+elt.subject_seats+"</td>" +
									"<td><input type='button' value='강의 정보' class='info' id='"+elt.subject_code+"'/></td>" +
									"<td><input type='button' value='강의 신청' class='request' id='"+elt.subject_code+"'/></td></tr>";
						});
						$('#result').append(text);
					}
				}
		);
	});	
});

$(document).on("click",".info",function(e){
	console.log('제발요..');
	$.ajax(
			{
				url:"getOpSubjectInfo.htm",
				data:{subject_code:e.currentTarget.id},
				dataType:"json",
				success:function(data){
					console.log(data);
					$('#subject_name').html(data.subject_info.subject_name);
					$('#subject_code').html(data.subject_info.subject_code);
					$('#professor_name').html(data.subject_info.professor_name);
					var classroom="";
					var period="";
					$.each(data.subject_info.customClassroomDTO, function(i, elt) {
						console.log('classroom : ' + elt);
						classroom+="<i>"+elt.classroom_name+"</i><br>";
						$.each(elt.periodlist, function(i, p) {
							period += p.period_day + " / " + p.period_start + " / " + p.period_end + "<br>";
						})
					});
					$('#classroom_name').html(classroom);
					$('#period').html(period);
					$('#grade_limit').html(data.subject_info.grade_limit);
					if(data.subject_info.required_choice == 0){
						$('#required_choice').html('필수');
					}else{
						$('#required_choice').html('선택');
					}
					$('#subject_seats').html(data.subject_info.subject_seats);
					$('#dialog').dialog({opacity: "0", width:"500"});
				}
			}
	);
});


//gradeSum+=data.subject_info.subject_credit;

$(document).on("click",".request", function(e){
	$.ajax(
		{
			url:"getOpSubjectInfo.htm",
			data:{subject_code:e.currentTarget.id},
			dataType:"json",
			success:function(data){
				var prev = 0;
				var prevDay = "";
				var days = ['MON','TUE','WEN','THU','FRI'];
				var text=data.subject_info.subject_code+"<br>"+data.subject_info.subject_name+"<br>"+data.subject_info.professor_name+"<br>";
				var hidden = "<input type='hidden' class='sub' value='"+data.subject_info.subject_code+"'/>";
				$.each(data.subject_info.period, function(i, elt) {
					//console.log(elt.period_code.substr(7,1));
					var str = elt.period_code.split("_");
					if(str[1] == prevDay && str[2] - prev == 1 && elt.period_code.substr(7,1) != 1){
						$('#'+elt.period_code).attr('style','background-color:skyblue');
						$('#'+elt.period_code).html(hidden);
					}else{
						$('#'+elt.period_code).html(text+hidden);
						$('#'+elt.period_code).attr('style','background-color:skyblue');
					}
					prev = str[2];
					prevDay = str[1];
				})
			}
		}
	);
});

$(document).on("click",".table_ele",function(e){
	console.log('e.currentTarget.id : ' + e.currentTarget.id);
	var text = $('#'+e.currentTarget.id).text();
	var subject_code = text.substr(0, 7);
	console.log('subject_code : ' + subject_code);
	console.log('hidden : ' + $('#'+e.currentTarget.id+" .sub").val());
});