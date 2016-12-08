<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<h4>▶&nbsp;연구실 상세정보 </h4>
<br><br>
<form action="updateLab.htm" id="updateLab_form">
   <div style="border: 1px solid green; padding: 3%; border-radius: 1em; width: 40%; margin: auto;">
      <div class="form-horizontal">
         <div class="form-group">
         <label class="col-sm-3 control-label col-sm-offset-1">연구실코드</label>
            <div class="col-sm-6">
               <input type="text" value="${lab.lab_code }" id="lab_code" name="lab_code" readonly="readonly" class="form-control">
            </div>
         </div>
      </div>
      <div class="form-horizontal">
         <div class="form-group">
         <label class="col-sm-3 control-label col-sm-offset-1">건물코드</label>
            <div class="col-sm-6">
               <select id="building_code" name="building_code" class="form-control">
                  <c:forEach items="${building}" var="bd">
                    <c:choose>
                     	<c:when test="${bd.building_code==lab.building_code}">
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
      </div>
      <div class="form-horizontal">
         <div class="form-group">
         <label class="col-sm-3 control-label col-sm-offset-1">연구실 이름</label>
            <div class="col-sm-6">
               <input type="text" value="${lab.lab_name }" id="lab_name" name="lab_name" class="form-control">
            </div>
         </div>
      </div>
      <div class="form-horizontal">
         <div class="form-group">
         <label class="col-sm-3 control-label col-sm-offset-1">전화번호</label>
            <div class="col-sm-6">
               <input type="text" value="${lab.lab_phone }" id="lab_phone" name="lab_phone" class="form-control" placeholder="ex)000-0000-0000">
            </div>
         </div>
      </div>
      <div class="form-group" style="text-align: center;">
     		<div id="labPhoneCheck"></div>
      </div>
      <div class="form-horizontal">
         <div class="form-group">
         <label class="col-sm-3 control-label col-sm-offset-1">사용가능 여부</label>
            <div class="col-sm-6">
            <label class="radio-inline">
              <c:choose>
               <c:when test="${lab.lab_possible ==0}">
                  <input type="radio" value="0" id="lab_possible" name="lab_possible" checked>사용가능       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <input type="radio" value="1" id="lab_possible" name="lab_possible">사용불가
               </c:when>
               <c:when test="${lab.lab_possible ==1}">
                  <input type="radio" value="0" id="lab_possible" name="lab_possible" >사용가능       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <input type="radio" value="1" id="lab_possible" name="lab_possible" checked>사용불가
               </c:when>
            </c:choose>   
            </label> 
            
            </div>
         </div>
      </div>
   </div>
   
   <br><br>
   
   </form>
   <div align="center">
      <a href="showlablist.htm"><button class="btn btn-default">목록</button></a> &nbsp;&nbsp;<button type="submit" id="edit_lab" class="btn btn-success">수정하기</button>
   </div>   
