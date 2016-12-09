<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
   @Project : InitSpring
   @File name : departmentlist.jsp
   @Author : 성홍모
   @Data : 2016.11.09
   @Desc : 학과 목록 보기 view
-->         
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>  
<div class="row  col-sm-offset-2"> 
<h4>▶&nbsp;학부  리스트 </h4><br><br>
	<div class="col-sm-10">	
   <table class="table table-bordered" style="text-align: center" >
      <thead>
         <tr class="active">
            <th style="text-align: center">이름</th>
            <th style="text-align: center">단과 대학</th>
            <th style="text-align: center">건물 이름</th>
            <th style="text-align: center">사무실이름</th>
         </tr>
      </thead>
      <c:forEach items="${department}" var="dp" varStatus="index">
         <tbody>
            <tr>
               <td>
                  <a href="departmentDetail.htm?department_code=${dp.department_code}">
                     ${dp.department_name}(${dp.department_code})
                  </a>
               </td>
               <td>${dp.college_name}(${dp.college_code})</td>
               <td>${dp.building_name}</td>
               <td>${dp.office_name}(${dp.office_code})</td>
            </tr>
         </tbody>
      </c:forEach>
   </table>
   <a href="registerdepartment.htm" style="float:right;" class="btn btn-danger">되돌아가기</a>
	</div>
</div>   