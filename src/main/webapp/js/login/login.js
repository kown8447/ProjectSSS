/*
 * @JavaScript : notice.js
 * @Date : 2016.11.26
 * @Author : 송아름
 * @Desc
 * 로그인 화면에서 아이디와 비밀번호를 입력하지 않을 경우 경고창
*/


$(function(){
	$('#loginBtn').click(function() {
		console.log('버튼 클릭되었다!!!');
		if($('#member_id').val()==""){
			alert("아이디를 입력하세요.");
			return false;
		}
		
		if($('#member_pwd').val()==""){
			alert("비밀번호를 입력하세요.");
			return false;
		}
	})
	
	
	$('#searchID').click(function(){
		$('div.modal').modal({
            remote : '${pageContext.request.contextPath}/login/searchID.htm'
          	  
      })
    });
    
});