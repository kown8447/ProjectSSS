/*
 * @JavaScript : register.js
 * @Date : 2016.11.21
 * @Author : 최준호
 * @Desc
 * 학생의 등록,장학기록 열람에 관련한 로직
 * 화면에 탭 형식 적용
 * 
*/
(function($) {

  'use strict';

  $(document).on('show.bs.tab', '.nav-tabs-responsive [data-toggle="tab"]', function(e) {
    var $target = $(e.target);
    var $tabs = $target.closest('.nav-tabs-responsive');
    var $current = $target.closest('li');
    var $parent = $current.closest('li.dropdown');
      $current = $parent.length > 0 ? $parent : $current;
    var $next = $current.next();
    var $prev = $current.prev();
   
    $tabs.find('>li').removeClass('next prev');
    $prev.addClass('prev');
    $next.addClass('next');
  });

})(jQuery);