/*
 * @JavaScript : studentmain.js
 * @Date : 2016.12.02
 * @Author : 조장현
 * @Desc
 * 학생리스트 조회 및 성적입력 비동기처리
 */


var subject_code='';


	$(function(){
			
		$("#subject").change(function(){
			subject_code = $("#subject").val();
			console.log(subject_code);
			$.ajax(
					{
						url : "selectStudent.htm",
						data: {
							subject_code : subject_code,
						},
						dataType : "json",
						success : function(data){
							$("td").remove();
							console.log(data);
							$.each(data.student,function(index){
							$("#list").append("<tr><td id='student_code_"+index+"'>"+data.student[index].student_code+"</td>"
							+"<td>"+data.student[index].member_name+"</td><td>"+data.student[index].member_email+"</td>"
							+"<td><select id='grade_"+index+"'>"
							//+"<option value='"+data.student[index].record_level+"'>"+data.student[index].record_level+"</option>"
							+"<option value='A+'>A+</option><option value='A'>A</option>"
							+"<option value='B+'>B+</option><option value='B'>B</option><option value='C+'>C+</option>"
							+"<option value='C'>C</option><option value='D+'>D+</option><option value='D'>D</option>"
							+"<option value='F'>F</option>"
							+"</select><button class='update' value='btn_"+index+"'>성적등록</button></td></tr>"
							+"<input type='hidden' id='semester"+index+"' value='"+data.student[index].semester_code+"'>")
							
							
							})		
							recordUpdateSetting();
							
									
						}
						
					}
					)
			
		});
		
		function recordUpdateSetting() {
			$('.update').click(function() {
				alert("떳다업데이트버튼");
				
			var btnIndex=$(this).val().split('_')[1];
			/* alert(btnIndex);	
			alert($('#student_code_'+btnIndex).text());
			alert("이게 성적:"+$('#grade_'+btnIndex).val());
			alert(subject_code);
			alert($('#semester'+btnIndex).val()); */
			
			
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
							alert("성공스");
						})
					}
				);
				
			});
		}
		
	})
