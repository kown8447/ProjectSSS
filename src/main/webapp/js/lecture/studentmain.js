/*
 * @JavaScript : studentmain.js
 * @Date : 2016.12.02
 * @Author : 조장현
 * @Desc
 * 학생리스트 조회 및 성적입력 비동기처리
 */


var subject_code='';
var grade = '';
var select = '';
	$(function(){
			
		$("#subject").change(function(){
			subject_code = $("#subject").val();
			$.ajax(
					{
						url : "selectStudent.htm",
						data: {
							subject_code : subject_code,
			
						},
						dataType : "json",
						success : function(data){
					
							$("td").remove();
							
							$.each(data.student,function(index){
								grade = data.student[index].record_level;
								if(grade == 'A+'){
									select = "<option value='A+' selected>A+</option>";
								}else if(grade == 'A'){
									select = "<option value='A' selected>A</option>";
								}else if(grade == 'B+'){
									select = "<option value='B+' selected>B+</option>"; 
								}else if(grade == 'B'){
									select = "<option value='B' selected>B</option>"; 
								}else if(grade == 'C+'){
									select = "<option value='C+' selected>C+</option>"; 
								}else if(grade == 'C'){
									select = "<option value='C' selected>C</option>"; 
								}else if(grade == 'D+'){
									select = "<option value='D+' selected>D+</option>"; 
								}else if(grade == 'D'){
									select = "<option value='D' selected>D</option>"; 
								}else if(grade == 'F'){
									select = "<option value='F' selected>F</option>"; 
								}
								
							$("#list").append("<tr><td id='student_code_"+index+"'>"+data.student[index].student_code+"</td>"
							+"<td>"+data.student[index].member_name+"</td><td>"+data.student[index].member_email+"</td>"
							+"<td><select class='form-control' style='width:100px; float:left' id='grade_"+index+"'>"+select
							+"<option value='A+'>A+</option>" 
							+"<option value='A'>A</option>"
							+"<option value='B+'>B+</option><option value='B'>B</option><option value='C+'>C+</option>"
							+"<option value='C'>C</option><option value='D+'>D+</option><option value='D'>D</option>"
							+"<option value='F'>F</option>"
							+"</select><button class='update btn btn-success' value='btn_"+index+"'>성적등록</button></td></tr>"
							+"<input type='hidden ' id='semester"+index+"' value='"+data.student[index].semester_code+"'>");
							
							
							})		
							recordUpdateSetting();
							
									
						}
						
					}
				)
			
		});
		
		function recordUpdateSetting() {
			$('.update').click(function() {
				
			var btnIndex=$(this).val().split('_')[1];
			
			$.ajax(
					{
						url : "insertGrade.htm",
						data : {
							 subject_code: subject_code,
							student_code : $("#student_code_"+btnIndex).text(),
						   	semester_code : $("#semester"+btnIndex).val(), 
						   	record_level: $('#grade_'+btnIndex).val(), 
							
						},
						success : (function(data){
							alert("성적입력이 완료됬습니다.")
						})
					}
				);
				
			});
		}
		
	})
