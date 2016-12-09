<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
   @Project : InitSpring
   @File name : scsystemlist.jsp
   @Author : 성홍모
   @Data : 2016.11.09
   @Desc : 장학제도 목록 pageView
-->        
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<div class="row  col-sm-offset-2">
	<h4>▶&nbsp;장학제도 목록 </h4><br><br>
<div class="col-sm-10">
<table class="table table-bordered" style="text-align: center" >
      <thead>
         <tr class="active">
            <th style="text-align: center">장학코드</th>
            <th style="text-align: center">장학명</th>
            <th style="text-align: center">선발기준</th>
            <th style="text-align: center">수혜인원</th>
            <th style="text-align: center">장학금액</th>
            <th style="text-align: center">비고</th>
            <th style="text-align: center">시행</th>
         </tr>
      </thead>
      <c:forEach items="${scsystemlist}" var="sc" varStatus="index">
         <tbody>
            <tr>
               <td>${sc.sys_code}</td>
               <td>
                  <a href="detailScSystem.htm?sys_code=${sc.sys_code}">
                     ${sc.scholaship_name}
                  </a>
               
               </td>
               <td>${sc.scholaship_standard}</td>
               <td>${sc.scholaship_member}</td>
               <td>${sc.scholaship_amount}</td>
               <td>${sc.scholaship_note}</td>
               <td>
               <c:choose>
                  <c:when test="${sc.scholaship_use ==0 }">
                  시행중
                  </c:when>
                  <c:when test="${sc.scholaship_use ==1 }">
                  미시행중
                  </c:when>
                  <c:otherwise>에러</c:otherwise>   
               </c:choose>
               </td>
            </tr>
         </tbody>
      </c:forEach>
   </table>
   <a href="registerscsystem.htm"style="float:right;" class="btn btn-danger">되돌아가기</a>
</div>
</div>