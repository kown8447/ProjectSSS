/*
 * @JavaScript : callendar
 * @Date : 2016.11.26
 * @Author :김영빈
 * @Desc
 * 캘린더를 db 에 불러온 데이터를 바탕으로 작성함
 * 캘린더 클릭시 이벤트를 활용 
 */
$(function() {

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

	$("#startdate").datepicker({

		showButtonPanel : true,
		changeMonth : true,
		changeYear : true,
		showAnim : "fold"
	});
	$("#enddate").datepicker({
		showButtonPanel : true,
		changeMonth : true,
		changeYear : true,
		showAnim : "fold"
	});
	$("#startdate1").datepicker({

		showButtonPanel : true,
		changeMonth : true,
		changeYear : true,
		showAnim : "fold"

	});
	$("#enddate1").datepicker({

		showButtonPanel : true,
		changeMonth : true,
		changeYear : true,
		showAnim : "fold"
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
							console.log(data);
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
									title : $('#title').val(),
									content : $('#content').val(),
									start : $('#startdate').val(),
									end : a.yyyymmdd()
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
		console.log('딜리트 클릭 !!!');
		console.log("code : " + delete_code);
		$.ajax({
			url : "scdelete.htm",
			method : "post",

			data : {
				id : delete_code
			},
			dataType : "json",
			success : function(data) {
				console.log(data);
				if (data.result > 0) {
					alert('삭제 성공 ');
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
			alert("제목을  꼭 입력하세요!");
            $("#title1").focus();
		}else if ($("#content1").val() == "") {
			alert("내용을 꼭 입력하세요!");
            $("#content1").focus();
		}else if ($("#startdate1").val() == "") {
			alert("시작일을 꼭 입력하세요!");
            $("#startdate1").focus();
		}else if ($("#enddate1").val() == "") {
			alert("종료일을 꼭 입력하세요!");
            $("#enddate1").focus();
		}else if($("#startdate1").val() > $("#enddate1").val()){
			alert("시작일보다 종료일이 이릅니다 ");
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
					console.log(data);
					if (data.result > 0) {
						alert('업데이트 성공 ');
						$('#updatemodal').modal('hide');
						$('#calendar').fullCalendar('removeEvents',
								delete_code);
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
							title : $('#title1').val(),
							content : $('#content1').val(),
							start : $('#startdate1').val(),
							end : a.yyyymmdd()
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
						left : 'prev,next today',
						center : 'title',
						right : 'month'
					},
					editable : false,
					droppable : false, // this allows things to be dropped onto the calendar

					events : function(start, end, timezone, callback) {
						$.ajax({
							url : "scview.htm",

							success : function(data) {
								console.log(data);
								var b = [];
								var request = [];

								$.each(data.list, function(index, value) {

									console.log(value.calendar_start);
									console.log(value.calendar_end);
									var end = value.calendar_end;
									var year = end.substring(0, 4);
									var month = end.substring(5, 7);
									var day = end.substring(8, 10);

									console.log(year + "/" + "/" + month + "/"
											+ day);

									var edate = new Date(year, month, day);

									var a = new Date(edate.getFullYear(),
											(edate.getMonth() - 1), (edate
													.getDate() + 1));

									console.log("a ;" + a);
									console.log("a adsfsdf : " + a.yyyymmdd());
									console.log(value.calendar_code);

									b[index] = value.calendar_code;

									console.log("edate : " + edate);
									request.push({
										id : value.calendar_code,
										title : value.calendar_title,
										content : value.calendar_content,
										start : value.calendar_start,
										end : a.yyyymmdd()
									});
								});
								max = 0;
								for (var i = 0; i < b.length; i++) {
									console.log(b[i]);
									if (parseInt(b[i]) > max) {
										max = b[i];

									}

								}
								console.log("max : " + max);
								callback(request);

							}

						});

					},

					eventClick : function(event, calEvent, start, end) {

						$('#modalTitle').html(event.title);

						console.log(event.start + "/" + event.end);
						console.log(typeof (event.start) + "/");
						var start = new Date(event.start);
						var end = new Date(event.end);
						var a = new Date(end.getFullYear(), (end.getMonth()),
								(end.getDate() - 1));
						$('#modalBody').html(
								event.content + "<br><br>" + "WHEN : "
										+ start.yyyymmdd() + " ~ "
										+ a.yyyymmdd() + "<br>");
						$('#eventUrl').attr('href', event.url);
						$('#fullCalModal').modal();
						delete_code = event.id;
						console.log("EDIT delete_code : " + delete_code);
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

					}

				});

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
		return false
	}
	if (confirm("업로드 하시겠습니까?")) {
		var options = {
			success : function(data) {

				if (data.result) {
					console.log("성공");
					alert("성공");
					location.href = "schedule.htm";
				} else {
					console.log("실패");
					alert("실패");
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