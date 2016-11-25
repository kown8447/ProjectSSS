/*
 * @JavaScript : notice.js
 * @Date : 2016.11.25
 * @Author : 송아름
 * @Desc
 * 글 목록에서 검색어를 입력하지 않고 검색버튼을 눌렸을경우 경고창
 * 제목과 내용을 입력 하지 않을 경우 공지사항을 등록하지 못하도록 함
*/


$(function(){
	$('#searchBtn').click(function() {
		console.log('버튼 클릭되었다!!!');
		if($('#keyword').val()==""){
			alert("검색어를 입력하세요.");
			return false;
		}
	})
	
	$('#writeBtn').click(function(){
		if($('#notice_title').val()==""){
			alert("공지사항 제목을 입력하세요.");
			return false;
		}
		if($('#notice_content').val()==""){
			alert("내용을 입력하세요.");
			return false;
		}
	});
	
	$('#replyBtn').click(function(){
		if($('#notice_title').val()==""){
			alert("공지사항 제목을 입력하세요.");
			return false;
		}
		if($('#notice_content').val()==""){
			alert("내용을 입력하세요.");
			return false;
		}
	});
	
	$('#editBtn').click(function(){
		if($('#notice_title').val()==""){
			alert("공지사항 제목을 입력하세요.");
			return false;
		}
		if($('#notice_content').val()==""){
			alert("내용을 입력하세요.");
			return false;
		}
	});
});
