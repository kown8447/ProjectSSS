<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath}/js/requestCourse/correctRegisterCourse.js"></script>
<div class="row">
	<div class="col-md-5">
		<div class="navbar-header">
			<form class="form-inline" onSubmit='return false'>
				<select id="real_searchType" class="form-control" style="font-size: small">
					<option value="subject_name">과목명</option>
					<option value="subject_code">과목코드</option>
				</select> 
				<input type="text" class="form-control" id="correct_keyword" /> 
				<input type="button" class="btn btn-default" id="correct_searchBtn" value="검색">
				<div id="correct_result" style="overflow:auto;height:400px;"> </div>
			</form>
		</div>
	</div>


	<div class="col-md-7" style="overflow:auto;height:500px;">
		<form action="" method="post">
			<table id="correct_timetable" class="table table-hover" style="table-layout: fixed;" cellpadding="5" cellspacing="5" align="center">
				<tr style='position:relative;top:expression(this.offsetParent.scrollTop);text-align: center'>
					<th>시간</th>
					<th>월</th>
					<th>화</th>
					<th>수</th>
					<th>목</th>
					<th>금</th>
				</tr>
				<c:forEach var="i" begin="1" end="20">
					<tr style="font-size:small; text-align: center" height="20px">
						<td id="PERIOD_START_${i}_4" style="word-break: break-all; font-size:xx-samll;"></td>
						<td id="PR_MON_${i}_4" class="correct_table_ele" height="auto" style="word-break: break-all;font-size:xx-saml;l"></td>
						<td id="PR_TUE_${i}_4" class="correct_table_ele" height="auto" style="word-break: break-all;font-size:xx-samll;"></td>
						<td id="PR_WEN_${i}_4" class="correct_table_ele" height="auto" style="word-break: break-all;font-size:xx-samll;"></td>
						<td id="PR_THU_${i}_4" class="correct_table_ele" height="auto" style="word-break: break-all;font-size:xx-samll;"></td>
						<td id="PR_FRI_${i}_4" class="correct_table_ele" height="auto" style="word-break: break-all;font-size:xx-samll;"></td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</div>
</div>


<div class="modal fade" id="correct_layerpop">
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
						<td id="correct_subject_name"></td>
						<td id="correct_subject_code"></td>
						<td id="correct_professor_name"></td>
						<td id="correct_classroom_name"></td>
						<td id="correct_period"></td>
						<td id="correct_grade_limit"></td>
						<td id="correct_required_choice"></td>
						<td id="correct_subject_seats"></td>
						<td id="correct_subject_credit"></td>
					</tr>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>