$(function() {

	/*$.ajax({
		url : "listCall.htm",
		method : "post",
		dataType : "json",
		success : function(data) {
			console.log(data);
		}
	});*/

	var $gallery = $("#gallery"), $box = $("#box");

	$("li", $gallery).draggable({
		cancel : "a.ui-icon",
		revert : "invalid",
		containment : "document",
		helper : "clone",
		cursor : "move",
	});

	$box.droppable({
		accept : "#gallery > li",
		drop : function(event, ui) {
			deleteImage(ui.draggable);
		}
	});

	$gallery.droppable({
		accept : "#box li",
		drop : function(event, ui) {
			recycleImage(ui.draggable);
		}
	});

	// Image deletion function
	function deleteImage($item) {
		$item.fadeOut(function() {
			var $list = $("ul", $box).length ? $("ul", $box) : $(
					"<ul class='gallery ui-helper-reset'/>").appendTo($box);

			$item.appendTo($list).fadeIn(function() {
				$item.animate({}).find("button").animate({});
			});
		});
	}

	// Image recycle function
	var box_icon = "<a href='link/to/box/script/when/we/have/js/off'></a>";
	function recycleImage($item) {
		$item.fadeOut(function() {
			$item.find("a").remove().end().append(box_icon).find("button")
					.end().appendTo($gallery).fadeIn();
		});
	}

	// Image preview function, demonstrating the ui.dialog used as a modal
	// window
	function viewLargerImage($link) {
		var src = $link.attr("href"), title = $link.siblings("button");

		if ($modal.length) {
			$modal.dialog("open");
		} else {
			setTimeout(function() {
				img.dialog({
					title : title,
					width : 400,
					modal : true
				});
			}, 1);
		}
	}

});