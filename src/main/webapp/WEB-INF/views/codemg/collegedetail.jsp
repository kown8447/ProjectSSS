<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<div class="container" style="width:60%">
<h4>▶&nbsp;단과대학 상세정보 </h4><br><br>   

<form action="updateColleage.htm" id="updateColleage_form">
<input type="hidden" name="before_office_code" value="${college.office_code}">
  <div style="border: 1px solid green; padding: 3%; border-radius: 1em; width: 90%; margin: auto;">
      <div class="form-horizontal">
         <div class="form-group">
         <label class="col-sm-3 control-label col-sm-offset-1">단대코드</label>
            <div class="col-sm-6">
               <input type="text" id="college_code" name="college_code" value="${college.college_code}" class ="form-control" readonly="readonly">
            </div>
         </div>
      
      
         <div class="form-group">
         <label class="col-sm-3 control-label col-sm-offset-1">사무실코드</label>
            <div class="col-sm-6">
               <select id="office_code" name="office_code" class="form-control">
                   <c:forEach items="${officelist}" var="of">
                   		<c:choose>
                   			<c:when test="${of.office_code == college.office_code}">
                   				<option selected="selected" value="${of.office_code}">${of.office_name}/(${of.office_code})</option>
                   			</c:when>
                   			<c:otherwise>
                   				<option value="${of.office_code}">${of.office_name}/(${of.office_code})</option>
                   			</c:otherwise>
                   		</c:choose>
                   </c:forEach>
               </select>
            </div>
         </div>
     
      
         <div class="form-group">
         <label class="col-sm-3 control-label col-sm-offset-1">이름</label>
            <div class="col-sm-6">
               <input type="text" id="college_name" name="college_name" value="${college.college_name}" class ="form-control">
            </div>
         </div>
      
         <div class="form-group">
         <label class="col-sm-3 control-label col-sm-offset-1">설명</label>
            <div class="col-sm-6">
               <textarea  id="college_description" name="college_description"  class ="form-control" rows="7">${college.college_description}</textarea>
            </div>
         </div>
      </div>   
   </div>
</form>
	<div align="center">
    	<a href="collegeList.htm" class="btn btn-default">목록</a> &nbsp;&nbsp;
    	<button type="submit" id="edit_college" class="btn btn-success">수정하기</button>
	</div>
</div>