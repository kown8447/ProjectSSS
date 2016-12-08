<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="http://malsup.github.com/jquery.form.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<div class="container" style="width:65%">
   <h4>▶&nbsp;사무실 등록 </h4><br><br>   

      <form action="insertOffice.htm" method="post" id="insertOffice_form">
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
			<label class="col-sm-2 control-label col-sm-offset-2">전화번호</label>
			<div class="col-sm-6">
				<input type="text" name="office_phone" id="office_phone" class="form-control">
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-2 control-label col-sm-offset-2">사무실 이름</label>
			<div class="col-sm-6">
				<input type="text" name="office_name" id="office_name" class="form-control">
			</div>
		</div>
	</div>
	</form>
    
      <br><br>
      <div align="center">
         <a href="showofficelist.htm"><button class="btn btn-default">사무실 목록</button></a>
         <button class="btn btn-warning" data-target="#layerpop" data-toggle="modal">엑셀일괄등록</button>
         <input type="button" value="등록" class="btn btn-success" id="officereg">
      </div>
      <div style="height:15%;"></div>
 </div>


<div class="modal fade" id="layerpop">
   <div class="modal-dialog modal-sm">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">×</button>
            <h5 class="modal-title">사무실 일괄 등록</h5>
         </div>
         <div class="modal-body">
            <form id="ofexcelForm" action="ofExcelUpload.htm">
               <span class="filetype"> <input type="text" class="file-text" />
                  <span class="file-btn">찾아보기</span> <span class="file-select">
                     <input id="ofexcel" name="ofexcel"  class="input-file" type="file" accept=".xlsx"   placeholder="Excel input">
               </span>
               </span>
            </form>
            Sample File :<a href="ofexcel.htm">Office.xlsx</a> <br> 
            <font style="font-size: 8pt; color: #A6A6A6;">파일을 다운로드 하여 사무실을 일괄등록 해보세요</font>
         </div>
         <div class="modal-footer">
            <button type="button" id="ofexcelUp" name="ofexcelUp" class="btn btn-success">등록하기</button>   
         </div>
      </div>
   </div>
</div>   