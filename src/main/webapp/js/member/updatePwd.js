$(function(){
	$('#updatePwdBtn').click(function() {
		console.log('버튼 클릭되었다!!!');
		if($('#password').val() != $('#password2').val()){
			alert('비밀번호가 일치하지 않습니다. 다시 입력해 주세요.');
			$('#password').focus();
			$('#password').select();
			return false;
		}
	});
});