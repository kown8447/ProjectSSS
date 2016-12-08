<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="http://malsup.github.com/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<div class = "row">
<div class = "col-sm-3"></div>
<div class="col-sm-6"  >
   <h4>▶&nbsp;부전공 등록</h4><br><br>
   
      <form action="insertMjRecord.htm" method="post" id="insertMjRecord_form">
         <div class="form-horizontal">
         	<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-2">학번코드</label>
				<div class="col-sm-6">
					<input type="text" name="student_code" id="student_code" class="form-control">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-2">학과코드</label>
				<div class="col-sm-6">
					<select name="department_code" id="department_code" class="form-control">
                    	 <c:forEach items="${department}" var="dp">
                        	<option value="${dp.department_code}">${dp.department_name} (${dp.department_code})</option>
                     	</c:forEach>
                  	</select>
				</div>
			</div>           
               <input type="hidden" name="mj_type" id="mj_type" value="1">
         </div>      
      </form>
      <br><br>
      <div align="center">
               <a href="mjRecordList.htm" class="btn btn-default">전공/부전공 목록</a>
               <button class="btn btn-warning" data-target="#layerpop" data-toggle="modal">엑셀일괄등록</button>
              <input type="button" value="등록" class="btn btn-success" id="mjrecord_reg">
         </div>   
         <div style="height:15%;"></div>  
      </div>
   </div>


<div class="modal fade" id="layerpop">
   <div class="modal-dialog modal-sm">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">×</button>
            <h5 class="modal-title">전공/부전공 일괄 등록</h5>
         </div>
         <div class="modal-body">
            <form id="mjexcelForm" action="mjExcelUpload.htm">
               <span class="filetype"> <input type="text" class="file-text" />
                  <span class="file-btn">찾아보기</span> <span class="file-select">
                    <input id="mjexcel" name="mjexcel" type="file" placeholder="Excel input" class="input-file" accept=".xlsx"   >
                  </span>
               </span>
            </form>
            Sample File :<a href="sclexcel.htm">mjrecord.xlsx</a> <br> 
            <font style="font-size: 8pt; color: #A6A6A6;">파일을 다운로드 하여 전공/부전공을 일괄등록 해보세요</font>
         </div>
         <div class="modal-footer">
            <button type="button" id="mjexcelUp" class="btn btn-success">등록하기</button>   
         </div>
      </div>
   </div>
</div>   