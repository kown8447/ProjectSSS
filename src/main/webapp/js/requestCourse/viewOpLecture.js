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
						/*console.log(data);*/
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
						console.log(data);
						var text="<table border='1'><tr><th>구분</th><th>강의 이름</th><th>시간</th><th>지도 교수</th><th>강의 계획서</th><th>모집 인원</th></tr>";
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
							text+="<tr><td>"+major+"</td><td>"+elt.subject_name+"</td><td>";
							$.each(elt.period, function(i, period) {
								period_day = period.period_day;
								period_start = period.period_start;
								period_end = period.period_end;
								text += period_day+" "+period_start+" "+period_end+"<br>"
							});
							text += "</td><td>"+elt.professor_name+"</td>" +
									"<td><a href='download.htm?f="+elt.subject_filesrc+"'>"+elt.subject_filesrc+"</a></td>" +
											"<td>"+elt.subject_seats+"</td></tr>";
						});
						text += "</table>";
						console.log(text);
						$('#result').empty();
						$('#result').append(text);
					}
				}
				
		);
	});
});