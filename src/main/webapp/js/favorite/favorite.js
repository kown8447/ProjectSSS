$(function() {

	var $gallery = $("#gallery");
	var $box = $("#box");

	$("#gallery li").draggable({
		cancel : "a.ui-icon",
		revert : "invalid",
		containment : "document",
		helper : "clone",
		cursor : "move",
	});
	
	
	$("#box li").draggable({
		cancel : "a.ui-icon",
		revert : "invalid",
		containment : "document",
		helper : "clone",
		cursor : "move",
	});
	
	
	
	$box.droppable({
		accept : "#gallery li",
		drop : function(event, ui) {
			var eventLiID = ui.draggable[0].id;
			appendFavorite(eventLiID.trim());
		}
	});

	$gallery.droppable({
		accept : "#box li",
		drop : function(event, ui) {
			var eventLiID = ui.draggable[0].id;
			removeFavorite(eventLiID.trim());
		}
	});

	// Image deletion function
	function appendFavorite(item) {

		var str = item.split('_');
		$
				.ajax({
					url : "favoriteAppend.htm?link_code=" + str[1] + '_'
							+ str[2],
					method : "post",
					dataType : "json",
					success : function(data) {
						if (data.result) {
							$("#" + item).toggle("blind", {}, 300);
							var btn = '<li id="'
									+ item
									+ '_favorite" style="display: none;"><button class="btn btn-success">'
									+ $('#' + item + '_btn').text()
									+ '</button></li>'
							$('#gallery_favorite').append(btn);

							$("#" + item + '_favorite')
									.toggle("blind", {}, 300);
							$("#" + item + "_favorite").draggable({
								cancel : "a.ui-icon",
								revert : "invalid",
								containment : "document",
								helper : "clone",
								cursor : "move",
							});
							favoriteRenew();
						}
					}
				});

	}
	function removeFavorite(item) {
		var str = item.split('_');

		$.ajax({
			url : "favoriteDelete.htm?link_code=" + str[1] + '_' + str[2],
			method : "post",
			dataType : "json",
			success : function(data) {
				if (data.result) {
					$("#" + item).toggle("blind", {}, 300);
					setTimeout($('#' + item).remove(), 300);
					$('#' + str[0] + '_' + str[1] + '_' + str[2]).toggle(300);
					favoriteRenew();
				}
			}
		});

	}

});