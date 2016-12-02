$(function() {
	
	$('#viewTimetable').click(function() {
		$('#tableviewer').modal({
			remote : 'goTimetableForm.htm'
		});
	});
});


