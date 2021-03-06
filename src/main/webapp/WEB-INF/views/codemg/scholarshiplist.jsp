<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
   @Project : InitSpring
   @File name : scholarshiplist.jsp
   @Author : 성홍모
   @Data : 2016.11.09
   @Desc : 장학생 목록 pageView
-->        
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<div class="row  col-sm-offset-2">
<h4>▶&nbsp;장학생 목록 </h4><br><br>

<div class="col-sm-10">
<table class="table table-bordered" style="text-align: center" >
      <thead>
         <tr class="active">
            <th style="text-align: center">장학수혜코드</th>
            <th style="text-align: center">학번</th>
            <th style="text-align: center">이름</th>
            <th style="text-align: center">장학명</th>
            <th style="text-align: center">학기</th>
            <th style="text-align: center">지급일</th>
         </tr>
      </thead>
      <c:forEach items="${scholarshipList}" var="ss" varStatus="index">
      <tbody>
         <tr>
            <td>
                  ${ss.scholarship_code }
            </td>
            <td>
               ${ss.student_code }
            </td>
            <td>
               <a href="detailScholarship.htm?scholarship_code=${ss.scholarship_code}">
               ${ss.code_name }
               </a>
            </td>
            <td>
               ${ss.scholaship_name }
            </td>
            <td>
               ${ss.semester_name  }
            </td>
            <td>
               ${ss.scholarship_payday }
            </td>
      </tbody>
      </c:forEach>
   </table>
   <a href="registerscholarship.htm" style="float:right;" class="btn btn-danger">되돌아가기</a>
</div>
</div>
