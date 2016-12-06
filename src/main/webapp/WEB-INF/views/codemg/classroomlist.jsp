<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<div class="container" style="width:75%">
<h4>▶&nbsp;강의실 리스트 </h4>
<br><br>
 <div class="container" style="width:95%">
   <table class="table" style="text-align: center">
      <thead>
         <tr>
            <td>강의실코드</td>
            <td>건물코드</td>
            <td>강의실 이름</td>
            <td>수용인원</td>
            <td>강의실 타입</td>
         </tr>
      </thead>
      <c:forEach items="${classlist}" var="c" varStatus="index">
      <tbody>
         <tr>
            <td>
               ${c.classroom_code }
            </td>
            <td>
               ${c.building_name }
            </td>
            <td>
               <a href="classroomUpdate.htm?classroom_code=${c.classroom_code}">${c.classroom_name }</a>   
            </td>
            <td>
               ${c.seat }
            </td>
            <td>
               <c:choose>
                  <c:when test="${c.classroom_type==0 }" >
                     일반강의실
                  </c:when>
                  <c:when test="${c.classroom_type==1 }" >
                     실습실
                  </c:when>
                  <c:otherwise>
                     에러
                  </c:otherwise>
               </c:choose>
            </td>
      </tbody>
      </c:forEach>
   </table>
      <a href="registerclassroom.htm"><button style="float:right;" class="btn btn-danger">되돌아가기</button></a>
   </div>   
</div>

