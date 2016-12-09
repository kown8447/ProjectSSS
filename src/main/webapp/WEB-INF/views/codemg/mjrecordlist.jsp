<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
   @Project : InitSpring
   @File name : mjreordlist.jsp
   @Author : 성홍모
   @Data : 2016.11.09
   @Desc : 부전공/전공 목록 보기 view
-->         
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>  
<div class = "row">
<div class = "col-sm-3"></div>
<div class="col-sm-6"  >
<h4>▶&nbsp;부전공/ 전공 리스트 </h4><br><br>

<table class="table table-bordered" style="text-align: center" >
      <thead>
         <tr class="active">
            <th style="text-align: center">코드</th>
            <th style="text-align: center">학과코드</th>
            <th style="text-align: center">전공구분</th>
         </tr>
      </thead>
      <c:forEach items="${mjrecord}" var="mj" varStatus="index">
         <tbody>
            <tr>
               <td>${mj.student_code}</td>
               <td>${mj.department_code}</td>
               <td>
               <c:choose>
                     <c:when test="${mj.mj_type==0}">
                        전공
                     </c:when>
                     <c:when test="${mj.mj_type==1}">
                        부전공
                     </c:when>
                     <c:otherwise>
                        코드 에러
                     </c:otherwise>
                  </c:choose>
               </td>
            </tr>
         </tbody>
      </c:forEach>
   </table>
   <a href="registermjrecord.htm" style="float:right;" class="btn btn-danger">되돌아가기</a>
   </div>
</div>