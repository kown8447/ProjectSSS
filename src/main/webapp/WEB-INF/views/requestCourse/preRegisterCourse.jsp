<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath}/js/requestCourse/preRegisterCourse.js"></script>

<h4>
	<span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>&nbsp;예비 수강신청</h4>
<br><br>
<div class="row">
	<div class="col-md-5">
			<form class="form-inline" onSubmit='return false'>
				<select id="searchType" class="form-control" style="font-size: small">
					<option value="subject_name">과목명</option>
					<option value="subject_code">과목코드</option>
				</select> 
				<input type="text" class="form-control" id="keyword" /> 
				<input type="button" class="btn btn-success" id="searchBtn" style="font-size:small" value="검색">
				<div id="result" style="overflow:auto;height:400px;"> </div>
			</form>
	</div>
	
	<div class="col-md-7" style="overflow:auto;">
		<form action="" method="post">
			<table id="timetable" class="table table-condensed" style="table-layout: fixed;font-size: 7pt;" cellpadding="5" align="center">
				<tr style="font-size: 10pt;">
					<th style="text-align: center">시간</th>
					<th style="text-align: center">월</th>
					<th style="text-align: center">화</th>
					<th style="text-align: center">수</th>
					<th style="text-align: center">목</th>
					<th style="text-align: center">금</th>
				</tr>
				<c:forEach var="i" begin="1" end="20">
					<tr style=" text-align: center" height="20px">
						<td id="PERIOD_START_${i}" style="word-break: break-all; text-align: center"></td>
						<td id="PR_MON_${i}" class="table_ele" height="auto" style="word-break: break-all;"></td>
						<td id="PR_TUE_${i}" class="table_ele" height="auto" style="word-break: break-all;"></td>
						<td id="PR_WEN_${i}" class="table_ele" height="auto" style="word-break: break-all;"></td>
						<td id="PR_THU_${i}" class="table_ele" height="auto" style="word-break: break-all;"></td>
						<td id="PR_FRI_${i}" class="table_ele" height="auto" style="word-break: break-all;"></td>
					</tr>
				</c:forEach>
			</table>
			<div class="row" style="text-align: center">
				시간표 공유 여부 : <input type="radio" class="" id="timetable_share" name="timetable_share" value="1" />
				공유함 <input type="radio" id="timetable_share" name="timetable_share" value="0" checked />공유안함 
				<input type="button" id="requestBtn" class="btn btn-primary" value="수강 신청" />
			</div>
		</form>
	</div>
</div>


<div class="modal fade" id="layerpop">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<!-- header -->
			<div class="modal-header">
				<!-- 닫기(x) 버튼 -->
				<button type="button" class="close" data-dismiss="modal">×</button>
				<!-- header title -->
				<h4 class="modal-title">강의 정보</h4>
			</div>
			<!-- body -->
			<div class="modal-body">
				<table class="table table-bordered">
					<tr class="info">
						<th style="text-align: center;">과목명</th>
						<th style="text-align: center;">과목코드</th>
						<th style="text-align: center;">교수명</th>
						<th style="text-align: center;">강의실</th>
						<th style="text-align: center;">강의 시간</th>
						<th style="text-align: center;">대상 학년</th>
						<th style="text-align: center;">필수/선택</th>
						<th style="text-align: center;">정원</th>
						<th style="text-align: center;">학점</th>
					</tr>
					<tr style="text-align: center;font-size: x-samll;">
						<td id="subject_name" style="text-align: center;font-size: x-samll;"></td>
						<td id="subject_code2" style="text-align: center;font-size: x-samll;"></td>
						<td id="professor_name" style="text-align: center;font-size: x-samll;"></td>
						<td id="classroom_name" style="text-align: center;font-size: x-samll;"></td>
						<td id="period" style="text-align: center;font-size: x-samll;"></td>
						<td id="grade_limit" style="text-align: center;font-size: x-samll;"></td>
						<td id="required_choice" style="text-align: center;font-size: x-samll;"></td>
						<td id="subject_seats" style="text-align: center;font-size: x-samll;"></td>
						<td id="subject_credit" style="text-align: center;font-size: x-samll;"></td>
					</tr>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>