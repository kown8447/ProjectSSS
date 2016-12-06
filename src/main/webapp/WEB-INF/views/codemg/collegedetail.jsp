<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h4>▶&nbsp;단과대학 상세정보 </h4>
<br><br>   
<form action="updateColleage.htm" id="updateColleage_form">
  <div style="border: 1px solid green; padding: 3%; border-radius: 1em; width: 40%; margin: auto;">
      <div class="form-horizontal">
         <div class="form-group">
         <label class="col-sm-3 control-label col-sm-offset-1">단대코드</label>
            <div class="col-sm-6">
               <input type="text" id="college_code" name="college_code" value="${college.college_code}" class ="form-control" readonly="readonly">
            </div>
         </div>
      </div>
      <div class="form-horizontal">
         <div class="form-group">
         <label class="col-sm-3 control-label col-sm-offset-1">사무실코드</label>
            <div class="col-sm-6">
               <input type="text" id="office_code" name="office_code" value="${college.office_code}" class ="form-control">
            </div>
         </div>
      </div>
      <div class="form-horizontal">
         <div class="form-group">
         <label class="col-sm-3 control-label col-sm-offset-1">이름</label>
            <div class="col-sm-6">
               <input type="text" id="college_name" name="college_name" value="${college.college_name}" class ="form-control">
            </div>
         </div>
      </div>   
      <div class="form-horizontal">
         <div class="form-group">
         <label class="col-sm-3 control-label col-sm-offset-1">설명</label>
            <div class="col-sm-6">
               <input type="text" id="college_description " name="college_description" value="${college.college_description}" class ="form-control">
            </div>
         </div>
      </div>   
   </div>
</form>
<div align="center">
      <a href="collegeList.htm"><button class="btn btn-default">목록</button></a> &nbsp;&nbsp;<button type="submit" id="edit_college" class="btn btn-success">수정하기</button>
</div>