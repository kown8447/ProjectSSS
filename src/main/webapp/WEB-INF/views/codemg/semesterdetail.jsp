<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
   @Project : InitSpring
   @File name : semesterdetail.jsp
   @Author : 성홍모
   @Data : 2016.11.09
   @Desc : 학기코드 상세보기 pageView
-->        
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<div class = "row">
<div class = "col-sm-3"></div>
<div class="col-sm-6">
<h4>▶&nbsp;학기 상세정보 </h4>
<br><br>
<c:set value="${semester}" var="sm"/>
<form action="updateSemester.htm" id="updateSemester_form">
	<div style="border: 1px solid green; padding: 3%; border-radius: 1em;  margin: auto;">
	<table class="table">
           <tr>
              <td>학기코드</td>
              <td>
                 <div class="col-sm-6">
                    <input type="text" value="${sm.semester_code }" readonly="readonly" name="semester_code" id="semester_code" class="form-control">
                 </div>
              </td>
           </tr>
           <tr>
              <td>학기 이름</td>
              <td>
                 <div class="col-sm-6">
                    <input type="text" value="${sm.semester_name }" name="semester_name" id="semester_name" class="form-control">
                 </div>
              </td>   
           </tr>
           <tr>
              <td>학기 시작일</td>
              <td>
                 <div class="col-sm-6">
                    <input type="text" value="${sm.semester_start }" name="semester_start" id="semester_start" class="form-control">
                 </div>
              </td> 
           </tr>
           <tr>
              <td>학기 종료일</td>
              <td colspan="3">
                 <div class="col-sm-6">
                    <input type="text" value="${sm.semester_end }" name="semester_end" id="semester_end" class="form-control">
                 </div>
              </td>
           </tr>
        </table>
	</div>
</form>
<div align="center">
<a href="semesterList.htm"><button class="btn btn-default">되돌아가기</button></a>&nbsp;&nbsp;<button type="submit" id ="edit_semester" name="edit_semester" class="btn btn-success">수정하기</button>
 </div>
</div>
</div>

