/*
 * @JavaScript : postsubject.js
 * @Date : 2016.12.02
 * @Author : 조장현
 * @Desc
 * 과목 신청,삭제시 유효성 처리 및 비동기처리
 */


$(function(){
		var count = 0;
		
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
			var credit = $("#credit").val();

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
			$(this).text($('#subject_name').val());
			$(this).css("background","#47C83E");
			count+=1;
			}
		}
			
			}
		
			})
		
	

	
	})
	
		var count = 0;
		function getvalue(i){ 
		
		var credit = $("#credit").val();
			
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