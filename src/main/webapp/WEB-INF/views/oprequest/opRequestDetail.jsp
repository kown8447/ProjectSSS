<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set value="${detail}" var="de"/>
<c:set value="${room}" var="c"/>
<c:set value="${opcheck}" var="oc"/>
<div>
<script type="text/javascript">
$(function() {
	$('#reject').click(function() {
		$('#rejectForm').submit();
	});
});

</script>
<form id="rejectForm" action="UpdateReject.htm?code=${de.subject_code}">
<table style="table-layout: auto;">
	<tr>
		<td>구분</td>
		<td>${oc.required_choice}</td>
		<td>강의대상</td>
		<td>${de.grade_limit}</td>
	</tr>
	<tr>
		<td>선수과목</td>
		<td colspan="3">${beforeSubject}</td>
	</tr>
	<tr>
		<td>과목명</td>
		<td colspan="3">${de.subject_name}</td>
	</tr>
	<tr>
		<td>시간</td>	
		<td colspan="3">
		<c:forEach items="${period}" var="pl">
		${pl.period_day} ${pl.period_start} ${pl.period_end}<Br>
		</c:forEach>
		</td>	
	</tr>
	<tr>
		<td>학점</td>
		<td>${de.subject_credit}</td>
		<td>정원</td>
		<td>${de.subject_seats }</td>
	</tr>
	<tr>
		<td>건물명</td>
		<td>${c.building_name}</td>	
		<td>강의실</td>
		<td>${c.classroom_name}</td>
	</tr>
	<tr>
		<td>강의 계획서</td>
		<td colspan="3">${de.subject_Filesrc}</td>
	</tr>
	<tr>
		<td>거절사유</td>
		<td colspan="3">
			<textarea placeholder="거절사유" name="rejectReason" id="rejectReason"></textarea>	
		</td>
	</tr>
</table>
<input type="text" value="${de.subject_code}" name="code"><br>
	<c:if test="${de.success_check==3}">
		<input id="reject" type="button" value="거절">
	</c:if>
</form>
<c:if test="${de.success_check==3}">
	<button><a href="updateSuccess.htm?code=${de.subject_code}">승인</a></button>		
</c:if>

<button><a href="list.htm">목록</a></button>
<input type="hidden" id="subject_state" value="${de.subject_state}"/>
</div>