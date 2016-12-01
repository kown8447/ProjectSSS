/*
 * @JavaScript : callendar
 * @Date : 2016.11.26
 * @Author :김영빈
 * @Desc
 * 캘린더를 db 에 불러온 데이터를 바탕으로 작성함
 * 캘린더 클릭시 이벤트를 활용 
 */
$(function() {
	$("#startdate,#enddate,#startdate1,#enddate1").datepicker({
		 autoclose: true
	   });
   var max;
   var code;
   var delete_code;
   var dcode;
   $(document).ajaxStop(function() {
      load();
    
   });

   $(document).ready(function() {
      load();
     
   });
   
  
   /*
    * @JavaScript : callendar
    * @Date : 2016.11.26
    * @Author :김영빈
    * @Desc
    * 일정 추가 이벤트
    */
   $('#insert').click(function() {
	   
      if ($("#title").val() == "") {
         alert("제목을  꼭 입력하세요!");
            $("#title").focus();
      }else if ($("#content").val() == "") {
         alert("내용을 꼭 입력하세요!");
            $("#content").focus();
      }else if ($("#startdate").val() == "") {
         alert("시작일을 꼭 입력하세요!");
            $("#startdate").focus();
      }else if ($("#enddate").val() == "") {
         alert("종료일을 꼭 입력하세요!");
            $("#enddate").focus();
      }else if($("#startdate").val() > $("#enddate").val()){
         alert("시작일보다 종료일이 이릅니다 ");
         $("#enddate").focus();
      }else{
         $.ajax({
                  url : "scinsert.htm",
                  method : "post",
                  data : {
                     title : $('#title').val(),
                     content : $('#content').val(),
                     start : $('#startdate').val(),
                     end : $('#enddate').val()
                  },
                  dataType : "json",
                  success : function(data) {
                     if (data.result > 0) {
                        alert('입력성공');
                        $('#basicModal').modal('hide');
                        var insertend = $('#enddate').val();
                        var month = insertend.substring(0, 2);
                        var day = insertend.substring(3, 5);
                        var year = insertend.substring(6, 10);
                        var edate = new Date(year, month, day);
                        var a = new Date(edate.getFullYear(),
                              (edate.getMonth() - 1), (edate
                                    .getDate() + 1));
                        var myEvent = {
                           id : parseInt(max) + 1,
                           color : "#B1DB4E",
                           textColor : "#000000",
                           title : $('#title').val(),
                           content : $('#content').val(),
                           start : $('#startdate').val(),
                           end : a.yyyymmdd(),
                           allDay: true
                        };
                        $('#calendar').fullCalendar('renderEvent',
                              myEvent);
                        max++;

                     } else {
                        alert('정보가 일치하지 않습니다.');
                     }
                  }
               });
      }
   });
   /*
    * @JavaScript : callendar
    * @Date : 2016.11.26
    * @Author :김영빈
    * @Desc
    * 캘린더를 db 에 불러온 데이터를 바탕으로 작성함
    * 일정삭제 이벤트
    */
   $('#delete').click(function() {
      $.ajax({
         url : "scdelete.htm",
         method : "post",
         data : {
            id : delete_code
         },
         dataType : "json",
         success : function(data) {
            if (data.result > 0) {
               alert('일정이 삭제되었습니다.');
               $('#fullCalModal').modal('hide');
               $('#calendar').fullCalendar('removeEvents', delete_code);
            } else {
               alert('정보가 일치하지 않습니다.');

            }
         }
      });

   });
   /*
    * @JavaScript : callendar
    * @Date : 2016.11.26
    * @Author :김영빈
    * @Desc
    * 일정 수정 이벤틍 
    */
   $('#up').click(function() {
	  
      if ($("#title1").val() == "") {
         alert("제목을 입력하세요.");
            $("#title1").focus();
      }else if ($("#content1").val() == "") {
         alert("내용을 입력하세요.");
            $("#content1").focus();
      }else if ($("#startdate1").val() == "") {
         alert("시작일을 입력하세요.");
            $("#startdate1").focus();
      }else if ($("#enddate1").val() == "") {
         alert("종료일을 입력하세요.");
            $("#enddate1").focus();
      }else if($("#startdate1").val() > $("#enddate1").val()){
         alert("날짜를 정확히 선택해 주세요.");
         $("#enddate1").focus();
      }else{
         $.ajax({
            url : "scupdate.htm",
            method : "post",
            data : {
               id : delete_code,
               title : $('#title1').val(),
               content : $('#content1').val(),
               start : $('#startdate1').val(),
               end : $('#enddate1').val()
            },
            dataType : "json",
            success : function(data) {
               if (data.result > 0) {
                  alert('일정이 수정되었습니다.');
                  $('#updatemodal').modal('hide');
                  $('#calendar').fullCalendar('removeEvents', delete_code);
                  var insertend = $('#enddate1').val()
                  var month = insertend.substring(0, 2);
                  var day = insertend.substring(3, 5);
                  var year = insertend.substring(6, 10);
                  var edate = new Date(year, month, day);

                  var a = new Date(edate.getFullYear(),
                        (edate.getMonth() - 1), (edate
                              .getDate() + 1));
                  var myEvent = {
                     id : delete_code,
                     color : "#B1DB4E",
                     textColor : "#000000",
                     title : $('#title1').val(),
                     content : $('#content1').val(),
                     start : $('#startdate1').val(),
                     end : a.yyyymmdd(), 
                     allDay: true
                  };
                  $('#calendar').fullCalendar('renderEvent',
                        myEvent);
               } else {
                  alert('정보가 일치하지 않습니다.');
               }
            }
         });
      }
   });
   /*
    * @JavaScript : callendar
    * @Date : 2016.11.26
    * @Author :김영빈
    * @Desc
    * 캘린더를 불러오는 함수 
    */
   function load() {
      $('#calendar').fullCalendar(
            {
               header : {
            	   left:   'title',
            	   center: '',
            	   right:  'today prev,next'
            	    
               }, 
               editable : false,
               droppable : false, // this allows things to be dropped onto the calendar
               
               events : function(start, end, timezone, callback) {
                  $.ajax({
                     url : "scview.htm",

                     success : function(data) {
                        var b = [];
                        var request = [];

                        $.each(data.list, function(index, value) {
                           var end = value.calendar_end;
                           var year = end.substring(0, 4);
                           var month = end.substring(5, 7);
                           var day = end.substring(8, 10);
                           var edate = new Date(year, month, day);
                           var a = new Date(edate.getFullYear(),
                                 (edate.getMonth() - 1), (edate
                                       .getDate() + 1));
                           b[index] = value.calendar_code;
                           request.push({
                              color : "#EAEAEA",
                              textColor : "#000000",
                              id : value.calendar_code,
                              title : value.calendar_title,
                              content : value.calendar_content,
                              start : value.calendar_start,
                              end : a.yyyymmdd(),
                              allDay: true
                           });
                        });
                        max = 0;
                        for (var i = 0; i < b.length; i++) {
                           if (parseInt(b[i]) > max) {
                              max = b[i];
                           }
                        }
                        callback(request);

                     }

                  });

               },

               eventClick : function(event, calEvent, start, end) {

                  $('#modalTitle').html(event.title);
                  delete_code = event.id;
                 
                  var start = new Date(event.start);
                  var end = new Date(event.end);
                  var a = new Date(end.getFullYear(), (end.getMonth()),
                        (end.getDate() - 1));
                  $('#modalBody').html(
                        event.content + "<br><br><br>" + "기간  : "
                              + start.yyyymmdd() + " ~ "
                              + a.yyyymmdd() + "<br>");
                  $('#eventUrl').attr('href', event.url);
                  $('#fullCalModal').modal();
                  
                  $('#update').click(
                        function() {
                           $('#fullCalModal').modal('hide');
                           $('#updatemodal #title1').val(event.title);
                           $('#updatemodal #content1').val(
                                 event.content);
                           $('#updatemodal').modal();
                           
                           var s = new Date(event.start);
                           var e = new Date(event.end);
                           
                           $('#updatemodal #startdate1').val(
                                 start.mmddyyyy());
                           $('#updatemodal #enddate1').val(
                                 a.mmddyyyy());
                        });

               },
               dayClick : function(event, start, end) {
                  $('#basicModal').modal();

               },eventAfterRender: function(event, element, view) {
                  $('.fc-event').css('height', '20px');

               }
               
            });
      
      $('.fc-sat').css('color','#0000FF');//이거 토요일 의TD
      $('.fc-sun').css('color','#FF0000');//이것은 일요일에 있을 TD

   }
   /*
    * @JavaScript : callendar
    * @Date : 2016.11.26
    * @Author :김영빈
    * @Desc
    * date 형식바꾸는 함수 생성
    */
   Date.prototype.mmddyyyy = function() {
      var yyyy = this.getFullYear().toString();
      var mm = (this.getMonth() + 1).toString();
      var dd = this.getDate().toString();
      return (mm[1] ? mm : '0' + mm[0]) + "/" + (dd[1] ? dd : '0' + dd[0])
            + "/" + yyyy;
   }

   Date.prototype.yyyymmdd = function() {
      var yyyy = this.getFullYear().toString();
      var mm = (this.getMonth() + 1).toString();
      var dd = this.getDate().toString();
      return yyyy + "-" + (mm[1] ? mm : '0' + mm[0]) + "-"
            + (dd[1] ? dd : '0' + dd[0]);
   }

});
/*
 * @JavaScript : callendar
 * @Date : 2016.11.26
 * @Author :김영빈
 * @Desc
 * 파일업로드 
 */
