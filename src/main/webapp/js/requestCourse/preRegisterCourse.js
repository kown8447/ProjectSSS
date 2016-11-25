/*
 * @JavaScript : preRegisterCourse.js
 * @Date : 2016.11.22
 * @Author : 권기엽
 * @Desc
 * 예비수강 신청 페이지 제어 JS.
 * 과목명 / 과목코드로 개설과목 검색 가능
 * 사용자가 선택한 과목은 시간표로 옮겨지며, 시간표에 담겨진 과목을 클릭할 경우 시간표에서 제외됨
*/

var preGradeSum=0;

$(function(){
	/*
	 * @method Name : ajax
	 * @Author : 권기엽
	 * @description : 페이지 로딩 시 사용자가 가진 Reserve 테이블 내의 정보를 가져와서 시간표에 뿌림
	*/	
	$.ajax(
		{
			url:"getPreTimetable.htm",
			dataType:"json",
			success:function(data){
				
				$.each(data.periodList, function(i, elt) {
					$('#PERIOD_START_'+(i+1)).html(elt.period_start);
				});
				
				if(data.timetable_share==0){
					$('input:radio[name=timetable_share]:input[value=0]').attr("checked", true);
				}else{
					$('input:radio[name=timetable_share]:input[value=1]').attr("checked", true);
				}
				
				$.each(data.lists, function(i, elt) {
					preGradeSum+=elt.subject_credit;
					var prev = 0;
					var prevDay = "";
					var color="";
					var text=elt.subject_code+"<br>"+elt.subject_name+"<br>"+elt.professor_name+"<br>";
					var hidden = "<input type='hidden' class='sub' id='subject_code' name='subject_code' value='"+elt.subject_code+"'/>";
					$.each(elt.period, function(i, obj) {
						var str = obj.period_code.split("_");
						if(str[1] == prevDay && str[2] - prev == 1 && obj.period_code.substr(7,1) != 1){
							if(elt.retake_check == 1){color="red";}
							else{color="skyblue";}
							$('#'+obj.period_code).attr('style','background-color:'+color);
							$('#'+obj.period_code).html(hidden);
						}else{
							if(elt.retake_check == 1){color="red";}
							else{color="skyblue";}
							$('#'+obj.period_code).html(text+hidden);
							$('#'+obj.period_code).attr('style','background-color:'+color);
						}
						prev = str[2];
						prevDay = str[1];
					});
				});
			}
		}
	);
	
	$('#dialog').hide();
	
	/*
	 * @method Name : searchBtn.click(function()
	 * @Author : 권기엽
	 * @description : 과목을 검색하여 해당 결과를 가져옴
	*/	
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
						var text="<table class='table table-hover' style='margin-top:20px'><tr style='font-size:x-small; text-align: center; position:relative;top:expression(this.offsetParent.scrollTop);'><th>과목코드</th><th>과목명</th><th>신청/정원</th><th>학점</th><th>정보</th><th>등록</th><tr>"
						$('#result').empty();
						$.each(data.lists, function(i, elt) {
							text+="<tr style='font-size:x-small; text-align: center;'><td>"+elt.subject_code+"</td><td>"+elt.subject_name+"</td><td>"+elt.reserve_seats+"/"+elt.subject_seats+"</td>" +
									"<td>"+elt.subject_credit+"</td><td><input type='button' value='강의 정보' class='info' id='"+elt.subject_code+"'" +
											"data-target='#layerpop' data-toggle='modal'/></td>" +
									"<td><input type='button' value='강의 신청' class='request' id='"+elt.subject_code+"'/></td></tr>";
						});
						$('#result').append(text);
					}
				}
		);
	});	
});

