<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="http://malsup.github.com/jquery.form.js"></script>
<div class = "row">
<div class = "col-sm-3"></div>
   <div class="col-sm-6"  >
   <h4>▶&nbsp;등록 </h4>
   <br><br>
   <div id="register">
      <form action="insertRegister.htm" method="post" id="insertRegister_form">
         <table class="table" style="text-align: center">
            <tr>
               <td>학번</td>
               <td>
                  <div class="col-sm-6"  > 
                     <input type="text" name="student_code" id=student_code class="form-control">
                  </div>
               </td>
            </tr>
            <tr>
               <td>학기코드</td>
               <td>
                  <div class="col-sm-6"  > 
                  <select id="semester_code " name="semester_code" class="form-control">
                     <c:forEach items="${semester}" var="sm">
                        <option value="${sm.semester_code}">${sm.semester_name}( ${sm.semester_code})</option>
                     </c:forEach>
                  </select>
                  </div>
               </td>
            </tr>
            <tr>
               <td>등록금</td>
               <td>
                  <div class="col-sm-6"  > 
                     <input type="text" id="tuition" name="tuition" class="form-control">
                  </div>
               </td>
            </tr>
            <tr>
               <td>등록구분</td>
               <td>
               <div class="col-sm-6"  > 
                  <div class="form-group">   
                             등록&nbsp;&nbsp;&nbsp;<input type="radio" value="0" id="register_state" name="register_state">&nbsp;&nbsp;
                             취소&nbsp;&nbsp;&nbsp;<input type="radio" value="1" id="register_state" name="register_state">
                        </div>
               </div>
               </td>
            </tr>
            <tr>
               <td>등록여부</td>
               <td>
               <div class="col-sm-9"  > 
                  <div class="form-group">   
                             일반학기&nbsp;&nbsp;&nbsp;<input type="radio" value="0" id="register_type" name="register_type">
                             계절학기&nbsp;&nbsp;&nbsp;<input type="radio" value="1" id="register_type" name="register_type">
                           졸업연기&nbsp;&nbsp;&nbsp;<input type="radio" value="2" id="register_type" name="register_type">
                        </div>
               </div>
               </td>
            </tr>
         </table>
      </form>
      
      <div align="center">
               <a href="registerlist.htm"><button class="btn btn-default">등록 목록</button></a>
               <button class="btn btn-warning" data-target="#layerpop" data-toggle="modal">엑셀일괄등록</button>
              <input type="button" value="등록" class="btn btn-success" id="register_reg">
         </div>
   </div>
</div>
</div>



<div class="modal fade" id="layerpop">
   <div class="modal-dialog modal-sm">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">×</button>
            <h5 class="modal-title">등록 일괄 등록</h5>
         </div>
         <div class="modal-body">
            <form id="regexcelForm" action="regExcelUpload.htm" method="post">
               <span class="filetype"> <input type="text" class="file-text" />
                  <span class="file-btn">찾아보기</span> <span class="file-select">
                        <input id="regexcel" name="regexcel" type="file" placeholder="Excel input" class="input-file" accept=".xlsx"   >
                  </span>
               </span>
            </form>
            Sample File :<a href="regexcel.htm">register.xlsx</a> <br> 
            <font style="font-size: 8pt; color: #A6A6A6;">파일을 다운로드 하여 등록을 일괄등록 해보세요</font>
         </div>
         <div class="modal-footer">
            <button type="button" id="regexcelUp" class="btn btn-success">등록하기</button>   
         </div>
      </div>
   </div>
</div>   