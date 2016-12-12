$(function(){
	
	$("#submit").click(function(){
		if($("#subject_credit").val() >= 5){
			alert("학점은 최대 4점까지 등록가능합니다");
			$("#subject_credit").focus();
			return false;
		}else if($("#subject_credit").val() < 1){
			alert("학점은 1점 이상 등록가능합니다.");
			$("#subject_credit").val(1);
			return false;
		} 
		

		if($("#grade_limit").val() >= 5){
			alert("학년은 4학년까지 등록가능합니다");
			$("#grade_limit").focus();
			return false;
		}else if($("#grade_limit").val < 0){
			alert("학년은 0부터 등록이 가능합니다.");
			$("#grade_limit").val(0);
			return false;
		}
		
		if($("#subject_seats").val() < 1){
			alert("정원은 1명 이상이어야 합니다.");
			$("#subject_seats").val(1);
			return false;
		}
		
		
	})
	
	$("#back").click(function(){
		history.go(-1)();
	})
	
})
