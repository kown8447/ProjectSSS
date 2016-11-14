$(function(){
	$('#searchIdBtn').click(function() {
		console.log('버튼 클릭되었다!!!');
		var name = $('#name').val();
		$.ajax(
				{
					url:"searchIDajax.htm",
					data:{
						name:$('#name').val(),
						email:$('#email').val()
					},
					dataType:"json",
					success:function(data){
						console.log(data);
						if(data.userid != "" && data.userid != null){
							$('#result').empty();
							var result = "<h3>"+name+" 님의 아이디는 "+data.userid+" 입니다.</h3>";
							$('#result').append(result);
						}else if(data.userid == null || data.userid == ""){
							$('#result').empty();
							var result = "<h3>찾으시는 아이디가 없습니다.</h3>";
							$('#result').append(result);
						}
					}
				}
		);
	});
});