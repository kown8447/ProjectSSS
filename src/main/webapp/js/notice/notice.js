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