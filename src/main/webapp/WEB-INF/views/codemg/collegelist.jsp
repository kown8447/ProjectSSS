<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
   @Project : InitSpring
   @File name : collegelist.jsp
   @Author : 성홍모
   @Data : 2016.11.09
   @Desc : 단과대학 상세보기 view
-->        
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<div class="row  col-sm-offset-2">
	<h4>▶&nbsp;단과대학 리스트 </h4> <br><br>
	<div class="col-sm-10">
   <form action="updateColleage.htm">
      <table class="table table-bordered" style="text-align: center" >
         <thead>
            <tr class="active">
               <th style="text-align: center">단대코드</th>
               <th style="text-align: center">이름</th>
               <th style="text-align: center">사무실 이름</th>
               <th style="text-align: center">사무실 코드</th>
            </tr>
         </thead>
         <c:forEach items="${college}" var="cl">
            <tbody>
               <tr>
                  <td>${cl.college_code}</td>
                  <td>
                     <a href="selectCollege.htm?college_code=${cl.college_code}">
                        ${cl.college_name}
                     </a>
                  </td>
                  <td>${cl.office_name}</td>
                  <td>${cl.office_code}</td>
               </tr>
            </tbody>
         </c:forEach>
      </table>
      </form>
      <a href="registercollege.htm"  style="float:right;" class="btn btn-danger">되돌아가기</a>      
   </div>
</div>
   