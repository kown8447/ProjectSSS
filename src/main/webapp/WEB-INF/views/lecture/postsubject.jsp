<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script type="text/javascript">
	$(function(){
		var count = 0;
		var credit = ${list.subject_credit};
		$.ajax(
				{
					url : "lecturePeriod.htm",
					dataType : "json",
					success : function(data){
						
						$.each(data.periodlist,function(index,value){
							$('#PERIOD_START_'+index).html(value.period_start);
						})
						
					}
				}
				)

		$("#building").change(function(){
		//alert($("#building").val());


		$.ajax(
				{
					url : "requestsubject.htm",
					data : {
						building_code : $("#building").val(),
					},
					dataType : "json",
					success : function(data){
						var content = "<option>없음</option>";
								
						$.each(data.classroom,function(index){
						console.log(data.classroom[index].classroom_name);	
						content += "<option value='"+data.classroom[index].classroom_code+"'>"
										+data.classroom[index].classroom_name+"</option>"
						});
						$('#classroom').html(content);
						
					}
				})
		
		});

		$("#classroom").change(function(){
		//alert($("#classroom").val());
		$.ajax(
					{
						url : "requestclassroom.htm",
						data : {
							classroom_code : $("#classroom").val(),
	
						},
						dataType : "json",
						success : function(data){
						$("td").empty();
						$("td").css("background","white");
							console.log(data);
				
							$.each(data.time,function(index){
								console.log(data.time[index].period_code);
								console.log(data.time[index].classroom_code);
								if(data.time[index].period_code != "" || data.time[index].period_code != null){
									$('#'+data.time[index].period_code).html(data.time[index].subject_name);
									$('#'+data.time[index].period_code).append(" - "+data.time[index].member_name+" 교수");
									$('#'+data.time[index].period_code).css("background","skyblue");
								}
							})		
						}
						
					}
					)
			
		
		})

		$("td").click(function(){
			if($("#classroom").val()=="없음" || $("#classroom").val()=="" || $("#building").val()=="없음" || 
					$("#building").val() == ""){
				alert("강의실을 선택해주세요");		
				return false;
			}else{
				if(($(this).html()) != ""){
					alert("중복입니다")
					return false;}
				else{
				
			if(credit <= count){
			alert("그만넣어");
			return false;
			}else{
				console.log("else탐");
			$(this).text("${list.subject_name}");
			$(this).css("background","#47C83E");
			count+=1;
			}
		}
			
			}
		
			})
		
	

	
	})
	
		var count = 0;
		function getvalue(i){ 
		
		var credit = ${list.subject_credit};
			
		if(count >= credit){
				return false;
			}
			else{
				//periodArray.push(i);
				$("#period").append("<input type=hidden name=period_code value="+i+">");
				count+=1;
			}
		console.log(count);
			}

</script>
</head>
<body>

<div class="container">

	<table border=1px class="table">
		<tr>
		<th>구분</th><th>선수과목</th><th>과목명</th><th>학점</th><th>정원</th><th>수강대상</th>
		</tr>
		<tr>
		<th>	
		<c:choose>
		<c:when test = "${list.subject_type == '0'}"> 전공 </c:when>
		<c:when test = "${list.subject_type == '1'}"> 교양 </c:when>
		</c:choose>
		<c:choose>
		<c:when test = "${list.required_choice == '0' }">필수</c:when>
		<c:when test = "${list.required_choice == '1' }">선택</c:when>
		</c:choose>
		</th>
		<th>${list.before_name}</th>
		<th>${list.subject_name }</th>
		<th>${list.subject_credit}</th>
		<th>${list.subject_seats}</th>
		<th>${list.grade_limit }</th>
		</tr>
		</table>
		<br>
		</div>
	
	<form action="postRequestSubject.htm" enctype="multipart/form-data" method="post">
	
	<input type="hidden" value="${list.subject_code }" name="subject_code">
	<input type="hidden" value="${list.success_check }" name="success_check">
	<div class="row">
		<!-- 건물  -->
		<div class="col-sm-3">
		<select class="form-control" id="building" name="building_code">
			<option>없음</option>
			<option value="B_001" name="B_001">본부동</option>
			<option value="B_002" name="B_002">가츠동</option>
			<option value="B_003" name="B_003">어우동</option>
		</select>
		<label>시간</label> <div id="period" name="period"></div>
	
		<label>강의계획서</label> <input type="file" id="subject_filesrc" name="subject_filename"><br>
			
		</div>
		<div class="col-sm-3">
		<select class="form-control" id="classroom" name="classroom_code">없음
		<option value="0">없음</option>
		</select>  
	
		<%-- 		
		<select id="semester_code" name="semester_code" class="form-control">
		<c:forEach items="${semester }" var="i">
			<option value="${i.semester_code }">${i.semester_name }</option>
		</c:forEach>
		</select>
		 --%>
		</div>
	
	

	 <div class="col-sm-6">
	
			<table id="timetable" class="table table-condensed" style="table-layout: fixed;" cellpadding="5" align="center" width="200">
				<tr style='position:relative;top:expression(this.offsetParent.scrollTop);'>
					<th style="text-align: center">시간</th>
					<th style="text-align: center">월</th>
					<th style="text-align: center">화</th>
					<th style="text-align: center">수</th>
					<th style="text-align: center">목</th>
					<th style="text-align: center">금</th>
				</tr>
				<c:forEach var="i" begin="1" end="20">
					<tr bordercolor="black" style="font-size:small; text-align: center; border: 1px" height="20px">
						<th id="PERIOD_START_${i}" style="word-break: break-all; text-align: center"></td>
						<td id="PR_MON_${i}" height="auto" style="word-break: break-all;"  onclick=getvalue("PR_MON_${i}")></td>
						<td id="PR_TUE_${i}" height="auto" style="word-break: break-all;"  onclick=getvalue("PR_TUE_${i}")></td>
						<td id="PR_WEN_${i}" height="auto" style="word-break: break-all;"  onclick=getvalue("PR_WEN_${i}")></td>
						<td id="PR_THU_${i}" height="auto" style="word-break: break-all;"  onclick=getvalue("PR_THU_${i}")></td>
						<td id="PR_FRI_${i}" height="auto" style="word-break: break-all;"  onclick=getvalue("PR_FRI_${i}")></td>
					</tr>
				</c:forEach>
			
			</table>
		
				<input type="submit" id="submit" value="제출">
			
			</form>
		</div>
	</div>

	
	<button id="back" class="btn btn-primary">돌아가기</button>
</body>
</html>