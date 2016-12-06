<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script src="http://malsup.github.com/jquery.form.js"></script>


   <div class="container" style="width:50%">
   <h4>▶&nbsp;빌딩 등록 </h4>
      <br><br>   
      <div  style="width:90%; margin: auto;">
      <form action="registerBuilding.htm" method="post" id="registerBuilding_form">
         <table class="table ">
            <tr>
               <td>건물명</td>
               <td>
                  <div class="col-sm-6 ">
                     <input type="text" name="building_name" id=building_name class="form-control ">
                  </div>
               </td>
            </tr>
            <tr>
               <td>주소</td>
               <td>
                  <div class="col-sm-6 ">
                     <input type="text" name="building_addr" id="building_addr" class="form-control ">
                  </div>
               </td>
            </tr>
         </table>
         <br>
         
         
      </form>
      
      <div align="center">
         <a href="buildingList.htm"><button class="btn btn-default">빌딩목록</button></a>
         <button class="btn btn-warning" data-target="#layerpop" data-toggle="modal">엑셀일괄등록</button>
         <input type="button" value="등록" class="btn btn-success" id="buildingreg">
      </div>
      <br>
   
      </div>
   </div>
<div class="modal fade" id="layerpop">
   <div class="modal-dialog modal-sm">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">×</button>
            <h5 class="modal-title">빌딩 일괄 등록</h5>
         </div>
         <div class="modal-body">
            <form id="bdexcelForm" action="buildingExcelUpload.htm">
               <span class="filetype"> <input type="text" class="file-text" />
                  <span class="file-btn">찾아보기</span> <span class="file-select">
                     <input id="bdexcel" name="bdexcel" class="input-file" type="file" accept=".xlsx"   placeholder="Excel input">
               </span>
               </span>
            </form>
            Sample File :<a href="buildingexcel.htm">Building.xlsx</a> <br> 
            <font style="font-size: 8pt; color: #A6A6A6;">파일을 다운로드 하여 빌딩을 일괄등록 해보세요</font>
         </div>
         <div class="modal-footer">
            <button type="button" id="bdexcelUp" class="btn btn-success">등록하기</button>   
         </div>
      </div>
   </div>
</div>   