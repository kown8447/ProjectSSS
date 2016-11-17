/*
 * @JavaScript : updatePwd.js
 * @Date : 2016.11.16
 * @Author : 권기엽
 * @Desc
 * 비밀번호1 과 비밀번호2 의 일치 유효성 검증.
 * 일치하지 않을 경우 false return 하여 submit 이 작동하지 않도록 함.
*/
$(function(){
	$('#updatePwdBtn').click(function() {
		console.log('버튼 클릭되었다!!!');
		if($('#member_pwd').val() != $('#member_pwd2').val()){
			alert('비밀번호가 일치하지 않습니다. 다시 입력해 주세요.');
			$('#member_pwd').focus();
			$('#member_pwd').select();
			return false;
		}
	});
});