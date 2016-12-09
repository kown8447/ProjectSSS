<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!-- 
   @Project : InitSpring
   @File name : registerclassroom.jsp
   @Author : 성홍모
   @Data : 2016.11.09
   @Desc : 강의실 등록 pageView
-->       
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="http://malsup.github.com/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<div class="container" style="width:65%">
   <h4>▶&nbsp;강의실 등록 </h4> <br><br>
      <form action="registerClassroom.htm" method="post" id="registerClassroom_form">
        <div class="form-horizontal">
		<div class="form-group">
			<label class="col-sm-2 control-label col-sm-offset-2">건물코드</label>
			<div class="col-sm-6">
				<select id="building_code" name="building_code" class="form-control">
                        <c:forEach items="${building}" var="bd">
                           <option value="${bd.building_code}">${bd.building_name}(${bd.building_code})</option>
                        </c:forEach>
                 </select>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-2 control-label col-sm-offset-2">주소</label>
			<div class="col-sm-6">
				<input type="text" name="classroom_name" id="classroom_name" class="form-control">
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-2 control-label col-sm-offset-2">수용인원</label>
			<div class="col-sm-6">
				<input type="text" name="seat" id="seat" class="form-control">
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-2 control-label col-sm-offset-2">강의실 타입</label>
			<div class="col-sm-6">
				 <label class="radio-inline">
                        일반강의실&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="0" id="classroom_type" name="classroom_type">
                     </label>
                     <label class="radio-inline">
                        실습실&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="1" id="classroom_type" name="classroom_type">
                     </label>
			</div>
		</div>
		</div>
      </form>
     </div>
      
      <br><br>
      <div align="center">
         <a href="showclasslist.htm"><button class="btn btn-default">강의실 목록</button></a>
         <button class="btn btn-warning" data-target="#layerpop" data-toggle="modal">엑셀일괄등록</button>
         <input type="button" value="등록" class="btn btn-success" id="classreg">
      </div>
      <div style="height:15%;"></div>  


<div class="modal fade" id="layerpop">
   <div class="modal-dialog modal-sm">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">×</button>
            <h5 class="modal-title">강의실 일괄 등록</h5>
         </div>
         <div class="modal-body">
            <form id="clexcelForm" action="classroomExcelUpload.htm">
               <span class="filetype"> <input type="text" class="file-text" />
                  <span class="file-btn">찾아보기</span> <span class="file-select">
                     <input id="clexcel" name="clexcel" type="file" placeholder="Excel input" class="input-file" accept=".xlsx"   >
                  </span>
               </span>
            </form>
            Sample File :<a href="classroomexcel.htm">classroom.xlsx</a> <br> 
            <font style="font-size: 8pt; color: #A6A6A6;">파일을 다운로드 하여 강의실을 일괄등록 해보세요</font>
         </div>
         <div class="modal-footer">
            <button type="button" id="clexcelUp" class="btn btn-success">등록하기</button>   
         </div>
      </div>
   </div>
</div>   