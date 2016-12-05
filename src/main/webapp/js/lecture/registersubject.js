$(function(){
	$("#submit").click(function(){
		if($("#subject_credit").val() >= 5){
			alert("학점은 최대 4점까지 등록가능합니다.");
			$("#subject_credit").focus();
			return false;
		}
	})
	
	$("#cancel").click(function(){
		history.go(-1)();
	})
})
