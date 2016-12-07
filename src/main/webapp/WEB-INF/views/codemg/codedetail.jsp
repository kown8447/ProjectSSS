<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<div class = "row">
<div class = "col-sm-3"></div>
<div class="col-sm-6"  >
<h4>▶&nbsp;회원 상세정보 </h4>
<br><br>
<c:set value="${code}" var="c"></c:set>
<form action="updateCode.htm" method="post" id="updateCode_form">
   <div style="border: 1px solid green; padding: 3%; border-radius: 1em;  margin: auto;">
      <table class="table">
           <tr>
              <td>코드번호</td>
              <td>
                 <div class="col-sm-12">
                    <input type="text" value="${c.code}" readonly="readonly" name="code" id="code" class="form-control">
                 </div>
              </td>
              <td>구분</td>
              <td>
                 <div class="col-sm-6">
                    <input type="hidden" value="${c.code_type}" name="code_type" id="code_type">
                    <c:choose>
                      <c:when test="${c.code_type==0 }">
                         <input type="text" value="학생" readonly="readonly" class="form-control">
                      </c:when>
                      <c:when test="${c.code_type==1 }">
                         <input type="text" value="교수" readonly="readonly" >
                      </c:when>
                      <c:when test="${c.code_type==2 }">
                         <input type="text" value="관리자" readonly="readonly" >   
                      </c:when>
                   </c:choose>
                 </div>
              </td>   
           </tr>
           
           <tr>
              <td>이름</td>
              <td >
                 <div class="col-sm-12">
                    <input type="text" value="${c.code_name }" name="code_name" id="code_name" class="form-control">
                 </div>
              </td>
           
           </tr>
           <tr>
              <td>생년월일</td>
              <td >
                 <div class="col-sm-12">
                    <input type="text" value="${c.code_birth }" name="code_birth" id="code_birth" class="form-control">
                 </div>
              </td>
           </tr>
        </table>
    </div>
 </form>
 <div align="center">
      <a href="typeofcodelist.htm?code_type=0"><button class="btn btn-default">목록</button></a> &nbsp;&nbsp;<button type="submit" id ="edit_student" class="btn btn-success">수정하기</button>
 </div>
</div>
</div>