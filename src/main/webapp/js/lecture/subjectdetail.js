/*
 * @JavaScript : subjectdetail.js
 * @Date : 2016.12.02
 * @Author : 조장현
 * @Desc
 * 과목 수정 유효성 처리
 */

$(function(){
		
		var success_check = $("#sc").val();
		var subject_code = $("#subject_code").val();
		
		$("#delete").click(function(){
				alert("삭제못해요");
				return false;
			
		});
		
		$("#update").click(function(){
			if(success_check != 0){
				alert("신청중이므로 수정할 수 없습니다");
				return false;
			}else{
				location.replace("lectureEdit.htm?subject_code="+subject_code);
			}
			
		});
		
		$("#request").click(function(){
			if(success_check != 0){
				alert("이미 신청된 과목입니다");
				return false;
			}else{
				location.replace("lecturePost.htm?subject_code="+subject_code+"&success_check="+success_check);
			}
		})
		
	})