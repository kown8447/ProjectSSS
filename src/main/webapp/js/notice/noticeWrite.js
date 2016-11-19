/*
 * @JavaScript : noticeWrite.js
 * @Date : 2016.11.18
 * @Author : 송아름
 * @Desc
 * 게시판 공지사항 글쓰기 유효성 검증
 * 글제목, 글내용이 null이면  경고창 
*/

$(function(){
	$('#writeBtn').click(function(){
		console.log('공지사항 글쓰기 등록 버튼 클릭');
		if(('#notice_Title').val()==""){
			alert("제목을 입력하세요.");
		}
		if(('#notice_Content').val()==""){
			alert("내용을 입력하세요.");
		}
	});
	
})