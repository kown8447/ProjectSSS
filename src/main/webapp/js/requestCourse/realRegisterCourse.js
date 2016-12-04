/*
 * @JavaScript : realRegisterCourse.js
 * @Date : 2016.11.23
 * @Author : 권기엽
 * @Desc
 * 본 수강 신청 페이지 제어 JS.
 * 과목명 / 과목코드로 개설과목 검색 가능
 * 사용자가 선택한 과목은 시간표로 옮겨지며, 시간표에 담겨진 과목을 클릭할 경우 시간표에서 제외됨
 * 정원 초과 과목에 대해서는 신청 불가능
*/

var realGradeSum=0;	//총학점



$(function(){
	
	onloadRealtable();
	
	/*
	 * @method Name : searchBtn.click(function()
	 * @Author : 권기엽
	 * @description : 과목을 검색하여 해당 결과를 가져옴
	*/	
	$('#real_searchBtn').click(function() {
		$.ajax(
				{
					url:"searchBykeword.htm",
					data:{
						searchType:$('#real_searchType').val(),
						keyword:$('#real_keyword').val()
					},
					dataType:"json",
					success:function(data){
						var text="<table class='table table-hover' style='margin-top:40px'><tr><td colspan='6' style='color:blue; font-size:x-small; text-align: center; position:relative;top:expression(this.offsetParent.scrollTop);'>검색 결과</td></tr><tr><th style='text-align:center'>과목코드</th><th style='text-align:center'>과목명</th><th style='text-align:center'>정원</th><th style='text-align:center'>학점</th><th style='text-align:center'>정보</th><th style='text-align:center'>등록</th></tr>"
						$('#real_result').empty();
						$.each(data.lists, function(i, elt) {
							text+="<tr style='font-size:x-small; text-align: center;'><td>"+elt.subject_code+"</td><td>"+elt.subject_name+"</td><td>"+elt.registed_seat+"/"+elt.subject_seats+"</td>" +
									"<td>"+elt.subject_credit+"</td><td><input type='button' value='강의 정보' style='font-size:8pt;' class='real_info btn btn-xs' id='"+elt.subject_code+"'" +
											"data-target='#real_layerpop' data-toggle='modal'/></td>" +
									"<td><input type='button' value='강의 신청' style='background-color:#FDCECE;font-size:8pt;' class='real_request btn-xs btn' id='"+elt.subject_code+"'/></td></tr>";
						});
						$('#real_result').append(text);
					}
				}
		);
	});	

});


/*
 * @method Name : (document).on("click","real_info",function(e) @Author : 권기엽
 * @description : 과목의 상세 정보 확인
 */	
$(document).on("click",".real_info",function(e){
	$.ajax(
			{
				url:"getOpSubjectInfo.htm",
				data:{subject_code:e.currentTarget.id},
				dataType:"json",
				success:function(data){
					$('#real_subject_name').html(data.subject_info.subject_name);
					$('#real_subject_code').html(data.subject_info.subject_code);
					$('#real_professor_name').html(data.subject_info.professor_name);
					$('#real_subject_credit').html(data.subject_info.subject_credit);
					var classroom="";
					var period="";
					$.each(data.subject_info.customClassroomDTO, function(i, elt) {
						classroom+=elt.classroom_name+"<br>";
						
					});
					$.each(data.subject_info.period, function(i, p) {
						period += p.period_day + " : " + p.period_start + " ~ " + p.period_end + "<br>";
					})
					$('#real_classroom_name').html(classroom);
					$('#real_period').html(period);
					$('#real_grade_limit').html(data.subject_info.grade_limit);
					if(data.subject_info.required_choice == 0){
						$('#real_required_choice').html('필수');
					}else{
						$('#real_required_choice').html('선택');
					}
					$('#real_subject_seats').html(data.subject_info.subject_seats);
				}
			}
	);
});

