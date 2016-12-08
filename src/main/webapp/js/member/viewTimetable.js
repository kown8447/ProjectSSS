/*
 * @JavaScript : viewTimetable.js
 * @Date : 2016.11.30
 * @Author : 권기엽
 * @Desc
 * 시간표 조회시 페이지 로딩과 동시에 시간표 정보 JSP 에 전달
*/
$(function(){
	$.ajax(
			{
				url:"viewCurrentTimetable.htm",
				dataType:"json",
				async: true,
				success:function(data){
					$.each(data.periodList, function(i, elt) {
						$('#PERIOD_START_'+(i+1)).html(elt.period_start);
					});
					
					$.each(data.lists, function(i, elt) {
						var color="skyblue";
						var text=elt.subject_code+"<br>"+elt.subject_name+"<br>"+elt.professor_name+"<br>";

						$.each(elt.period, function(i, obj) {
							$('#'+obj.period_code).html(text);
							$('#'+obj.period_code).attr('style','background-color:'+color);
						});
					});
				}
			}
		);
});