/*
 * @method Name : (document).on("click",".info",function(e)
 * @Author : 권기엽
 * @description : 과목의 상세 정보 확인
*/	
$(document).on("click",".info",function(e){
	$.ajax(
			{
				url:"getOpSubjectInfo.htm",
				data:{subject_code:e.currentTarget.id},
				dataType:"json",
				success:function(data){
					$('#subject_name').html(data.subject_info.subject_name);
					$('#subject_code2').html(data.subject_info.subject_code);
					$('#professor_name').html(data.subject_info.professor_name);
					$('#subject_credit').html(data.subject_info.subject_credit);
					var classroom="";
					var period="";
					$.each(data.subject_info.customClassroomDTO, function(i, elt) {
						classroom+="<i>"+elt.classroom_name+"</i><br>";
					});
					$.each(data.subject_info.period, function(i, elt) {
						period += elt.period_day + " : " + elt.period_start + " ~ " + elt.period_end + "<br>";
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
				}
			}
	);
});

/*
 * @method Name : $(document).on("click",".request", function(e)
 * @Author : 권기엽
 * @description : 해당 과목을 시간표에 옮김
*/	
$(document).on("click",".request", function(e){
	var subject_code  = e.currentTarget.id;
	$.ajax(
		{
			url:"checkGrade.htm",
			data:{subject_code:e.currentTarget.id},
			dataType:"json",
			success:function(data){
				if(data.result == true){
					alert('수강 대상 학년이 아닙니다.');
				}else{
					beforeSubject(subject_code);
				}
			}
		}
	);
});

function beforeSubject(e){
	$.ajax(
		{
			url:"checkBeforeSubject.htm",
			data:{subject_code:e},
			dataType:"json",
			success:function(data){
				if(data.result == 0 || data.result == 1){
					insertTimeTable(e);
				}
				else{
					alert(data.subject_code+'는 선수강 과목이 필요합니다.');
				}
			}
		}
	);
}

function insertTimeTable(e){
	
	$.ajax(
			{
				url:"getOpSubjectInfo.htm",
				data:{subject_code:e},
				dataType:"json",
				success:function(data){
					console.log(data);
					var prev = 0;
					var prevDay = "";
					var color="";
					var text=data.subject_info.subject_code+"<br>"+data.subject_info.subject_name+"<br>"+data.subject_info.professor_name+"<br>";
					var hidden = "<input type='hidden' class='sub' id='subject_code' name='subject_code' value='"+data.subject_info.subject_code+"'/>";
					
					preGradeSum+=data.subject_info.subject_credit;
					if(preGradeSum > 21){
						alert('21학점 초과 등록할 수 없습니다.');
						preGradeSum-=data.subject_info.subject_credit;
					}else{
						$.each(data.subject_info.period, function(i, elt) {
							
							if($('#'+elt.period_code).html() != ''){
								alert('먼저 등록된 시간표를 제거해 주세요.');
								preGradeSum-=data.subject_info.subject_credit;
								return false;
							}else{
								var str = elt.period_code.split("_");
								if(str[1] == prevDay && str[2] - prev == 1 && elt.period_code.substr(7,1) != 1){
									if(data.subject_info.retake_check==1){color="red";}
									else{color="skyblue";}
									$('#'+elt.period_code).attr('style','background-color:'+color);
									$('#'+elt.period_code).html(hidden);
								}else{
									if(data.subject_info.retake_check==1){color="red";}
									else{color="skyblue";}
									$('#'+elt.period_code).html(text+hidden);
									$('#'+elt.period_code).attr('style','background-color:'+color);
								}
								prev = str[2];
								prevDay = str[1];
							}
						})
					}
				}
			}
		);
}

/*
 * @method Name : $(document).on("click",".table_ele",function(e)
 * @Author : 권기엽
 * @description : 시간표 내의 과목을 클릭할 경우, 과목이 시간표에서 제외됨
*/	
$(document).on("click",".table_ele",function(e){
	var subject_code = $('#'+e.currentTarget.id+" .sub").val();
	
	if(subject_code){
		$.ajax(
				{
					url:"getSubjectCredit.htm",
					data:{subject_code:subject_code},
					dataType:"json",
					success:function(data){
						if(data.subject_credit){
							preGradeSum -= data.subject_credit;
						}
					}
					
				}
			);
			
			for(var i=1; i<=20; i++){
				if($('#PR_MON_'+i+" .sub").val()==subject_code){
					$('#PR_MON_'+i+" .sub").val('');
					$('#PR_MON_'+i).html('');
					$('#PR_MON_'+i).attr('style','background-color:white');
				}
				if($('#PR_TUE_'+i+" .sub").val()==subject_code){
					$('#PR_TUE_'+i+" .sub").val('');
					$('#PR_TUE_'+i).html('');
					$('#PR_TUE_'+i).attr('style','background-color:white');
				}
				if($('#PR_WEN_'+i+" .sub").val()==subject_code){
					$('#PR_WEN_'+i+" .sub").val('');
					$('#PR_WEN_'+i).html('');
					$('#PR_WEN_'+i).attr('style','background-color:white');
				}
				if($('#PR_THU_'+i+" .sub").val()==subject_code){
					$('#PR_THU_'+i+" .sub").val('');
					$('#PR_THU_'+i).html('');
					$('#PR_THU_'+i).attr('style','background-color:white');
				}
				if($('#PR_FRI_'+i+" .sub").val()==subject_code){
					$('#PR_FRI_'+i+" .sub").val('');
					$('#PR_FRI_'+i).html('');
					$('#PR_FRI_'+i).attr('style','background-color:white');
				}
			}
	}else{
		return false;
	}
});

/*
 * @method Name : $(document).on("click","#requestBtn",function()
 * @Author : 권기엽
 * @description : 수강신청 버튼 이벤트
*/
$(document).on("click","#requestBtn",function(){
	var array=[];
	var subject_code_list=[];
	for(var i=1; i<=20; i++){
		if($('#PR_MON_'+i+" .sub").val()){array.push($('#PR_MON_'+i+" .sub").val());}
		if($('#PR_TUE_'+i+" .sub").val()){array.push($('#PR_TUE_'+i+" .sub").val());}
		if($('#PR_WEN_'+i+" .sub").val()){array.push($('#PR_WEN_'+i+" .sub").val());}
		if($('#PR_THU_'+i+" .sub").val()){array.push($('#PR_THU_'+i+" .sub").val());}
		if($('#PR_FRI_'+i+" .sub").val()){array.push($('#PR_FRI_'+i+" .sub").val());}
	}
	
	$.each(array, function(i, elt) {
		if($.inArray(elt, subject_code_list) == -1) subject_code_list.push(elt);
	});
	
	$.ajax(
		{
			url:"requestReserve.htm",
			data:{subject_codes:subject_code_list, timetable_share:$(':radio[name="timetable_share"]:checked').val()},
			dataType:"json",
			success:function(data){
				if(data.result==true){
					location.href="preRegister.htm";
				}else{
					alert('수강신청에 실패했습니다.오류가 지속될 경우에는 관리자에게 문의 부탁드립니다.');
				}
			}
		}
	);
	
	console.log('총학점 : ' + preGradeSum);
});