<%--
@Project : InitSpring
@File name : postsubject.jsp
@Author : 조장현
@Data : 2016.11.23
@Desc : 과목 신청하기
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script src="${pageContext.request.contextPath}/js/lecture/postsubject.js"></script>
</head>

<body>

<div class="container">

<h4>▶&nbsp;과목 신청</h4><br><br>
	 <table class="table table-bordered">
		<tr class="active">
			<th style="text-align: center">구분</th>
			<th style="text-align: center">선수과목</th>
			<th style="text-align: center">과목명</th>
			<th style="text-align: center">학점</th>
			<th style="text-align: center">정원</th>
			<th style="text-align: center">수강대상</th>
		</tr>
		<tr>
		<th>	
		<c:choose>
		<c:when test = "${list.subject_type == '0'}"> 전공 </c:when>
		<c:when test = "${list.subject_type == '1'}"> 교양 </c:when>
		</c:choose>
		<c:choose>
		<c:when test = "${list.required_choice == '0' }">필수</c:when>
		<c:when test = "${list.required_choice == '1' }">선택</c:when>
		</c:choose>
		</th>
		<th>${list.before_name}</th>
		<th>${list.subject_name }</th>
		<th>${list.subject_credit}</th>
		<th>${list.subject_seats}</th>
		<th>${list.grade_limit }</th>
		</tr>
		</table>
		</div>
		<hr>
		<input type="hidden" id="credit" value=${list.subject_credit }>
		<input type="hidden" id="subject_name" value=${list.subject_name }>
	<form action="postRequestSubject.htm" enctype="multipart/form-data" method="post">
		
	<input type="hidden" id="professor_code" value=${list.professor_code }>
	<input type="hidden" value="${list.subject_code }" name="subject_code">
	<input type="hidden" value="${list.success_check }" name="success_check">


	<div class="row">
		<!-- 건물  -->
		<div class="col-sm-3">
		<select class="form-control" id="building" name="building_code">
			<option>없음</option>

		</select>
		<div id="period" name="period"></div>
	    <table class="table" style="border:1px" id="mytime" name="mytime"><tr><th>요일</th><th>시작시간</th><th>종료시간</th></table>
		
		<div class="form-group">
			<p style="color:blue">강의계획서</p> 
			<input type="file" id="subject_filesrc" name="subject_filename"><br>
		</div>	
		
		</div>
		<div class="col-sm-3">
		<select class="form-control" id="classroom" name="classroom_code">없음
		<option value="0">없음</option>

		</select>  
	</div>

	

	 <div class="col-sm-6">
	
			<table id="timetable" class="table table-condensed" style="table-layout: fixed;" cellpadding="5" align="center" width="200">
				<tr style='position:relative;top:expression(this.offsetParent.scrollTop);'>
					<th style="text-align: center">시간</th>
					<th style="text-align: center">월</th>
					<th style="text-align: center">화</th>
					<th style="text-align: center">수</th>
					<th style="text-align: center">목</th>
					<th style="text-align: center">금</th>
				</tr>
				<c:forEach var="i" begin="0" end="19" varStatus="idx">
					<tr bordercolor="black" style="font-size:small; text-align: center; border: 1px" height="20px">
						<th id="PERIOD_START_${idx.count}" style="word-break: break-all; text-align: center"></td>
						<td id="PR_MON_${idx.count}" height="auto" style="word-break: break-all;" class="cd_delete" onclick=getvalue("PR_MON_${idx.count}")></td>
						<td id="PR_TUE_${idx.count}" height="auto" style="word-break: break-all;" class="cd_delete" onclick=getvalue("PR_TUE_${idx.count}")></td>
						<td id="PR_WEN_${idx.count}" height="auto" style="word-break: break-all;" class="cd_delete" onclick=getvalue("PR_WEN_${idx.count}")></td>
						<td id="PR_THU_${idx.count}" height="auto" style="word-break: break-all;" class="cd_delete" onclick=getvalue("PR_THU_${idx.count}")></td>
						<td id="PR_FRI_${idx.count}" height="auto" style="word-break: break-all;" class="cd_delete" onclick=getvalue("PR_FRI_${idx.count}")></td>
					</tr>
				</c:forEach>
			
			</table>
		
				<button id="submit" class="btn btn-success">제출</button>
				<button id="back" class="btn btn-danger">돌아가기</button>
			
			</form>
		</div>		
	</div>


	
	<!-- <button id="back" class="btn btn-primary">돌아가기</button> -->
</body>
</html>