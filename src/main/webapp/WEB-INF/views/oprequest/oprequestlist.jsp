<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
   @Project : InitSpring
   @File name : oprequestlist.jsp
   @Author : 성홍모
   @Data : 2016.11.22
   @Desc : 개설신청과목 목록보기 view 
-->       
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<div class="container" style="width:65%">
<h4>▶&nbsp;개설과목 신청 현황 리스트 </h4><br><br>

	<table class="table table-bordered" style="text-align: center" >
		<thead>
			 <tr class="active">
				<th style="text-align: center">구분</th>
				<th style="text-align: center">학년</th>
				<th style="text-align: center">과목</th>
				<th style="text-align: center">교수</th>
				<th style="text-align: center">상태</th>
			</tr>
		</thead>
	
		<tbody>
		<c:if test="${empty oprequest}">
            <tr>
               <td colspan="5" style="text-align: center">등록된 정보가 없습니다.</td>
            </tr>
         </c:if>
         	<c:forEach items="${oprequest}" var="oplist">
			<tr>
				<td>${oplist.required_choice}</td>
				<td>${oplist.grade_limit}</td>
				<td><a href="requestDetail.htm?code=${oplist.subject_code}">${oplist.subject_name}</a></td>
				<td>${oplist.code_name}</td>
				<td>
				<c:choose>
					<c:when test="${oplist.success_check == 0}">대기</c:when>
					<c:when test="${oplist.success_check == 1}">승인</c:when>
					<c:when test="${oplist.success_check == 2}">거절</c:when>
					<c:otherwise>대기</c:otherwise>
				</c:choose>	
				</td>
			</tr>
			</c:forEach>
		</tbody>	
	</table>
	</div>