/*
 * @method Name : onloadRealtable
 * @Author : 권기엽
 * @description : 페이지 로딩 시 사용자가 가진 Enrollment 테이블 내의 정보를 가져와서 시간표에 뿌림 + 수강 실패 과목을 최상단으로 보여줌
*/	
function onloadRealtable(){
	$.ajax(
		{
			url:"getRealTimetable.htm",
			dataType:"json",
			success:function(data){
				var failText="<table class='table table-hover' style='margin-top:40px'><tr><td colspan='6' style='color:red; font-size:x-small; text-align: center; position:relative;top:expression(this.offsetParent.scrollTop);'>예비 수강신청 실패 과목</td></tr><tr style='text-align:center'><th>과목코드</th><th>과목명</th><th>정원</th><th>학점</th><th>정보</th><th>등록</th><tr/>";
				$('#fail_result').empty();
				$.each(data.failedLists, function(i, elt) {
					failText+="<tr style='font-size:x-small; text-align: center;'><td>"+elt.subject_code+"</td><td>"+elt.subject_name+"</td><td>"+elt.registed_seat+"/"+elt.subject_seats+"</td>" +
					"<td>"+elt.subject_credit+"</td><td><input type='button' value='강의 정보' class='real_info' id='"+elt.subject_code+"'" +
							"data-target='#real_layerpop' data-toggle='modal'/></td>" +
					"<td><input type='button' value='강의 신청' class='real_request' id='"+elt.subject_code+"'/></td></tr>";
				});
				$('#fail_result').append(failText);
				
				$.each(data.periodList, function(i, elt) {
					$('#PERIOD_START_'+(i+1)+'_3').html(elt.period_start);
				});
				
				$.each(data.lists, function(i, elt) {
					realGradeSum+=elt.subject_credit;
					var prev = 0;
					var prevDay = "";
					var color="";
					var text=elt.subject_code+"<br>"+elt.subject_name+"<br>"+elt.professor_name+"<br>";
					var hidden = "<input type='hidden' class='real_sub' id='subject_code' name='subject_code' value='"+elt.subject_code+"'/>";
					$.each(elt.period, function(i, obj) {
						var str = obj.period_code.split("_");
						if(str[1] == prevDay && str[2] - prev == 1 && obj.period_code.substr(7,1) != 1){
							if(elt.retake_check == 1){color="red";}
							else{color="skyblue";}
							$('#'+obj.period_code+'_3').attr('style','background-color:'+color);
							$('#'+obj.period_code+'_3').html(hidden);
						}else{
							if(elt.retake_check == 1){color="red";}
							else{color="skyblue";}
							$('#'+obj.period_code+'_3').html(text+hidden);
							$('#'+obj.period_code+'_3').attr('style','background-color:'+color);
						}
						prev = str[2];
						prevDay = str[1];
					});
				});
				console.log('첫 로딩시 가져오는 학점 : ' + realGradeSum);
			}
		}
	);
}


/*
 * @method Name : $(document).on("click",".request", function(e)
 * @Author : 권기엽
 * @description : 해당 과목을 시간표에 옮김
*/	
$(document).on("click",".real_request", function(e){
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
					realBeforeSubject(subject_code);
				}
			}
		}
	);
});

function realBeforeSubject(e){
	$.ajax(
		{
			url:"checkBeforeSubject.htm",
			data:{subject_code:e},
			dataType:"json",
			success:function(data){
				if(data.result == 0 || data.result == 1){
					realInsertTimeTable(e);
				}
				else{
					alert(data.subject_code+'는 선수강 과목이 필요합니다.');
				}
			}
		}
	);
}

function realInsertTimeTable(e){

	$.ajax(
			{
				url:"getOpSubjectInfo.htm",
				data:{subject_code:e},
				dataType:"json",
				success:function(data){
					var flag=true;
					realGradeSum+=data.subject_info.subject_credit;
					if(realGradeSum > 21){
						alert('21학점 초과 등록할 수 없습니다.');
						realGradeSum-=data.subject_info.subject_credit;
					}else{
						$.each(data.subject_info.period, function(i, elt) {
							if($('#'+elt.period_code+'_3').html() != ''){
								alert('시간이 중복되는 과목이 있습니다.');
								realGradeSum-=data.subject_info.subject_credit;
								flag=false;
								return false;
							}	
						})
					}
					if(flag==true){
						insertRealDbSubject(data.subject_info.subject_code, data.subject_info.subject_credit);
					}
				}
			}
		);
}

