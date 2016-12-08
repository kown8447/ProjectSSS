<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<c:set value="${detail}" var="de"/>
<c:set value="${room}" var="c"/>
<c:set value="${opcheck}" var="oc"/>

<div class = "row">
<div class = "col-sm-3"></div>
<div class="col-sm-6"  >
<h4>▶&nbsp;개설신청  상세정보 </h4><br><br>
<form id="rejectForm" action="UpdateReject.htm?code=${de.subject_code}">
<table class="table table-bordered" style="text-align: center; ">
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
		<td style="display: table-cell; vertical-align: middle;">시간</td>	
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
	<c:if test="${de.success_check==3}">
	<tr>
		<td style="display: table-cell; vertical-align: middle;">거절사유</td>
		<td colspan="3">
			<textarea placeholder="거절사유" class="form-control" name="rejectReason" id="rejectReason"></textarea>	
		</td>
	</tr>
	</c:if>
	<input type="hidden" value="${de.subject_code}" name="code">
</table>
</form>

<div align="center">
	
<c:if test="${de.success_check==3}">
		<!-- <input id="reject" type="button" value="거절"> -->
		<button class="btn btn-default" type="button" value="거절" id="rejectbtn">거절</button>
	</c:if>
<c:if test="${de.success_check==3}">
	<a href="updateSuccess.htm?code=${de.subject_code}"><button class="btn btn-default" >승인</button></a>
</c:if>

<a href="list.htm"><button class="btn btn-success">목록</button></a>
</div>

</div>
</div>