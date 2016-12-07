<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${department}" var="dp"></c:set>
<h4>▶&nbsp;학과 상세정보 </h4>
<br><br>   
<form action="updateDepartment.htm" id="updateDepartment_form">
   <div style="width:60%; margin: auto;">
      <table class="table">
         <tr>
            <td>단대코드</td>
            <td>
               <div class="col-sm-6">
                  <input type="text" id="college_code" name="college_code" value="${dp.college_code}" class="form-control" readonly>
               </div>
            </td>
            <td>학과코드</td>
            <td>
               <div class="col-sm-6">
                        <input type="text" id="department_code" name="department_code" value="${dp.department_code}" class="form-control" readonly="readonly">
                  </div>
            </td>
            
         </tr>
         <tr>
            <td>학과명</td>
            <td>
               <div class="col-sm-6">
                        <input type="text" id="department_name" name="department_name"  class="form-control" value="${dp.department_name}"  >
                  </div>
            </td>
            <td> 사무실코드   </td>
            <td>
               <div class="col-sm-6">
               	  	<select id="office_code" name="office_code" class="form-control">
                        <c:forEach items="${office}" var="of">
                           <option value="${of.office_code}">${of.office_name}(${of.office_code})</option>
                        </c:forEach>
                     </select>
               </div>
            </td>
         </tr>
         <tr>
            <td>정원</td>
            <td>
               <div class="col-sm-6">
                        <input type="text" id="department_seat" name="department_seat"   value="${dp.department_seat}" class="form-control" >
                  </div>
            </td>
            <td>졸업학점</td>
            <td>
               <div class="col-sm-6">
                        <input type="text" id="graduation_credit"  name="graduation_credit" value="${dp.graduation_credit}" class="form-control" >
                  </div>
            </td>
         </tr>
         <tr>
            <td>학과 존재여부</td>
            <td colspan="3">   
               <div class="col-sm-9">
                  <c:choose>
                          <c:when test="${dp.department_exist ==0}">
                             <input type="radio" value="0" id="department_exist" name="department_exist" checked> 유지       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                              <input type="radio" value="1" id="department_exist" name="department_exist" > 폐지
                           </c:when>
                           <c:when test="${dp.department_exist ==1}">
                              <input type="radio" value="0" id="department_exist" name="department_exist" >유지       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                              <input type="radio" value="1" id="department_exist" name="department_exist" checked> 폐지
                           </c:when>
                        </c:choose> 
                        
                  </div>
            </td>
         </tr>
         <tr>
            <td >설명</td>
            <td colspan="3">
               <div class="col-sm-9">
                        <textarea rows="5" id="department_description" class="form-control" name="department_description"  class="form-control"  value="${dp.department_description}" ></textarea>
                     <script>
                        $('#department_description').val("${dp.department_description}");
                     </script>
                  </div>
            </td>
         </tr>
      </table>
   </div>   
   <input type="hidden" id="double_possible" class="form-control"  name="double_possible" value="${dp.double_possible}" class="form-control" >
                  
</form>

<div align="center">
      <a href="departmentlist.htm"><button class="btn btn-default">목록</button></a> &nbsp;&nbsp;<button type="submit" id ="edit_depart" class="btn btn-success">수정하기</button>
</div>