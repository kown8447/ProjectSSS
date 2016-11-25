<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h3>예비 수강 신청</h3>
<div class="row">
	<div class="col-md-6">
		<div class="navbar-header">
			<form class="form-inline" onSubmit='return false'>
				<select id="searchType" class="form-control">
					<option value="subject_name">과목명</option>
					<option value="subject_code">과목코드</option>
				</select> <input type="text" class="form-control" id="keyword" /> <input
					type="button" class="btn btn-default" id="searchBtn" value="검색">
				<span id="result" style="margin: 20px 20px 20px 20px"> </span>
			</form>

		</div>
	</div>


	<div class="col-md-6">
		<form action="" method="post">
			<table id="timetable" class="table table-hover" style="table-layout: fixed;" cellpadding="5" cellspacing="5" align="center">
				<tr>
					<th>시간</th>
					<th>월</th>
					<th>화</th>
					<th>수</th>
					<th>목</th>
					<th>금</th>
				</tr>
				<c:forEach var="i" begin="1" end="20">
					<tr>
						<td id="PERIOD_START_${i}" style="word-break: break-all;"></td>
						<td id="PR_MON_${i}" class="table_ele" height="auto" style="word-break: break-all;"></td>
						<td id="PR_TUE_${i}" class="table_ele" height="auto" style="word-break: break-all;"></td>
						<td id="PR_WEN_${i}" class="table_ele" height="auto" style="word-break: break-all;"></td>
						<td id="PR_THU_${i}" class="table_ele" height="auto" style="word-break: break-all;"></td>
						<td id="PR_FRI_${i}" class="table_ele" height="auto" style="word-break: break-all;"></td>
					</tr>
				</c:forEach>
			</table>
			시간표 공유 여부 : <input type="radio" class="" id="timetable_share" name="timetable_share" value="1" />
			공유함 <input type="radio" id="timetable_share" name="timetable_share" value="0" checked />공유안함
			<input type="button" id="requestBtn" class="btn btn-primary" value="수강 신청" />
		</form>
	</div>
</div>


<div class="modal fade" id="layerpop">
	<div class="modal-dialog">
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
					<tr>
						<th>과목명</th>
						<th>과목코드</th>
						<th>교수명</th>
						<th>강의실</th>
						<th>강의 시간</th>
						<th>대상 학년</th>
						<th>필수/선택</th>
						<th>정원</th>
						<th>학점</th>
					</tr>
					<tr>
						<td id="subject_name"></td>
						<td id="subject_code2"></td>
						<td id="professor_name"></td>
						<td id="classroom_name"></td>
						<td id="period"></td>
						<td id="grade_limit"></td>
						<td id="required_choice"></td>
						<td id="subject_seats"></td>
						<td id="subject_credit"></td>
					</tr>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>