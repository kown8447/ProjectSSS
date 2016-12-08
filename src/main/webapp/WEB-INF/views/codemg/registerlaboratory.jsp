<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="http://malsup.github.com/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<div class="container" style="width:65%">
   <h4>▶&nbsp;연구실 등록 </h4> <br><br>   
      <form action="insertLab.htm" method="post" id="insertLab_form">
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
			<label class="col-sm-2 control-label col-sm-offset-2">연구실 이름</label>
			<div class="col-sm-6">
				<input type="text" name="lab_name" id="lab_name" class="form-control">
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-2 control-label col-sm-offset-2">전화번호</label>
			<div class="col-sm-6">
				<input type="text" name="lab_phone" id="lab_phone" class="form-control">
			</div>
		</div>
		</div>
      </form>
      
      <br><br>
      <div align="center">
         <a href="showlablist.htm"><button class="btn btn-default">연구실 목록</button></a>
         <button class="btn btn-warning" data-target="#layerpop" data-toggle="modal">엑셀일괄등록</button>
         <input type="button" value="등록" class="btn btn-success" id="labreg">
      </div>
      <div style="height: 15%;"></div> 
</div>
  

<div class="modal fade" id="layerpop">
   <div class="modal-dialog modal-sm">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">×</button>
            <h5 class="modal-title">연구실 일괄 등록</h5>
         </div>
         <div class="modal-body">
            <form id="lbexcelForm" action="lbExcelUpload.htm">
               <span class="filetype"> <input type="text" class="file-text" />
                  <span class="file-btn">찾아보기</span> <span class="file-select">
                      <input id="lbexcel" name="lbexcel"  class="input-file" type="file" accept=".xlsx"   placeholder="Excel input">
               </span>
               </span>
            </form>
            Sample File :<a href="lbexcel.htm">laboratory.xlsx</a> <br> 
            <font style="font-size: 8pt; color: #A6A6A6;">파일을 다운로드 하여 연구실을 일괄등록 해보세요</font>
         </div>
         <div class="modal-footer">
            <button type="button" id="lbexcelUp" name="lbexcelUp" class="btn btn-success">등록하기</button>   
         </div>
      </div>
   </div>
</div>   