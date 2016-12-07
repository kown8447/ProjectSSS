/*
 * @JavaScript : viewOpLecture.js
 * @Date : 2016.11.18
 * @Author : 권기엽
 * @Desc
 * 개설과목을 조건에 맞게 보여주는 곳
 * 단과대학 / 학과 또는 학부를 입력받아 조건값에 맞게 화면에 출력
*/
$(function(){
	$('#collegeList').change(function() {
		$.ajax(
				{
					url:"viewDepartmentList.htm",
					data:{college_code:$('#collegeList').val()},
					dataType:"json",
					success:function(data){
						$("#departmentList").find("option").remove();
						$("#departmentList").append("<option value='1000'>학부/학과 선택</option>");
						$("#departmentList").append("<option value='100'>교양과목</option>");
						$.each(data.result, function(i, elt) {
							$("#departmentList").append("<option value='"+elt.department_code+"'>"+elt.department_name+"</option>");
						});
					}
				}
		);
	});
	
	$('#searchSubject').click(function() {
		$.ajax(
				{
					url:"searchSubject.htm",
					data:{department_code:$('#departmentList').val()},
					dataType:"json",
					success:function(data){
						var text="<table class='table table-hover'><tr style='text-align : center;' class='active'><th style='text-align: center'>구분</th><th style='text-align: center' id='order_subject_name' class='asc'>강의 이름</th><th style='text-align: center'>시간</th><th style='text-align: center' id='order_subject_name' class='asc'>지도 교수</th><th style='text-align: center'>강의 계획서</th><th style='text-align: center'>모집 인원</th></tr>";
						$.each(data.lists, function(i, elt) {
							var major="";
							if(elt.subject_type=='0'){
								major="전공";
								if(elt.required_choice=='0'){
									major+=" 필수";
								}else{
									major+=" 선택";
								}
							}else if(elt.subject_type=='1'){
								major="교양";
								if(elt.required_choice=='0'){
									major+=" 필수";
								}else{
									major+=" 선택";
								}
							}
							var period_day;
							var period_start;
							var period_end;
							text+="<tr><td style='text-align: center'>"+major+"</td><td style='text-align: center'>"+elt.subject_name+"</td><td style='text-align: center'>";
							$.each(elt.period, function(i, period) {
								period_day = period.period_day;
								period_start = period.period_start;
								period_end = period.period_end;
								text += period_day+" "+period_start+" "+period_end+"<br>"
							});
							text += "</td><td style='text-align: center'>"+elt.professor_name+"</td>" +
									"<td style='text-align: center'><a href='download.htm?f="+elt.subject_filesrc+"'>"+elt.subject_filesrc+"</a></td>" +
											"<td style='text-align: center'>"+elt.subject_seats+"</td></tr>";
						});
						text += "</table>";
						$('#result').empty();
						$('#result').append(text);
					}
				}
		);
	});
});

