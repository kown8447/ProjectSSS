/*
 * @JavaScript : qnanotice.js
 * @Date : 2016.11.16
 * @Author : 우명제
 * @Desc
 * QnA게시판 유효성 처리
 * 일치하지 않을 경우 false return 하여 submit 이 작동하지 않도록 함.
 * 검색, 글쓰기, 수정, 답글, 댓글 작성 시 유효성 검증 정리.
 */
$(function() {
	$('#searchBtn').click(function() {
		if ($('#keyword').val() == "") {
			alert("검색어를 입력하세요.");
			return false;
		}
	})

	$('#writeBtn').click(function() {
		if ($('#qna_title').val() == "") {
			alert("제목을 입력하세요.");
			return false;
		}
		if ($('#qna_content').val() == "") {
			alert("내용을 입력하세요.");
			return false;
		}
	});

	$('#editBtn').click(function() {
		if ($('#qna_title').val() == "") {
			alert("제목을 입력하세요.");
			return false;
		}
		if ($('#qna_content').val() == "") {
			alert("내용을 입력하세요.");
			return false;
		}
	});

	$('#replyBtn').click(function() {
		if ($('#qna_title').val() == "") {
			alert("제목을 입력하세요.");
			return false;
		}
		if ($('#qna_content').val() == "") {
			alert("내용을 입력하세요.");
			return false;
		}
	});

	$('#qnaUpdate').click(function() {
		if ($('#readerId').val() != $('#writerId').val()) {
			alert('본인이 작성한 글이 아닙니다');
			return false;
		}
		location.href = $(this).attr('href');
	});
	$('#qnaDelete').click(function() {
		if ($('#readerId').val() != $('#writerId').val()) {
			alert('본인이 작성한 글이 아닙니다');
			return false;
		}
		location.href = $(this).attr('href');
	});

	// 댓글
	$('#commentBtn').click(
			function() {

				if ($('#insertReply_content').val() == "") {
					alert("댓글을 입력하세요.");
					return false;
				} else {
					$.ajax({
						url : "qnaCmt.htm?qna_index=" + $('#qna_index').val()
								+ "&reply_content="
								+ $('#insertReply_content').val(),
						method : "post",
						dataType : "json",
						success : function(data) {
							if (data.result == 'success') {
								alert("성공");
								$('#replyList').empty();
								replyReset(data.list);
								cmtdeleteSetting();
								cmtUpdateSetting();
							} else {
								alert("실패");
							}
						}
					});
				}
			});

	function replyReset(list) {
		$(list)
				.each(
						function(index, obj) {
							var replyTag = '<div id="replyView_'
									+ (index + 1)
									+ '"><input type="hidden" id="cmt_index_'
									+ (index + 1)
									+ '" value="'
									+ obj.reply_index
									+ '"><div id="cmt_writer_'
									+ (index + 1)
									+ '">'
									+ obj.member_id
									+ '</div><br><div class="cmtContent" id="cmt_content_'
									+ (index + 1)
									+ '">'
									+ obj.reply_content
									+ '</div><button type="button" value="cmtUpdate_'
									+ (index + 1)
									+ '" class="cmtEdit" id="cmtUpdate_'
									+ (index + 1)
									+ '" name="cmtEdit">수정</button><button type="button" value="cmtDelete_'
									+ (index + 1)
									+ '" class="cmtDel" id="cmtDelete_'
									+ (index + 1)
									+ '" name="cmtDel">삭제</button><input type="hidden" id="cmt_id_'
									+ (index + 1) + '"value="' + obj.member_id
									+ '"></div>';

							$('#replyList').append(replyTag);
						});

		$('#insertReply_content').val("");

	}

	function cmtdeleteSetting() {
		$('.cmtDel').click(
				function() {
					var clickedindex = $(this).val().split('_')[1];

					var deleteTarget = $("#cmt_index_" + clickedindex).val();
					// alert($('#cmt_id_' + clickedindex).val());

					if ($('#readerId').val() != $('#cmt_id_' + clickedindex)
							.val()) {
						alert('본인이 작성한 글이 아닙니다')
						return;
					}

					if (confirm("정말로 삭제하시겠습니까?")) {
						$.ajax({
							url : "qnaCmtDel.htm?reply_index=" + deleteTarget
									+ '&qna_index=' + $('#qna_index').val(),
							method : "post",
							dataType : "json",
							success : function(data) {
								if (data.result == 'success') {
									alert("성공");
									$('#replyList').empty();
									replyReset(data.list);
									cmtdeleteSetting();
									cmtUpdateSetting();
								} else {
									alert("실패");
								}
							}

						});
					}

				});
	}
	
	
    var cmtIndex=0;
    
    function cmtUpdateSetting() {
    	$('.cmtEdit').click(function() {
    		
    		var clickedindex = $(this).val().split('_')[1];
    		
    		if ($('#readerId').val() != $('#cmt_id_' + clickedindex)
    				.val()) {
    			alert('본인이 작성한 글이 아닙니다')
    			return;
    		}
    		
    		cmtIndex = $(this).val().split('_')[1];
            $('#updateCmtContent').remove();
            $('#updateCmtUpdate').remove();
            $('#updateCmtCancel').remove();
    		$('.cmtContent').attr("style", "display : inline;");
    		$('.cmtEdit').attr("style", "display : inline;");
    		$('.cmtDel').attr("style", "display : inline;");

    		$('#cmt_content_' + cmtIndex).attr("style", "display : none;");
    		$('#cmtUpdate_' + cmtIndex).attr("style", "display : none;");
    		$('#cmtDelete_' + cmtIndex).attr("style", "display : none;");
    		var updateTool=
    			'<input type="text" id="updateCmtContent" value="'+$('#cmt_content_'+cmtIndex).text() +'"><input type="button" id="updateCmtUpdate" value="확인"><input type="button" id="updateCmtCancel" value="취소">'
    			
    		$('#replyView_'+cmtIndex).append(updateTool);
    		$('#updateCmtUpdate').click(function() {
    			$.ajax({
    				url : "qnaCmtUpdate.htm?reply_index=" + $('#cmt_index_'+cmtIndex).val()
    						+ '&reply_content=' + $('#updateCmtContent').val()+ '&qna_index=' + $('#qna_index').val(),
    				method : "post",
    				dataType : "json",
    				success : function(data) {
    					if (data.result == 'success') {
    						alert("성공");
    						$('#replyList').empty();
    						replyReset(data.list);
    						cmtdeleteSetting();
    						cmtUpdateSetting();
    					} else {
    						alert("실패");
    					}
    				}
    			});
    		});
    		
    		$('#updateCmtCancel').click(function() {
    			$('#updateCmtContent').remove();
    	        $('#updateCmtUpdate').remove();
    	        $('#updateCmtCancel').remove();
    	        
    	    	$('.cmtContent').attr("style", "display : inline;");
    			$('.cmtEdit').attr("style", "display : inline;");
    			$('.cmtDel').attr("style", "display : inline;");
    		});
    	});
	}
    cmtdeleteSetting();
	cmtUpdateSetting();

});