/*
 * @method Name : insertRealDbSubject()
 * @Author : 권기엽
 * @description : 수강 등록 버튼을 눌렀을 경우, 시간표에 들어가기 이전 DB에서 Insert 작업을 먼저 해둔뒤에 시간표에 출력한다.
*/

var socket1 = null;	//소켓

function insertRealDbSubject(e,c){
	var subject_code = e;
	var subject_credit = c;

	socket1 = new WebSocket("ws://192.168.0.238:8090/initspring/wait.htm");
	
	socket1.onmessage = function(evt) {
		$('#waitlist').empty();
		$('#waitlist').append('신청 대기자열 : <font style="color:blue"><b>' + evt.data + '</b></font> 명');
	};
	
	var ajax = $.ajax(
		{
			url:"insertRealDbSubject.htm",
			data:{subject_code:subject_code},
			dataType:"json",
			beforeSend:function(){
				$('#wait_layerpop').modal('toggle')
			},
			success:function(data){
				if(data.map.result=='success'){
					alert('강의 신청에 성공하였습니다.');
				}else if(data.map.result=='over'){
					alert('수강 정원이 넘었습니다.');
				}else{
					alert('일시적인 장애가 발생하였습니다. 지속될 경우 관리자에게 문의해주세요.');
				}
			},
			complete:function(){
				location.href="realRegiser.htm";
			}
		}	
	);
}



/*
 * @method Name : $(document).on("click",".real_table_ele",function(e)
 * @Author : 권기엽
 * @description : 시간표 내의 과목을 클릭할 경우, 과목이 시간표에서 제외됨
*/	
$(document).on("click",".real_table_ele",function(e){
	var subject_code = $('#'+e.currentTarget.id+" .real_sub").val();
	
	if(subject_code){
		
		var r = confirm("해당 과목을 수강 취소 하시겠습니까?");
		
		if(r==true){
			$.ajax(
					{
						url:"deleteSubject.htm",
						data:{subject_code:subject_code},
						dataType:"json",
						success:function(data){
							if(data.subject_credit){
								realGradeSum -= data.subject_credit;
							
							
								for(var i=1; i<=20; i++){
									if($('#PR_MON_'+i+'_3 .real_sub').val()==subject_code){
										$('#PR_MON_'+i+'_3 .real_sub').val('');
										$('#PR_MON_'+i+'_3').html('');
										$('#PR_MON_'+i+'_3').attr('style','background-color:white');
									}
									if($('#PR_TUE_'+i+'_3 .real_sub').val()==subject_code){
										$('#PR_TUE_'+i+'_3 .real_sub').val('');
										$('#PR_TUE_'+i+'_3').html('');
										$('#PR_TUE_'+i+'_3').attr('style','background-color:white');
									}
									if($('#PR_WEN_'+i+"_3 .real_sub").val()==subject_code){
										$('#PR_WEN_'+i+"_3 .real_sub").val('');
										$('#PR_WEN_'+i+'_3').html('');
										$('#PR_WEN_'+i+'_3').attr('style','background-color:white');
									}
									if($('#PR_THU_'+i+"_3 .real_sub").val()==subject_code){
										$('#PR_THU_'+i+"_3 .real_sub").val('');
										$('#PR_THU_'+i+'_3').html('');
										$('#PR_THU_'+i+'_3').attr('style','background-color:white');
									}
									if($('#PR_FRI_'+i+"_3 .real_sub").val()==subject_code){
										$('#PR_FRI_'+i+"_3 .real_sub").val('');
										$('#PR_FRI_'+i+'_3').html('');
										$('#PR_FRI_'+i+'_3').attr('style','background-color:white');
									}
								}
								location.href="realRegiser.htm";
							}
						}
						
					}
				);
		}		
	}else{
		return false;
	}
});