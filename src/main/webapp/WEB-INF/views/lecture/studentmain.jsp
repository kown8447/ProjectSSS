<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
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
							+"<td><select id='grade_"+index+"'><option value='A+'>A+</option><option value='A'>A</option>"
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
			alert(btnIndex);	
			alert($('#student_code_'+btnIndex).text());
			alert("이게 성적:"+$('#grade_'+btnIndex).val());
			alert(subject_code);
			alert($('#semester'+btnIndex).val());
			
			
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

</script>
</head>
<body>
	<select id="subject">
		<option value="0">선택하세요</option>
		<c:forEach items="${myclass}" var="i">
			<option value="${i.subject_code }">${i.subject_name }</option>		
		</c:forEach>
	</select>
	
	<c:forEach items="${myclass }" var="y">
		<input type="hidden" id="d" value="${y.subject_code }">
	</c:forEach>
	
		<div class="container">
	<table id="list" border="1px" class="table">
	<tr><th>학번</th><th>학생명</th><th>학생이메일</th><th>성적</th></tr>
	
		</table>
		</div>


</body>
</html>