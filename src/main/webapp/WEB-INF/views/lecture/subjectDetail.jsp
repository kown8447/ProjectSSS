<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		
		var success_check = $("#sc").val();
		var subject_code = $("#subject_code").val();
		
		$("#delete").click(function(){
				alert("삭제못해요");
				return false;
			
		});
		
		$("#update").click(function(){
			alert(success_check);
			if(success_check != 0){
				alert("신청중이므로 수정할 수 없습니다");
				return false;
			}else{
				location.replace("lectureEdit.htm?subject_code="+subject_code);
			}
			
		});
		
		$("#request").click(function(){
			if(success_check != 0){
				alert("이미 신청된 과목입니다");
				return false;
			}else{
				location.replace("lecturePost.htm?subject_code="+subject_code+"&success_check="+success_check);
			}
		})
		
	})
	

</script>
</head>
<body>
	
	<div class="container">
	<table border=1px class="table table-striped">
		<tr>
		<td>구분</td><td name="subject_type">
		<c:choose>
		<c:when test = "${list.subject_type == '0'}"> 전공 </c:when>
		<c:when test = "${list.subject_type == '1'}"> 교양 </c:when>
		</c:choose>
		<input type="hidden" name="subject_type" value="${list.subject_type }">
		<c:choose>
		<c:when test = "${list.required_choice == '0'}">필수</c:when>
		<c:when test = "${list.required_choice == '1'}">선택</c:when>
		</c:choose>
		</td><td>강의대상</td><td>${list.grade_limit}</td></tr>
		<tr>
		<td>선수과목</td><td colspan="3">${list.before_name}</td></tr>
		<tr>
		<td>과목명</td><td colspan="3">${list.subject_name}</td></tr>
		<tr>
		<td>학점</td><td>${list.subject_credit}</td><td>정원</td><td>${list.subject_seats}</td></tr>
		
		<input type="hidden" id="subject_code" value="${list.subject_code }">
		<input type="hidden" id="sc" value=<%=request.getParameter("success_check") %>>
		
	</table>
	</div>
	<button id="back">돌아가기</button>  
	
	<button id="update" class="btn btn-primary">수정</button>
	<button id="delete" class="btn btn-danger">삭제</button>
	<button id="request" class="btn btn-success">신청</button>
	</body>
</html>