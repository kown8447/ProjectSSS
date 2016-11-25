<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 
   @Project : InitSpring
   @File name : joinStep1_Professor.jsp
   @Author : 김영빈
   @Data : 2016.11.22
   @Desc : 교수 회원가입 2번째 view
-->
<h1>회원가입 Step1</h1>
<table>
	
	<tr>
		<td>교수코드 </td>
		<td><input type="text" name="code" id="code"></td>
	</tr>
	<tr>
		<td>이름</td>
		<td><input type="text" name="code_name" id="code_name"></td>
	</tr>
	<tr>
		<td>
               <label for="inputNumber" class="col-sm-2 control-label" style="width: 30%">생년월일</label>
                  <div>
                  <div class="col-sm-2">
                  <select class="form-control" id="year" style="width:80%">
                     <option id="year" value="0">----</option>
                     <c:forEach var="i" begin="1950" end="2017">
                        <option value="${i}">${i}</option>
                     </c:forEach>
                  </select> <font size="3pt">년</font>
                  </div>

                  <div class="col-sm-2">
                  <select class="form-control" id="month" style="width:80%">
                     <option id="month" value="0">----</option>
                     <c:forEach var="i" begin="1" end="12">
                        <option value="${i}">${i}</option>
                     </c:forEach>
                  </select> <font size="3pt">월</font>
                  </div>
         
                  <div class="col-sm-2">
                  <select class="form-control" id="day" style="width:80%">
                     <option id="day" value="0">----</option>
                     <c:forEach var="i" begin="1" end="31">
                        <option value="${i}">${i}</option>
                     </c:forEach>
                  </select> <font size="3pt">일</font>
                  </div>
              </div>
              </td>
          
         
	</tr>	
</table>
<input type="button" value="다음 단계" id="step1btn">