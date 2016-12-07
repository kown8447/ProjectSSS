<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<div class = "row">
<div class = "col-sm-3"></div>
<div class="col-sm-6"  >
<h4>▶&nbsp;장학생 리스트 </h4>
<br><br>
<table class="table" style="text-align: center">
      <thead>
         <tr>
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
               <a href="detailScholarship.htm?scholarship_code=${ss.scholarship_code}">
                  ${ss.scholarship_code }
               </a>
            </td>
            <td>
               ${ss.student_code }
            </td>
            <td>
               ${ss.code_name }
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
   <a href="registerscholarship.htm"><button style="float:right;" class="btn btn-danger">되돌아가기</button></a>
   </div>
</div>