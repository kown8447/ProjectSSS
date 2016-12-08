<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="http://malsup.github.com/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<div class="container" style="width:60%">
   <h4>▶&nbsp;단대 등록 </h4>
   <br><br>
   <div id="classroomRegister" style="width:90%; margin: auto;">
      <form action="insertCollege.htm" method="post" id="insertCollege_form">
         <table class="table">
            <tr>
               <td>사무실코드</td>
               <td>
                  <div class="col-sm-6 ">   
                     <select id="office_code" name="office_code" class="form-control">
                        <c:forEach items="${officelist}" var="of">
                           <option value="${of.office_code}">${of.office_name}/(${of.office_code})</option>
                        </c:forEach>
                     </select>
                  </div>
               </td>
            </tr>
            <tr>
               <td>이름</td>
               <td>
                  <div class="col-sm-6 "> 
                     <input type="text" name="college_name" id="college_name" class="form-control">
                  </div>   
               </td>
            </tr>
            <tr>
               <td>설명</td>
               <td>
                  <div class="col-sm-12 "> 
                     <textarea id="college_description" name="college_description" class="form-control"></textarea>
                  </div>
               </td>
            </tr>
         </table>
         
      </form>
      <br>
      <div align="center">
            <a href="collegeList.htm"><button class="btn btn-default">단과대학 목록</button></a>
            <button class="btn btn-warning" data-target="#layerpop" data-toggle="modal">엑셀일괄등록</button>
           <input type="button" value="등록" class="btn btn-success" id="college_reg">
         </div>
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