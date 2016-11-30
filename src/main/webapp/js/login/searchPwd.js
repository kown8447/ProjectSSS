/*
 * @JavaScript : searchPwd.js
 * @Date : 2016.11.16
 * @Author : 권기엽
 * @Desc
 * 비밀번호 찾기 서비스 유효성 검증.
 * 비동기 처리 후, 성공할 경우 메인으로 / 실패할 경우 다시 비밀번호 찾기 창으로 이동
*/
$(function(){
	$('#dialog').hide();
	$('#searchPwdBtn').click(function() {
		console.log('버튼 클릭되었다!!!');
		console.log($('#modal_member_id').val()+' / ' + $('#modal_member_email').val());
		$.ajax(
			{
				url : "login/searchPwd.htm",
				method : "post",
				dataType : "json",
				data : {member_id:$('#modal_member_id').val(),member_email:$('#modal_member_email').val()},
				beforeSend:function(){
					$('#pwdEmail_layerpop').modal('toggle');
				},
				success:function(data){
					if(data.pwdresult == 'success'){
						alert('임시 비밀번호를 발급하였습니다. 등록하신 메일을 확인해 주세요.');
						location.href="login.htm";
					}else if(data.pwdresult == 'fail' || data.pwdresult == 'error'){
						alert('임시 비밀번호 발급에 실패하였습니다. 다시 시도해 주세요.');
						location.href="login/searchPwd.htm";
					}else if(data.pwdresult == 'incorrect'){
						alert('아이디 또는 등록된 이메일 주소가 다릅니다.');
						$('#member_email').focus();
						$('#pwdEmail_layerpop').modal('toggle');
					}
				}
			}
		);
	});
});