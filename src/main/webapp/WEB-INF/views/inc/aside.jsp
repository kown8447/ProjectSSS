<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="${pageContext.request.contextPath}/js/inc/aside.js"></script>

<ul>
	<li><a href="#">메뉴1</a></li>
	<li><a href="#">메뉴2</a></li>
	<li><a href="#">메뉴3</a></li>
	<li><a href="#">메뉴4</a></li>
	<li><a href="#" id="viewTimetable" data-toggle="modal" style="color:#5F00FF;">시간표 조회</a></li>
</ul>

<div class="modal fade" id="tableviewer">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<!-- remote ajax call이 되는영역 -->
		</div>
	</div>
</div> 
