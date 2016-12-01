<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath}/js/member/viewTimetable.js"></script>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">×</button>
	<h5>시간표 조회</h5>
</div>
<div class="modal-body">
	<table id="timetable" class="table table-condensed" style="table-layout: fixed;" cellpadding="5" align="center" width="200">
		<tr style='position: relative; top: expression(this.offsetParent.scrollTop);'>
			<th style="text-align: center">시간</th>
			<th style="text-align: center">월</th>
			<th style="text-align: center">화</th>
			<th style="text-align: center">수</th>
			<th style="text-align: center">목</th>
			<th style="text-align: center">금</th>
		</tr>
		<c:forEach var="i" begin="1" end="20">
			<tr style="font-size: small; text-align: center" height="20px">
				<td id="PERIOD_START_${i}" style="word-break: break-all; text-align: center"></td>
				<td id="PR_MON_${i}" class="table_ele" height="auto" style="word-break: break-all; font-size: xx-samll"></td>
				<td id="PR_TUE_${i}" class="table_ele" height="auto" style="word-break: break-all; font-size: xx-samll"></td>
				<td id="PR_WEN_${i}" class="table_ele" height="auto" style="word-break: break-all; font-size: xx-samll"></td>
				<td id="PR_THU_${i}" class="table_ele" height="auto" style="word-break: break-all; font-size: xx-samll"></td>
				<td id="PR_FRI_${i}" class="table_ele" height="auto" style="word-break: break-all; font-size: xx-samll"></td>
			</tr>
		</c:forEach>
	</table>
</div>


