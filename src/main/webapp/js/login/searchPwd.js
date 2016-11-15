$(function(){
	$('#searchPwdBtn').click(function() {
		console.log('버튼 클릭되었다!!!');
		$.ajax(
			{
				url : "searchPwd.htm",
				method : "post",
				dataType : "json",
				data : {userid:$('#userid').val(),email:$('#email').val()},
				success:function(data){
					if(data.pwdresult == 'success'){
						alert('임시 비밀번호를 발급하였습니다. 등록하신 메일을 확인해 주세요.');
						location.href="../index.htm";
					}else if(data.pwdresult == 'fail' || data.pwdresult == 'error'){
						alert('임시 비밀번호 발급에 실패하였습니다. 다시 시도해 주세요.');
						location.href="searchPwd.htm";
					}else if(data.pwdresult == 'incorrect'){
						alert('아이디 또는 등록된 이메일 주소가 다릅니다.');
						$('#email').focus();
					}
				}
			}
		);
	});
});