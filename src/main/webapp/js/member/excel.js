$(function(){
	
	function checkFileType(filepath){
		var fileForamt = filepath.split(".");
		if(fileForamt.indexOf("xls") > -1){
			return true;
		}else{
			return false;
		}
	}
	
	function check(){
		var file = $("#excel").val();
		if(file == "" || file == null){
			alert("파일을 선택해 주세요");
			return false;
		}else if(!checkFileType(file)){
			alert("엑셀 파일만 업로드 해주세요");
			return false
		}
		if(confirm("업로드 하시겠습니까?")){
			$("#excelUpForm").attr("action", "/member/compExcelUpload");
					var options = {
							sucess : function(data) {
								alert("모든 데이터가 업로드 되었습니다.");
								$("#ajax-content").html(data);
							},
							type : "POST"
					};
					$('form').ajaxSubmit(options);
		}
	}
});