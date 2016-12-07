/*
 * @JavaScript : postsubject.js
 * @Date : 2016.12.02
 * @Author : 조장현
 * @Desc
 * 과목 신청,삭제시 유효성 처리 및 비동기처리
 */
var insertcount = 0;
var array = new Array;

$(function() {
	
	var credit = $("#credit").val();

	$.ajax({
		url : "lecturePeriod.htm",
		dataType : "json",
		data : {
			professor_code : $("#professor_code").val()
		},
		success : function(data) {

			$.each(data.buildinglist, function(index, value) {

				console.log(value.building_name);
				$("#building").append(
						"<option value='" + value.building_code + "'>" + value
								+ "</option>");
			})
			$.each(data.periodlist, function(index, value) {
				$('#PERIOD_START_' + index).html(value.period_start);
			})

			$(data.myclass).each(function(index, elt) {
				$("#mytime").append("<ul>" + elt + "</ul>");
			})

		}
	})

	$("#building").change(
			function() {

				$.ajax({
					url : "requestsubject.htm",
					data : {
						building_code : $("#building").val(),
					},
					dataType : "json",
					success : function(data) {
						var content = "<option>없음</option>";

						$.each(data.classroom, function(index) {
							console.log(data.classroom[index].classroom_name);
							content += "<option value='"
									+ data.classroom[index].classroom_code
									+ "'>"
									+ data.classroom[index].classroom_name
									+ "</option>"
						});
						$('#classroom').html(content);

					}
				})

			});

	$("#classroom").change(
			function() {
				// alert($("#classroom").val());
				$.ajax({
					url : "requestclassroom.htm",
					data : {
						classroom_code : $("#classroom").val(),

					},
					dataType : "json",
					success : function(data) {
						$("td").empty();
						$("td").css("background", "white");
						console.log(data);

						$.each(data.time, function(index) {
							console.log(data.time[index].period_code);
							console.log(data.time[index].classroom_code);
							if (data.time[index].period_code != ""
									|| data.time[index].period_code != null) {
								$('#' + data.time[index].period_code).html(
										data.time[index].subject_name);
								$('#' + data.time[index].period_code).append(
										" - " + data.time[index].member_name
												+ " 교수");
								$('#' + data.time[index].period_code).css(
										"background", "skyblue");
							}
						})
					}

				})

			})

	$("#submit").click(
			function() {
			
				if (insertcount != credit) {
					alert("과목을 다 채워주세요");
					return false;
				}
				if ($("#subject_filesrc").val() == ""
						|| $("#subject_filesrc").val() == null) {
					alert("강의계획서를 등록해주세요");
					return false;
				}
			})

	$("#back").click(function() {
		history.go(-1)();
	})

});



function getvalue(i) {

	var credit = $("#credit").val();

	if (insertcount > credit) {
		alert("신청할 수 있는 시간을 초과했습니다.");
		return false;
	}

	if ($("#" + i).css("background-color") == "rgb(135, 206, 235)") {
		alert("다른 과목이 있습니다.");
		return false;
	}

	if ($("#" + i).html() != "") {
		if (!confirm("신청한 시간을 취소하시겠습니까?")) {
			return false;
		}
		for (var a = 0; a < array.length; a++) {
			if ($("#period" + i).val() == array[a]) {
				$("#period" + i).remove();
				$("#" + i).empty();
				$("#" + i).css("background", "white");
				array.splice(a, 1);
				insertcount -= 1;
			}
		}
	} else {

		$.ajax({
					url : "lectureRemoveTime.htm",
					data : {
						professor_code : $("#professor_code").val(),
						choice_code : i
					},
					dataType : "json",
					success : function(data) {
						if ($("#classroom").val() == "없음"
								|| $("#classroom").val() == ""
								|| $("#building").val() == "없음"
								|| $("#building").val() == "") {
							alert("강의실을 선택해주세요");
							return false;
						} else {
							console.log("성공");
							if (data.choice == "성공") {
								$("#period")
										.append(
												"<input type=text id=period"
														+ i
														+ " class=myclass name=period_code value="
														+ i + ">");
								array.push(i);

								console.log("배열:" + array);
								$("#" + i).text($('#subject_name').val());
								$("#" + i).css("background", "#47C83E");
								insertcount += 1;
							} else {
								alert("이미 다른 강의가 있는 시간입니다.");
								return false;
							}
						}
					}
				}

			)
	}
	
}
