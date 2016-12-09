<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
   @Project : InitSpring
   @File name : detailscholarship.jsp
   @Author : 성홍모
   @Data : 2016.11.09
   @Desc : 장학생 상세보기 view
-->         
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<div class = "row">
<div class = "col-sm-3"></div>
<div class="col-sm-6"  >
<h4>▶&nbsp;장학생 상세정보 </h4><br><br>

<form action="updateScholarship.htm" id="updateScholarship_from">
	<div style="border: 1px solid green; padding: 3%; border-radius: 1em;  margin: auto;">
		<c:set value="${scholarship}" var="sl"/>
		<c:set value="${scsystem}" var="sc"/>
		<c:set value="${semester}" var="sm"/>
		 <div class="form-horizontal">
		 	<div class="form-group">
         		<label class="col-sm-3 control-label col-sm-offset-1">장학수혜코드</label>
            	<div class="col-sm-6">
               		<input type="text" id="scholarship_code" name="scholarship_code" value="${sl.scholarship_code }" readonly="readonly" class="form-control">
            	</div>
         	</div>
         	
         	<div class="form-group">
         		<label class="col-sm-3 control-label col-sm-offset-1">학번</label>
            	<div class="col-sm-6">
               		<input type="text" id="student_code" name="student_code" value="${sl.student_code }" readonly="readonly" class="form-control">
            	</div>
           </div>
           
           <div class="form-group">
         		<label class="col-sm-3 control-label col-sm-offset-1">장학코드</label>
            	<div class="col-sm-6">
               		<select id="sys_code" name="sys_code" class="form-control">
							<c:forEach items="${scsystem}" var="sclsit">
							<option value="${sclsit.sys_code }">${sclsit.scholaship_name }</option>
							</c:forEach>
						</select>
            	</div>
           </div>
           
           <div class="form-group">
         		<label class="col-sm-3 control-label col-sm-offset-1">학기코드</label>
            	<div class="col-sm-6">
               		<select id="semester_code" name="semester_code" class="form-control">
							<c:forEach items="${semester}" var="smlsit">
							<option value="${smlsit.semester_code }">${smlsit.semester_name }</option>
							</c:forEach>
						</select>
            	</div>
           </div>
           	
           	<div class="form-group">
         		<label class="col-sm-3 control-label col-sm-offset-1">지급일</label>
            	<div class="col-sm-6">
               		<input type="text" id="scholarship_payday" name="scholarship_payday" value="${sl.scholarship_payday }" class="form-control">
            	</div>
           </div>	
      	</div>   
	</div>
</form>
	<div align="center">
		<a href="registerscholarship.htm"class="btn btn-default">목록</a> &nbsp;&nbsp;
		<button type="submit" id ="edit_scholarship" class="btn btn-success">수정하기</button>
	</div>
	<div style="height: 10%;"></div>
</div>
</div>