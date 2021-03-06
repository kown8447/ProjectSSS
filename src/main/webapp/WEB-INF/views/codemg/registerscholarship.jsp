<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!-- 
   @Project : InitSpring
   @File name : registerscholarship.jsp
   @Author : 성홍모
   @Data : 2016.11.09
   @Desc : 학생에게 장학금을 등록해주는 pageView
-->       
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="http://malsup.github.com/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<div class = "row">
<div class = "col-sm-3"></div>
<div class="col-sm-6"  >
   <h4>▶&nbsp;장학 등록 </h4><br><br>
    
      <form action="insertScholarship.htm" method="post" id="insertScholarship_form">
         <div class="form-horizontal">
            <div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-2">학번 코드</label>
				<div class="col-sm-6">
					<input type="text" name="student_code" id="student_code"  class="form-control">
				</div>
				<input type="button" class="btn btn-default" id="check_student_code" value="학번 조회">
			</div>
            
            <div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-2">장학코드</label>
				<div class="col-sm-6">
					 <select id="sys_code" name="sys_code" class="form-control">
                        <c:forEach items="${sc}" var="sc">
                           <option value="${sc.sys_code}">${sc.scholaship_name}(${sc.sys_code})</option>
                        </c:forEach>
                     </select>
				</div>
			</div> 
			
			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-2">학기코드</label>
				<div class="col-sm-6">
					<select id="semester_code " name="semester_code" class="form-control">
                        <c:forEach items="${semester}" var="sm">
                           <option value="${sm.semester_code}">${sm.semester_name}(${sm.semester_code})</option>
                        </c:forEach>
                  	</select>
				</div>
			</div>       
             
             <div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-2">지급일</label>
				<div class="col-sm-6">
					<input type="text" name="scholarship_payday" id="scholarship_payday" class="form-control">
				</div>
			</div>
         </div>
      </form>
      <br><br>
      <div align="center">
               <a href="scholarshipList.htm" class="btn btn-default">장학생 목록</a>
               <button class="btn btn-warning" data-target="#layerpop" data-toggle="modal">엑셀일괄등록</button>
               <input type="button" value="등록" class="btn btn-success" id="scholar_reg">
     </div>   
   </div>
   <div style="height: 10%;"></div>
</div>



<div class="modal fade" id="layerpop">
   <div class="modal-dialog modal-sm">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">×</button>
            <h5 class="modal-title">장학생 일괄 등록</h5>
         </div>
         <div class="modal-body">
            <form id="sclexcelForm" action="sclExcelUpload.htm">
               <span class="filetype"> <input type="text" class="file-text" />
                  <span class="file-btn">찾아보기</span> <span class="file-select">
                     <input id="sclexcel" name="sclexcel" type="file" placeholder="Excel input" class="input-file" accept=".xlsx"   >
                  </span>
               </span>
            </form>
            Sample File :<a href="sclexcel.htm">sc_system.xlsx</a> <br> 
            <font style="font-size: 8pt; color: #A6A6A6;">파일을 다운로드 하여 장학생을 일괄등록 해보세요</font>
         </div>
         <div class="modal-footer">
            <button type="button" id="sclexcelUp" class="btn btn-success">등록하기</button>   
         </div>
      </div>
   </div>
</div>   