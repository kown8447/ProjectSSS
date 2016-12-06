<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="http://malsup.github.com/jquery.form.js"></script>
<div class="container" style="width:60%">
   <h4>▶&nbsp;학부 등록 </h4>
   <br><br>
   <div id="classroomRegister" style="width:90%; margin: auto;">
      <form action="insertDepartment.htm" method="post" id="insertDepartment_form">
         <table class="table">
            <tr>
               <td>단대코드</td>
               <td>
                  <div class="col-sm-6 ">   
                     <select name="college_code" id="college_code" class="form-control">
                        <c:forEach items="${college}" var="cl">
                           <option value="${cl.college_code}">${cl.college_name}(${cl.college_code})</option>
                        </c:forEach>
                     </select>
                  </div>
               </td>
            </tr>
            <tr>
               <td>사무실코드</td>
               <td>
                  <div class="col-sm-6 ">
                     <select id="office_code" name="office_code" class="form-control">
                        <c:forEach items="${officelist}" var="of">
                           <option value="${of.office_code}">${of.office_name}(${of.office_code})</option>
                        </c:forEach>
                     </select>
                  </div>
               </td>
            </tr>
            <tr>
               <td>이름</td>
               <td>
                  <div class="col-sm-6 ">
                     <input type="text" name="department_name" id="department_name" class="form-control">
                  </div>
               </td>
            </tr>
            <tr>
               <td>정원</td>
               <td>
                  <div class="col-sm-6 ">
                     <input type="text" id="department_seat " name="department_seat" class="form-control">
                  </div>
               </td>
            </tr>
            <tr>
               <td>졸업학점(전공,교양)</td>
               <td>
                  <div class="col-sm-6 ">
                     <input type="text" id="graduation_credit " name="graduation_credit" class="form-control">
                  </div>
               </td>
            </tr>
            <tr>
               <td>복수전공 가능여부</td>
               <td>
                  <div class="form-group">   
                           <label class="radio-inline">
                              불가능&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" id="double_possible " name="double_possible" value="0">
                           </label>
                           <label class="radio-inline">
                              가능&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" id="double_possible " name="double_possible" value="1">
                           </label>
                       </div>
               </td>
            </tr>
            <tr>
               <td>설명</td>
               <td>
                  <div class="col-sm-12 ">
                     <textarea id="department_description " name="department_description" class="form-control"></textarea>
                  </div>
               </td>
            </tr>
         </table>
      </form>
      <div align="center">
            <a href="departmentlist.htm"><button class="btn btn-default">학부 목록</button></a>
            <button class="btn btn-warning" data-target="#layerpop" data-toggle="modal">엑셀일괄등록</button>
            <input type="button" value="등록" class="btn btn-success" id="departmentreg">
         </div>
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