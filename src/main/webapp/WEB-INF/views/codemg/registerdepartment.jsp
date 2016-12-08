<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="http://malsup.github.com/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<div class="container" style="width:60%">
   <h4>▶&nbsp;학부 등록 </h4><br><br>
   
  
      <form action="insertDepartment.htm" method="post" id="insertDepartment_form">
         <div class="form-horizontal">
            <div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-2">단대코드</label>
				<div class="col-sm-6">
					<select name="college_code" id="college_code" class="form-control">
                        <c:forEach items="${college}" var="cl">
                           <option value="${cl.college_code}">${cl.college_name}(${cl.college_code})</option>
                        </c:forEach>
                     </select>
				</div>
			</div> 
			
			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-2">사무실 코드</label>
				<div class="col-sm-6">
					<select id="office_code" name="office_code" class="form-control">
                        <c:forEach items="${officelist}" var="of">
                           <option value="${of.office_code}">${of.office_name}(${of.office_code})</option>
                        </c:forEach>
                     </select>
				</div>
			</div>
                     
            <div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-2">학과명</label>
				<div class="col-sm-6">
					<input type="text" name="department_name" id="department_name" class="form-control">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-2">정원</label>
				<div class="col-sm-6">
					 <input type="text" id="department_seat" name="department_seat" class="form-control">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-2">졸업학점(전체)</label>
				<div class="col-sm-6">
					 <input type="text" id="graduation_credit" name="graduation_credit" class="form-control">
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-2">설명</label>
				<div class="col-sm-6">
					 <textarea id="department_description" rows="7" name="department_description" class="form-control"></textarea>
				</div>
			</div>
			
			<input type="hidden" id="double_possible" name="double_possible" value="1">      
         </div>
      </form>
      
      <br><br>
      <div align="center">
            <a href="departmentlist.htm" class="btn btn-default">학부 목록</a>
            <button class="btn btn-warning" data-target="#layerpop" data-toggle="modal">엑셀일괄등록</button>
            <input type="button" value="등록" class="btn btn-success" id="departmentreg">
         </div>
   </div>


<div class="modal fade" id="layerpop">
   <div class="modal-dialog modal-sm">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">×</button>
            <h5 class="modal-title">학부 일괄 등록</h5>
         </div>
         <div class="modal-body">
            <form id="depexcelForm" action="depExcelUpload.htm">
               <span class="filetype"> <input type="text" class="file-text" />
                  <span class="file-btn">찾아보기</span> <span class="file-select">
                    <input id="depexcel" name="depexcel" type="file" placeholder="Excel input" class="input-file" accept=".xlsx"   >
                  </span>
               </span>
            </form>
            Sample File :<a href="depexcel.htm">department.xlsx</a> <br> 
            <font style="font-size: 8pt; color: #A6A6A6;">파일을 다운로드 하여 학부를 일괄등록 해보세요</font>
         </div>
         <div class="modal-footer">
            <button type="button" id="depexcelUp" class="btn btn-success">등록하기</button>   
         </div>
      </div>
   </div>
</div>   