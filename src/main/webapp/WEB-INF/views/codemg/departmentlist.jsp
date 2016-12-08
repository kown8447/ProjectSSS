<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>  
<h4>▶&nbsp;학부  리스트 </h4>
<br><br>
<div class="container" style="width:70%">
   <table class="table" style="text-align: center">
      <thead>
         <tr >
            <th style="text-align: center">이름</<th>
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
   <a href="registerdepartment.htm"><button style="float:right;" class="btn btn-danger">되돌아가기</button></a>
</div>
   