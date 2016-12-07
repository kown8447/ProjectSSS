/*
 * @JavaScript : header.js
 * @Date : 2016.11.21
 * @Author : 최준호
 * @Desc
 * 즐겨찾기 설정 페이지 용
 * 버튼의 li의 drag, drop 기능 조정
*/
function favoriteRenew() {
	$.ajax({
		url : "/initspring/favorite/favoriteCall.htm",
		method : "post",
		dataType : "json",
		success : function(data) {
			$('#favoriteList').empty();
			$(data.favoLinks).each(
					function(index, obj) {
						var link = '<li><a href="/initspring/' + obj.link_addr
								+ '">' + obj.link_name + '</a></li>'
						$('#favoriteList').append(link);
					});
		}
	});
}
favoriteRenew();



/* 아름이가 추가한거 */
$(function() {
	$("#main-menu").on("mouseenter", ".dropdown", function() {
		$(this).find(".firstlevel").parent().addClass("active");
		$(this).find(".firstlevel").show();
		$(this).on("mouseleave", function() {
			$(this).find(".firstlevel").hide();
			$(this).find(".firstlevel").parent().removeClass("active");
		});
	});

	$("#main-menu").on(
			"mouseenter",
			".twolevel",
			function() {

				var ww = $(window).width();
				var $menuItem = $(this).find(".thirdlevel");
				var $firstLevel = $(".firstlevel");
				var mw = $menuItem.width() + $firstLevel.offset().left
						+ $firstLevel.width();
				var marginLeft = 0 - ($menuItem.width() + $firstLevel.width());
				if (ww < mw) {
					$menuItem.css("margin-left", marginLeft);
				}
				$menuItem.css("display", "block");
				$(this).on("mouseleave", function() {
					$(this).find(".thirdlevel").css("display", "none");
				});
			});

	
	$(".dropdown-menu > li > a.trigger").on("click",
			function(e) {
				var current = $(this).next();
				var grandparent = $(this).parent().parent();
				if ($(this).hasClass('left-caret') || $(this).hasClass('right-caret'))
					$(this).toggleClass('right-caret left-caret');
				grandparent.find('.left-caret').not(this).toggleClass('right-caret left-caret');
				grandparent.find(".sub-menu:visible").not(current).hide();
				current.toggle();
				e.stopPropagation();
			});
	$(".dropdown-menu > li > a:not(.trigger)").on("click", function() {
		var root = $(this).closest('.dropdown');
		root.find('.left-caret').toggleClass('right-caret left-caret');
		root.find('.sub-menu:visible').hide();
	});

});
