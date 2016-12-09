/*
 * @JavaScript : studentRecord.js
 * @Date : 2016.11.21
 * @Author : 최준호
 * @Desc
 * 학생의 최대 학기와 학년에 따라 고를수 있는 선택지 변경
 * 비동기 통신을 통한 학기별 성적 표시
 * 
*/
$(function() {
		$('#recordGrade').change(
						function name() {
							var maxGrade = $('#maxGrade').val();
							var maxSemester = $('#maxSemester').val();
							if ($('#recordGrade').val() != 'default') {
								$('#recordSemester').empty();
								var option = '<option value="default">학기</option><option value="1">1</option>';
								if ($('#recordGrade').val() == maxGrade
										&& maxSemester < 2) {
								}else{
									option+='<option value="2">2</option>'
								}
								$('#recordSemester').append(option);
							}
						});
		$('.recordSelect').change(
				function() {
					var maxGrade = $('#maxGrade').val();
					var maxSemester = $('#maxSemester').val();
					var grade= $('#recordGrade').val();
					var semester=$('#recordSemester').val();
					if (grade != 'default'
							&& semester != 'default') {
						$.ajax({
							url : "StudentRecordAjax.htm",
							method : "post",
							data : {grade:grade,semester:semester},
							dataType:"json",
							success:function(data){
								console.log(data);
								$('#recordView').empty();
								var rows=''
								$(data.recordList).each(function(index,record) {
									 rows+='<tr><td>'+record.stringtype+'</td><td>'+record.subject_code+'</td><td>'+record.subject_name+'</td><td>'+record.subject_credit+'</td><td>'+record.record_level+'</td><td>';
									 if(record.retake_check!=0){
										 rows+='재수강'
									 }
									 rows+='</td></tr>'; 
								});
								 rows+='<tr><td colspan="6">전공:'+data.majorCredit+'&nbsp;&nbsp;&nbsp; 교양:'+data.liberalCredit+'&nbsp;&nbsp;&nbsp; 복수전공: '+ data.doubleCredit+'&nbsp;&nbsp;&nbsp; 총 이수학점:'+data.totalCredit+'&nbsp;&nbsp;&nbsp;평점(F포함 계산):'+data.inF+'&nbsp;&nbsp;&nbsp; 평점(F제외):'+data.outF+'</td></tr>';
								 $('#recordView').append(rows);
						        
						        $('#pdfDownLoader').attr("href","recordPdfRequest.htm?grade="+grade+"&semester="+semester);
							}
					});
						
					}
				}); 
	});