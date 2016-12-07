<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="http://malsup.github.com/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<div class = "row">
<div class = "col-sm-3"></div>
   <div class="col-sm-6"  >
   <h4>▶&nbsp;교수 등록 </h4>
   <br><br>
      <form action="professorCodeRegister.htm" method="post" id="professorCodeRegister_form">
         <table class="table" style="text-align: center">
            <tr>
               <td>코드</td>
               <td>
                  <div class="col-sm-6"  > 
                     <input type="text" name="code" id="code" class="form-control">
                  </div>   
               </td>
            </tr>
            <tr>
               <td>학과코드</td>
               <td>
                  <div class="col-sm-6"  >
                  <select id="department_code" name="department_code" class="form-control">
                     <c:forEach items="${department }" var="dp">
                        <option value="${dp.department_code}">${dp.department_name}(${dp.department_code})</option>
                     </c:forEach>
                  </select>
                  </div>
               </td>
            </tr>
            <tr>
               <td>이름</td>
               <td>
                  <div class="col-sm-6"  >
                     <input type="text" name="code_name" id="code_name" class="form-control">
                  </div>   
               </td>
            </tr>
            <tr>
               <td>생년월일</td>
               <td>
                  <div class="col-sm-6"  >
                     <input type="text" name="code_birth" id="code_birth" class="form-control">
                  </div>   
               </td>
            </tr>
         </table>
      </form>
      <div align="center">
               <a href="typeofcodelist.htm?code_type=1"><button class="btn btn-default">교수 목록</button></a>
               <button class="btn btn-warning" data-target="#layerpop" data-toggle="modal">엑셀일괄등록</button>
              <input type="button" value="등록" class="btn btn-success" id="professor_reg">
         </div>
   </div>
</div>


<div class="modal fade" id="layerpop">
   <div class="modal-dialog modal-sm">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">×</button>
            <h5 class="modal-title">교수 일괄 등록</h5>
         </div>
         <div class="modal-body">
            <form id="professorexcelForm" action="professorExcelUpload.htm", method="post">
               <span class="filetype"> <input type="text" class="file-text" />
                  <span class="file-btn">찾아보기</span> <span class="file-select">
                        <input id="professorexcel" name="professorexcel" type="file" placeholder="Excel input" class="input-file" accept=".xlsx"   >
                  </span>
               </span>
            </form>
            Sample File :<a href="professorexcel.htm">professor.xlsx</a> <br> 
            <font style="font-size: 8pt; color: #A6A6A6;">파일을 다운로드 하여 전공/부전공을 일괄등록 해보세요</font>
         </div>
         <div class="modal-footer">
            <button type="button" id="professorexcelUp" class="btn btn-success">등록하기</button>   
         </div>
      </div>
   </div>
</div>   