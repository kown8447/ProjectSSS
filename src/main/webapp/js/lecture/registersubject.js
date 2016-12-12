$(function(){
	$("#submit").click(function(){
		if($("#subject_credit").val() >= 5){
			alert("학점은 최대 4점까지 등록가능합니다.");
			$("#subject_credit").focus();
			return false;
		}
		
		if($("#grade_limit").val() >= 5){
			alert("학년은 4학년까지 등록가능합니다");
			$("#grade_limit").val(1);
			return false;
		}
		
	});
	
	$("#subject_credit").change(function(){
		if($("#subject_credit").val() < 1){
			alert("학점은 0보다 커야합니다");
			$("#subject_credit").val(1);
			return false;
		}
		
	})
	
	$("#grade_limit").change(function(){
		if($("#grade_limit").val() < 0){
			alert("학년은 0부터 등록 가능합니다.");
			$("#grade_limit").val(0);
			return false;
		}
	})
	
	$("#subject_seats").change(function(){
		if($("#subject_seats").val() < 1){
			alert("정원은 0보다 많아야합니다");
			$("#subject_seats").val(1);
			return false;
		}
		
	})
	$("#cancel").click(function(){
		history.go(-1)();
	})
})
