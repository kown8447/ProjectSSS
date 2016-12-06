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
	
	//댓글 리스트
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
									+ '"> <input type="hidden" id="cmt_id_'
									+ (index + 1)
									+ '" value="'
									+ obj.member_id
									+ '"><table><tr><td><div id="cmt_writer_'
									+ (index + 1)
									+ '" style="color: green">&nbsp;&nbsp;&nbsp;&nbsp;'
									+ obj.member_id
									+ ': &nbsp;&nbsp;</div></td><td><div class="cmtContent" id="cmt_content_'
									+ (index + 1)
									+ '">'
									+ obj.reply_content
									+ '</div></td><td><button type="button" value="cmtUpdate_'
									+ (index + 1)
									+ '" id="cmtUpdate_'
									+ (index + 1)
									+ '" class="cmtEdit btn-link" name="cmtEdit" >&nbsp;&nbsp;&nbsp;<font size="1">수정</font></button></td><td><button type="button" id="cmtDelete_'
									+ (index + 1)
									+ '" value="cmtDelete_'
									+ (index + 1)
									+ '" class="cmtDel btn-link" name="cmtDel" ><font size="1">삭제</font></button></td></tr></table></div>';
							
							$('#replyList').append(replyTag);
						});

		$('#insertReply_content').val("");

	}

	//댓글 삭제
	function cmtdeleteSetting() {
		$('.cmtDel').click(
				function() {
					var clickedindex = $(this).val().split('_')[1];

					var deleteTarget = $("#cmt_index_" + clickedindex).val();

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
    
    //댓글 수정 후 셋팅
    function cmtUpdateSetting() {
    	
    	//댓글 수정 과정
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
    			'<input type="text" id="updateCmtContent" value="'+$('#cmt_content_'+cmtIndex).text() +'"><input type="button" id="updateCmtUpdate" class="btn-link" style="font-size:5pt" value="확인"><input type="button" id="updateCmtCancel" class="btn-link" style="font-size:5pt" value="취소">'
    			
    		$('#replyView_'+cmtIndex).append(updateTool);
    		
    	//댓글 수정
    	$('#updateCmtUpdate').click(function() {  			
    		$.ajax({
    			url : "qnaCmtUpdate.htm?reply_index=" + $('#cmt_index_'+cmtIndex).val()
    					+ '&reply_content=' + $('#updateCmtContent').val()+ '&qna_index=' + $('#qna_index').val(),
    			method : "post",
    			dataType : "json",
    			success : function(data) {
    				if (data.result == 'success') {
    			
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
    		
    	//취소	
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


/*
 * @JavaScript : join
 * @Date : 2016.11.30
 * @Author : 송아름
 * @Desc
 * 파일 업로드 할 경우 파일 경로와 이름 표시 
*/
(function(global, $) {

   $(makeObjFile);

   function makeObjFile() {
      var inputFile = CustomFiletype();
      inputFile.init($('.filetype'));
   }

   function CustomFiletype() {
      if (this === window)
         return new CustomFiletype;
      this.$fileBox = null;
      this.$fileUpload = null;
   }

   CustomFiletype.prototype = {

      'init' : function(fileClass) {
         this.$fileBox = fileClass || $('.filetype');
         this.initEvent();
      },

      'initEvent' : function() {
         this.fileUpload();
      },

      'fileUpload' : function() {
         var _self = this;

         $.each(_self.$fileBox, function(idx, item) {

            var _$fileBox = _self.$fileBox.eq(idx), _$fileType = _$fileBox
                  .find('input[type=file]'), _$fileText = _$fileBox
                  .find('input[type=text]');
            _$fileText.attr('disabled', 'disabled');

            _$fileType.on('change', function() {
               var filePath = $(this).val();
               _$fileText.val(filePath);
               ;
            })
         })
      }
   }
})(window, jQuery);