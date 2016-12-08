<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<h4>▶&nbsp;강의실 상세정보 </h4>
<br><br>
<form action="updateClassroom.htm" id="updateClassroom_form">
   <div style="border: 1px solid green; padding: 3%; border-radius: 1em; width: 40%; margin: auto;">
      <div class="form-horizontal">
         <div class="form-group">
         <label class="col-sm-3 control-label col-sm-offset-1">강의실코드</label>
            <div class="col-sm-6">
               <input type="text" value="${classroom.classroom_code }" class ="form-control" readonly="readonly" id="classroom_code" name="classroom_code">
            </div>
         </div>
      
      
         <div class="form-group">
         <label class="col-sm-3 control-label col-sm-offset-1">건물코드</label>
            <div class="col-sm-6">
               <select id="building_code" name="building_code" class="form-control">
                  <c:forEach items="${building}" var="bd">
                    <c:choose>
                     	<c:when test="${bd.building_code==classroom.building_code}">
                     		<option selected="selected" value="${bd.building_code}">${bd.building_name}</option>
                     	</c:when>
                     	<c:otherwise>
                     		<option value="${bd.building_code}">${bd.building_name}</option>
                     	</c:otherwise>
                     </c:choose>
                  </c:forEach>
               </select>
            </div>
         </div>
    
      
         <div class="form-group">
         <label class="col-sm-3 control-label col-sm-offset-1">강의실 이름</label>
            <div class="col-sm-6">
               <input type="text" value="${classroom.classroom_name}" class ="form-control" id="classroom_name" name="classroom_name">
            </div>
         </div>
     
     
         <div class="form-group">
         <label class="col-sm-3 control-label col-sm-offset-1">수용인원</label>
            <div class="col-sm-6">
               <input type="text" value="${classroom.seat}" class ="form-control" id="seat" name="seat">
            </div>
         
      	</div>
      
      
         <label class="col-sm-2 control-label col-sm-offset-2">종류</label>&nbsp;&nbsp;&nbsp;&nbsp;
            <div class="col-sm-6">
               <label class="radio-inline">
                 <c:choose>
                  <c:when test="${classroom.classroom_type ==0}">
                     <input type="radio" value="0" id="classroom_type" name="classroom_type" checked>일반강의실       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                     <input type="radio" value="1" id="classroom_type" name="classroom_type">실습실
                  </c:when>
                  <c:when test="${classroom.classroom_type ==1}">
                     <input type="radio" value="0" id="classroom_type" name="classroom_type" >일반강의실       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                     <input type="radio" value="1" id="classroom_type" name="classroom_type" checked>실습실
                  </c:when>
               </c:choose>   
               </label> 
            </div>
      </div>
    </div>
   

   <br><br>
      
</form>
   <div align="center">
      <a href="showclasslist.htm"><button class="btn btn-default">목록</button></a> &nbsp;&nbsp;<button type="submit" id="edit_classroom" class="btn btn-success">수정하기</button>
   </div>   
