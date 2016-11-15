$(function() {
	$('#step1btn').click(function() {
		console.log('버튼 클릭되었다!!!');
		$.ajax({
				url : "join1.htm",
				method : "post",
				data : {
					code:$('#code').val(),
					code_type:$('#code_type').val(),
					code_name:$('#code_name').val(),
					code_birth:$('#code_birth').val()
				},
				dataType:"json",
				success:function(data){
					console.log(data);
					if(data.result == 'success'){
						location.href='join2.htm';
					}else if(data.result == 'fail'){
						alert('정보가 일치하지 않습니다.');
						location.href='join1.htm';
					}
				}
		});
	});
	
	var sessionid;
	
	$('#auth_email').click(function() {
		console.log('이메일 인증 버튼 클릭');
		$.ajax(
			{
				url:"authEmail.htm",
				data:{email:$('#email').val()},
				dataType:"json",
				success:function(data){
					if(data.mailresult == 'success'){
						sessionid = data.sessionID;
						alert('메일이 발송되었습니다. 인증번호를 확인해 주세요.');
					}
				}
			}	
		);
		
	});
	
	$('#step2btn').click(function() {
		console.log('전송버튼 클릭');
		if($('#auth_key').val() == sessionid){
			location.href="join3.htm";
		}else{
			alert('인증실패. 이메일 주소 또는 인증번호를 다시 확인 부탁드립니다.');
			$('#email').focus();
		}
	});
	
	var checkid = false;
	$('#checkID').click(function() {
		console.log('아이디 중복 체크');
		$.ajax(
				{
					url:"checkID.htm",
					data:{userid:$('#userid').val()},
					dataType:"json",
					success:function(data){
						if(data.checkresult == 'fail'){
							checkid = false;
							alert('중복된 아이디 입니다.');
						}else{
							checkid = true;
							alert('사용 가능한 아이디 입니다.');
						}
					}
				}
		);
	});
	
	$('#complete').click(function() {
		if(checkid == false){
			alert('아이디 중복 체크를 해주세요');
			return false;
		}
	});
});