<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="http://malsup.github.com/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<div class = "row">
<div class = "col-sm-3"></div>
<div class="col-sm-6"  >
   <h4>▶&nbsp;장학제도 등록 </h4>
   <br><br>
   <div  style="width:90%; margin: auto;">
      <form action="insertScSystem.htm" method="post" id="insertScSystem_form">
         <table class="table">
            <tr>
               <td>장학명</td>
               <td>
                  <div class="col-sm-6"  >   
                  <input type="text" name="scholaship_name" id="scholaship_name" class="form-control">
                  </div>
               </td>
            </tr>
            <tr>
               <td>선발기준</td>
               <td>
                  <div class="col-sm-6">   
                     <input type="text" class="form-control" name="scholaship_standard" id="scholaship_standard">
                  </div>
               </td>
            </tr>
            <tr>
               <td>수혜인원</td>
               <td>
                  <div class="col-sm-6 "> 
                     <input type="text" name="scholaship_member" id="scholaship_member" class="form-control">
                  </div>   
               </td>
            </tr>
            <tr>
               <td>장학금액</td>
               <td>
                  <div class="col-sm-6"  >
                     <input type="text" name="scholaship_amount" id="scholaship_amount" class="form-control">
                  </div>
               </td>
            </tr>
            <tr>
               <td>비고</td>
               <td>
                  <div class="col-sm-12">
                     <textarea name="scholaship_note" id="scholaship_note" class="form-control">-</textarea>
                  </div>
               </td>
            </tr>
         </table>
      </form>
         <div align="center">
               <a href="scSystemList.htm"><button class="btn btn-default">장학금 목록</button></a>
               <button class="btn btn-warning" data-target="#layerpop" data-toggle="modal">엑셀일괄등록</button>
              <input type="button" value="등록" class="btn btn-success" id="sc_reg">
            </div>
   
   </div>
</div>
</div>
<div class = "col-sm-3"></div>

<div class="modal fade" id="layerpop">
   <div class="modal-dialog modal-sm">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">×</button>
            <h5 class="modal-title">장학제도 일괄 등록</h5>
         </div>
         <div class="modal-body">
            <form id="scsexcelForm" action="scsExcelUpload.htm">
               <span class="filetype"> <input type="text" class="file-text" />
                  <span class="file-btn">찾아보기</span> <span class="file-select">
                     <input id="scsexcel" name="scsexcel" type="file" placeholder="Excel input" class="input-file" accept=".xlsx"   >
                  </span>
               </span>
            </form>
            Sample File :<a href="scsexcel.htm">sc_system.xlsx</a> <br> 
            <font style="font-size: 8pt; color: #A6A6A6;">파일을 다운로드 하여 장학제도를 일괄등록 해보세요</font>
         </div>
         <div class="modal-footer">
            <button type="button" id="scsexcelUp" class="btn btn-success">등록하기</button>   
         </div>
      </div>
   </div>
</div>   