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

	<table border=1px class="table">
		<tr>
		<th>구분</th><th>선수과목</th><th>과목명</th><th>학점</th><th>정원</th><th>수강대상</th>
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
		<br>
		</div>
		<input type="hidden" id="credit" value=${list.subject_credit }>
		<input type="hidden" id="subject_name" value=${list.subject_name }>
	<form action="postRequestSubject.htm" enctype="multipart/form-data" method="post">
	
	<input type="hidden" value="${list.subject_code }" name="subject_code">
	<input type="hidden" value="${list.success_check }" name="success_check">
	<div class="row">
		<!-- 건물  -->
		<div class="col-sm-3">
		<select class="form-control" id="building" name="building_code">
			<option>없음</option>
			<option value="B_001" name="B_001">본부동</option>
			<option value="B_002" name="B_002">가츠동</option>
			<option value="B_003" name="B_003">어우동</option>
		</select>
		<label>시간</label> <div id="period" name="period"></div>
	
		<label>강의계획서</label> <input type="file" id="subject_filesrc" name="subject_filename"><br>
			
		</div>
		<div class="col-sm-3">
		<select class="form-control" id="classroom" name="classroom_code">없음
		<option value="0">없음</option>
		</select>  
	
		<%-- 		
		<select id="semester_code" name="semester_code" class="form-control">
		<c:forEach items="${semester }" var="i">
			<option value="${i.semester_code }">${i.semester_name }</option>
		</c:forEach>
		</select>
		 --%>
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
				<c:forEach var="i" begin="1" end="20">
					<tr bordercolor="black" style="font-size:small; text-align: center; border: 1px" height="20px">
						<th id="PERIOD_START_${i}" style="word-break: break-all; text-align: center"></td>
						<td id="PR_MON_${i}" height="auto" style="word-break: break-all;"  onclick=getvalue("PR_MON_${i}")></td>
						<td id="PR_TUE_${i}" height="auto" style="word-break: break-all;"  onclick=getvalue("PR_TUE_${i}")></td>
						<td id="PR_WEN_${i}" height="auto" style="word-break: break-all;"  onclick=getvalue("PR_WEN_${i}")></td>
						<td id="PR_THU_${i}" height="auto" style="word-break: break-all;"  onclick=getvalue("PR_THU_${i}")></td>
						<td id="PR_FRI_${i}" height="auto" style="word-break: break-all;"  onclick=getvalue("PR_FRI_${i}")></td>
					</tr>
				</c:forEach>
			
			</table>
		
				<input type="submit" id="submit" value="제출">
			
			</form>
		</div>
	</div>


	
	<button id="back" class="btn btn-primary">돌아가기</button>
</body>
</html>