$(document).on("click","#order_subject_name",function(){	
	
	$.ajax(
		{
			url : "searchOpLectureOrderbySubjectName.htm",
			data : {
				department_code : $('#departmentList').val(),
				order_by : $('#order_subject_name').attr('class'),
			},
			dataType:"json",
			success:function(data){
				var text="";
				if($('#order_subject_name').attr('class')=='asc'){
					text="<table class='table table-hover'><tr style='text-align : center;' class='active'><th style='text-align: center'>구분</th><th style='text-align: center' id='order_subject_name' class='desc'>강의 이름</th><th style='text-align: center'>시간</th><th style='text-align: center' id='order_professor_name' class='desc'>지도 교수</th><th style='text-align: center'>강의 계획서</th><th style='text-align: center'>모집 인원</th></tr>";
				}
				else{
					text="<table class='table table-hover'><tr style='text-align : center;' class='active'><th style='text-align: center'>구분</th><th style='text-align: center' id='order_subject_name' class='asc'>강의 이름</th><th style='text-align: center'>시간</th><th style='text-align: center' id='order_professor_name' class='asc'>지도 교수</th><th style='text-align: center'>강의 계획서</th><th style='text-align: center'>모집 인원</th></tr>";
				}

				$.each(data.searchList, function(i, elt) {
					var major="";
					if(elt.subject_type=='0'){
						major="전공";
						if(elt.required_choice=='0'){
							major+=" 필수";
						}else{
							major+=" 선택";
						}
					}else if(elt.subject_type=='1'){
						major="교양";
						if(elt.required_choice=='0'){
							major+=" 필수";
						}else{
							major+=" 선택";
						}
					}
					var period_day;
					var period_start;
					var period_end;
					text+="<tr><td style='text-align: center'>"+major+"</td><td style='text-align: center'>"+elt.subject_name+"</td><td style='text-align: center'>";
					$.each(elt.period, function(i, period) {
						period_day = period.period_day;
						period_start = period.period_start;
						period_end = period.period_end;
						text += period_day+" "+period_start+" "+period_end+"<br>"
					});
					text += "</td><td style='text-align: center'>"+elt.professor_name+"</td>" +
							"<td style='text-align: center'><a href='download.htm?f="+elt.subject_filesrc+"'>"+elt.subject_filesrc+"</a></td>" +
									"<td style='text-align: center'>"+elt.subject_seats+"</td></tr>";
				});
				text += "</table>";
				$('#result').empty();
				$('#result').append(text);
				
				
			}
		}
	);
});



$(document).on("click","#order_professor_name",function(){	
	
	$.ajax(
		{
			url : "searchOpLectureOrderbyProfessorName.htm",
			data : {
				department_code : $('#departmentList').val(),
				order_by : $('#order_professor_name').attr('class'),
			},
			dataType:"json",
			success:function(data){
				var text="";
				if($('#order_professor_name').attr('class')=='asc'){
					text="<table class='table table-hover'><tr style='text-align : center;' class='active'><th style='text-align: center'>구분</th><th style='text-align: center' id='order_subject_name' class='desc'>강의 이름(클릭시 정렬)</th><th style='text-align: center'>시간</th><th style='text-align: center' id='order_professor_name' class='desc'>지도 교수</th><th style='text-align: center'>강의 계획서</th><th style='text-align: center'>모집 인원</th></tr>";
				}
				else{
					text="<table class='table table-hover'><tr style='text-align : center;' class='active'><th style='text-align: center'>구분</th><th style='text-align: center' id='order_subject_name' class='asc'>강의 이름(클릭시 정렬)</th><th style='text-align: center'>시간</th><th style='text-align: center' id='order_professor_name' class='asc'>지도 교수</th><th style='text-align: center'>강의 계획서</th><th style='text-align: center'>모집 인원</th></tr>";
				}

				$.each(data.searchList, function(i, elt) {
					var major="";
					if(elt.subject_type=='0'){
						major="전공";
						if(elt.required_choice=='0'){
							major+=" 필수";
						}else{
							major+=" 선택";
						}
					}else if(elt.subject_type=='1'){
						major="교양";
						if(elt.required_choice=='0'){
							major+=" 필수";
						}else{
							major+=" 선택";
						}
					}
					var period_day;
					var period_start;
					var period_end;
					text+="<tr><td style='text-align: center'>"+major+"</td><td style='text-align: center'>"+elt.subject_name+"</td><td style='text-align: center'>";
					$.each(elt.period, function(i, period) {
						period_day = period.period_day;
						period_start = period.period_start;
						period_end = period.period_end;
						text += period_day+" "+period_start+" "+period_end+"<br>"
					});
					text += "</td><td style='text-align: center'>"+elt.professor_name+"</td>" +
							"<td style='text-align: center'><a href='download.htm?f="+elt.subject_filesrc+"'>"+elt.subject_filesrc+"</a></td>" +
									"<td style='text-align: center'>"+elt.subject_seats+"</td></tr>";
				});
				text += "</table>";
				$('#result').empty();
				$('#result').append(text);
				
				
			}
		}
	);
});