function checkFileType(filepath) {
   var fileForamt = filepath.split(".");
   if (fileForamt.indexOf("xlsx") > -1) {
      return true;
   } else {
      return false;
   }
}

function check() {
   var file = $("#excel").val();
   if (file == "" || file == null) {
      alert("파일을 선택해 주세요");
      return false;
   } else if (!checkFileType(file)) {
      alert("엑셀 파일만 업로드 해주세요");
      return false;
   }
   if (confirm("일정을 등록하시겠습니까?")) {
      var options = {
         success : function(data) {
            if (data.result) {
               location.href = "schedule.htm";
            } else {
               alert("파일을 확인해 주세요.");
            }
         },
         statusCode : {
            404 : function() {
               alert("파일 내용이 잘못되었습니다.");
               location.href = "schedule.htm";
            }
         },
         type : "POST"
      };
      $('#excelForm').ajaxSubmit(options);
   }
}

/*
 * 파일 업로드 할 경우 파일 경로와 이름 표시 
*/
(function(global, $) {

  $(makeObjFile);

  function makeObjFile() {
    var inputFile = CustomFiletype();
    inputFile.init($('.filetype'));
  }

  function CustomFiletype() {
    if (this === window) return new CustomFiletype;
    this.$fileBox = null;
    this.$fileUpload = null;
  }

  CustomFiletype.prototype = {

    'init': function(fileClass) {
      this.$fileBox = fileClass || $('.filetype');
      this.initEvent();
    },

    'initEvent': function() {
      this.fileUpload();
    },

    'fileUpload': function() {
      var _self = this;

      $.each(_self.$fileBox, function(idx, item) {

        var _$fileBox = _self.$fileBox.eq(idx),
          _$fileType = _$fileBox.find('input[type=file]'),
          _$fileText = _$fileBox.find('input[type=text]');
        _$fileText.attr('disabled', 'disabled');

        _$fileType.on('change', function() {
          var filePath = $(this).val();
          _$fileText.val(filePath);
        })

      })
    }

  }

})(window, jQuery);