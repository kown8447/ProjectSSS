<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<div class="container" style="width:75%">
<h4>▶&nbsp;단과대학 리스트 </h4>
<br><br>
 <div class="container" style="width:95%">
   <form action="updateColleage.htm">
      <table class="table" style="text-align: center">
         <thead>
            <tr>
               <td>단대코드</td>
               <td>사무실코드</td>
               <td>이름</td>
               <td>설명</td>
            </tr>
         </thead>
         <c:forEach items="${college}" var="cl">
            <tbody>
               <tr>
                  <td>${cl.college_code}</td>
                  <td>${cl.office_code}</td>
                  <td>
                     <a href="selectCollege.htm?college_code=${cl.college_code}">
                        ${cl.college_name}
                     </a>
                  </td>
                  <td>${cl.college_description }</td>
               </tr>
            </tbody>
         </c:forEach>
      </table>
      </form>
      <a href="registercollege.htm"><button style="float:right;" class="btn btn-danger">되돌아가기</button></a>
      
   </div>
</div>
   