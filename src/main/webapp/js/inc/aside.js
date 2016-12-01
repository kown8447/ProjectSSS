$(function() {
	$('#viewTimetable').click(function() {
		$('#tableviewer').modal({
			remote : 'goTimetableForm.htm'
		});
	});
	
	$('#menu_toggle2').click(function(e) {
		console.log('클릭 되냐 안되냐');
		e.preventDefault();
		$('#page-content-wrapper').show();
		$("#wrapper").toggleClass("toggled");
	});
});