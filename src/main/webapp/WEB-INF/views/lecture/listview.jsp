<%--
@Project : InitSpring
@File name : listview.jsp
@Author : 조장현
@Data : 2016.11.23
@Desc : 과목 리스트  
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script src="${pageContext.request.contextPath}/js/lecture/listview.js"></script>
<title>Insert title here</title>
</head>
<body>
<h4>▶&nbsp;과목 목록</h4><br><br>
   <div class="container" style="width:70%">
   <table id="list" class="table table-bordered" style="text-align: center">
      <tr>
         <td style="width:20%">구분</td>
         <td style="width:10%">학년</td>
         <td>과목명</td>
         <td style="width:20%">상태</td>
      </tr>
      <c:forEach items="${subjectlist}" var="subject">
      	<tr>
      		<td>
      			<c:choose>
     				<c:when test = "${subject.subject_type == '0'}"> 전공 </c:when>
      				<c:when test = "${subject.subject_type == '1'}"> 교양 </c:when>
      			</c:choose>
      			<c:choose>
      				<c:when test = "${subject.required_choice == '0' }">필수</c:when>
   					<c:when test = "${subject.required_choice == '1' }">선택</c:when>
      			</c:choose>
      		</td>
			<td>${subject.grade_limit}</td>
			<td><a href="lectureDetail.htm?subject_code=${subject.subject_code}&success_check=${subject.success_check }">
			${subject.subject_name}</td></a>
      		<td>
      			<c:choose>
					<c:when test = "${subject.success_check == 0}"> 대기 </c:when>
					<c:when test = "${subject.success_check == 1}"> 승인 </c:when>
					<c:when test = "${subject.success_check == 2}"> 거절 </c:when>
					<c:when test = "${subject.success_check == 3}"> 신청 </c:when>
				</c:choose>
      		</td>
      	</tr>
   	</c:forEach>
   </table>
   <div align="right">
   <button class="btn btn-danger btn-sm" id="back" align="center">돌아가기</button>&nbsp;&nbsp;&nbsp;&nbsp;
   <a href="lectureRegister.htm"><button class="btn btn-success btn-sm">등록하기</button></a>
   </div>
	</div>

</body>
</html>