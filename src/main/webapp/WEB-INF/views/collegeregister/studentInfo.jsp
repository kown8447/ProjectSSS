<!-- 
   @Project : InitSpring
   @File name : studentInfo.jsp
   @Author : 최준호
   @Data : 2016.11.30
   @Desc : 학생정보 조회 페이지
-->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 <h4>▶&nbsp;개인정보</h4> 
<br><br>

   <table style="width:95%;" >
      <tr>
         <th rowspan="2">
            <div style="text-align: center;">
               <img src="${pageContext.request.contextPath}/profiles/${student.member_picture}" class="img-rounded" style="height: 150px;  border: 1px solid "   >
            </div>   
         </th>
         <td></td>
         <td>
            <table class="table table-bordered">
               <tr>
                  <th style="text-align: center;background-color: #F8F4EC;width:12%;">학번</th>
                  <td style="text-align: center;">${student.student_code}</td>
                  <th style="text-align: center;background-color: #F8F4EC;width:12%;">성명</th>
                  <td style="text-align: center;width:40%;">${student.member_name}</td>
                  </tr>
                  <tr>
                     <th style="text-align: center;background-color: #F8F4EC;">생년월일</th>
                     <td style="text-align: center;">${student.member_birth}</td>
                     <th style="text-align: center;background-color: #F8F4EC;">주소</th>
                     <td style="text-align: center;">${student.member_addr}</td>
                  </tr>
                  <tr>
                     <th style="text-align: center;background-color: #F8F4EC;">e-mail</th>
                     <td style="text-align: center;">${student.member_email}</td>
                     <th style="text-align: center;background-color: #F8F4EC;">연락처</th>
                     <td style="text-align: center;">${student.member_phone}</td>
                  </tr>
            </table>
          
            <table class="table table-bordered">
               <tr>
                  <th style="text-align: center;background-color: #F8F4EC;width:20%;">대학</th>
                  <td style="text-align: center;">${mainMajor.college_name}</td>
                  <th style="text-align: center;background-color: #F8F4EC;width:20%;">입학년도</th>
                  <td style="text-align: center;width:31%;">${student.enterYear}</td>
               </tr>
               <tr>
                  <th style="text-align: center;background-color: #F8F4EC;">학부/학과</th>
                  <td style="text-align: center;">${mainMajor.department_name}</td>
                  <th style="text-align: center;background-color: #F8F4EC;">학적상태</th>
                  <td style="text-align: center;">${student.code_name}</td>
               </tr>
               <tr>
                  <th style="text-align: center;background-color: #F8F4EC;">휴학학기</th>
                  <td style="text-align: center;">${absenceCount}</td>
                  <th style="text-align: center;background-color: #F8F4EC;">잔여 휴학학기</th>
                  <td style="text-align: center;">${8-absenceCount}</td>
               </tr>
               <tr>
                  <th style="text-align: center;background-color: #F8F4EC;">총 이수학점</th>   
                  <td style="text-align: center;">${state.total_credit}</td>
                  <th style="text-align: center;background-color: #F8F4EC;">이수/예정학기</th>
                  <td style="text-align: center;">${semesterCount}/8</td>
               </tr>   
               <tr>
                  <th style="text-align: center;background-color: #F8F4EC;">복수전공</th>
                  <td colspan="3" style="text-align: center;">
                  	<c:if test="${doubleMajor!=null}">${doubleMajor.department_name}</c:if> 
                  </td>
               </tr>
            </table> 
         </td>
      </tr>
   </table>
 
   

