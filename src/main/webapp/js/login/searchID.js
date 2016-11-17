/*
 * @JavaScript : searchID.js
 * @Date : 2016.11.16
 * @Author : 권기엽
 * @Desc
 * 아이디 찾기 서비스의 유효성 검증.
 * 비동기 처리 후, view단 메시지 출력.
*/
$(function(){
	$('#searchIdBtn').click(function() {
		console.log('버튼 클릭되었다!!!');
		var name = $('#member_name').val();
		$.ajax(
				{
					url:"searchIDajax.htm",
					data:{
						member_name:$('#member_name').val(),
						member_email:$('#member_email').val()
					},
					dataType:"json",
					success:function(data){
						console.log(data);
						if(data.member_id != "" && data.member_id != null){
							$('#result').empty();
							var result = "<h3>"+name+" 님의 아이디는 "+data.member_id+" 입니다.</h3>";
							$('#result').append(result);
						}else if(data.member_id == null || data.member_id == ""){
							$('#result').empty();
							var result = "<h3>찾으시는 아이디가 없습니다.</h3>";
							$('#result').append(result);
						}
					}
				}
		);
	});
});