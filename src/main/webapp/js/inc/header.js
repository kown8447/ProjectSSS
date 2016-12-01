$(function() {
   $("#main-menu").on("mouseenter", ".dropdown", function() {
      console.log("메롱");
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
   $(".dropdown-menu > li > a.trigger").on(
            "click",
            function(e) {
               var current = $(this).next();
               var grandparent = $(this).parent().parent();
               if ($(this).hasClass('left-caret')
                     || $(this).hasClass('right-caret'))
                  $(this).toggleClass('right-caret left-caret');
               grandparent.find('.left-caret').not(this).toggleClass(
                     'right-caret left-caret');
               grandparent.find(".sub-menu:visible").not(current).hide();
               current.toggle();
               e.stopPropagation();
            });
      $(".dropdown-menu > li > a:not(.trigger)").on("click", function() {
         var root = $(this).closest('.dropdown');
         root.find('.left-caret').toggleClass('right-caret left-caret');
         root.find('.sub-menu:visible').hide();
      });
      favoriteRenew();
});


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
   