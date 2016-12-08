<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<div class="container" style="width:60%">
<h4>▶&nbsp;빌딩 상세정보 </h4><br><br>

<form action="updateBuilbilding.htm" id="updateBuilbilding_form">
   <div style="border: 1px solid green; padding: 3%; border-radius: 1em; width: 90%; margin: auto;">
      <div class="form-horizontal">
         <div class="form-group">
         <label class="col-sm-2 control-label col-sm-offset-2">빌딩코드</label>
            <div class="col-sm-6">
               <input type="text" class="form-control" id="building_code" name="building_code" readonly="readonly" value="${building.building_code }">
            </div>
         </div>
      
         <div class="form-group">
         <label class="col-sm-2 control-label col-sm-offset-2">건물명</label>
            <div class="col-sm-6">
               <input type="text" id="building_name" name="building_name" value="${building.building_name }"  class="form-control"> 
            </div>
         </div>
     
         <div class="form-group">
         <label class="col-sm-2 control-label col-sm-offset-2">주소</label>
            <div class="col-sm-6">
               <input type="text" id="building_addr" name="building_addr" value="${building.building_addr }" class="form-control"> 
            </div>
         </div>
      </div>         
   </div>
   <br><br>
</form>
<div align="center">
   <a href="buildingList.htm"><button class="btn btn-default">목록</button></a> &nbsp;&nbsp;
   <button type="submit" id="edit_building" class="btn btn-success">수정하기</button>
</div>
<div style="height:15%;"></div>	
</div>
   