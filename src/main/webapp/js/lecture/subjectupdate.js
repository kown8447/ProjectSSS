$(function(){
	
	$("#submit").click(function(){
		if($("#submit_credit").val() >= 5){
			alert("학점은 최대 4점까지 등록가능합니다");
			$("#submit_credit").focus();
			return false;aaa
		} 

		if($("#grade_limit").val() >= 5){
			alert("학년은 4학년까지 등록가능합니다");
			$("#grade_limit").focus();
			return false;
		}
	})
	
	$("#back").click(function(){
		 history.go(-1)();
	})
	
})
