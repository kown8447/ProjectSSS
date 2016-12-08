<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="http://malsup.github.com/jquery.form.js"></script>
<div class = "row">
<div class = "col-sm-2"></div>
   <div class="col-sm-7"  >
  
   <h4>▶&nbsp;단대 등록 </h4><br><br>
   
      <form action="insertCollege.htm" method="post" id="insertCollege_form">
          <div class="form-horizontal">
            <div class="form-group">
			<label class="col-sm-2 control-label col-sm-offset-2">사무실 코드</label>
				<div class="col-sm-6">
					<select id="office_code" name="office_code" class="form-control">
                    	<c:forEach items="${officelist}" var="of">
                       	<option value="${of.office_code}">${of.office_name}/(${of.office_code})</option>
                    	</c:forEach>
                	</select>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-2">이름</label>
				<div class="col-sm-6">
					<input type="text" name="college_name" id="college_name" class="form-control">
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-2">설명</label>
				<div class="col-sm-6">
					 <textarea id="college_description" rows="7" name="college_description" class="form-control"></textarea>
				</div>
			</div>
         </div>        
      </form>
      <br><br>
      <div align="center">
            <a href="collegeList.htm"><button class="btn btn-default">단과대학 목록</button></a>
            <button class="btn btn-warning" data-target="#layerpop" data-toggle="modal">엑셀일괄등록</button>
           <input type="button" value="등록" class="btn btn-success" id="college_reg">
       </div>
       <div style="height: 15%;"></div> 
   </div>
</div>

<div class="modal fade" id="layerpop">
   <div class="modal-dialog modal-sm">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">×</button>
            <h5 class="modal-title">단과대학 일괄 등록</h5>
         </div>
         <div class="modal-body">
            <form id="colexcelForm" action="colExcelUpload.htm">
               <span class="filetype"> <input type="text" class="file-text" />
                  <span class="file-btn">찾아보기</span> <span class="file-select">
                     <input id="colexcel" name="colexcel" type="file" placeholder="Excel input" class="input-file" accept=".xlsx"   >
                  </span>
               </span>
            </form>
            Sample File :<a href="colexcel.htm">college.xlsx</a> <br> 
            <font style="font-size: 8pt; color: #A6A6A6;">파일을 다운로드 하여 단과대학을 일괄등록 해보세요</font>
         </div>
         <div class="modal-footer">
            <button type="button" id="colexcelUp" class="btn btn-success">등록하기</button>   
         </div>
      </div>
   </div>
</div>   