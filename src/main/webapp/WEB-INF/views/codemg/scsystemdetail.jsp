<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${scsystem}" var="sc"/>
<div class = "row">
<div class = "col-sm-3"></div>
<div class="col-sm-6"  >
<h4>▶&nbsp;장학제도 상세정보 </h4> <br><br>


<form action="scsytemUpdate.htm" id="scsytemUpdate_form">
   <div style="border: 1px solid green; padding: 3%; border-radius: 1em;  margin: auto;">
       <div class="form-horizontal">
           <div class="form-group">
           		<label class="col-sm-2 control-label col-sm-offset-2">장학코드</label>
           		<div class="col-sm-6">
           			<input type="text" value="${sc.sys_code}" id="sys_code" name="sys_code" readonly="readonly" class="form-control">
           		</div>
           </div>
           
           <div class="form-group">
           		<label class="col-sm-2 control-label col-sm-offset-2">장학명</label>
           		<div class="col-sm-6">
           			<input type="text" value="${sc.scholaship_name}" id="scholaship_name" name="scholaship_name"  class="form-control">
           		</div>
           </div>
           
           <div class="form-group">
           		<label class="col-sm-2 control-label col-sm-offset-2">수혜인원</label>
           		<div class="col-sm-6">
           			<input type="text" value="${sc.scholaship_member}" id="scholaship_member" name="scholaship_member"  class="form-control">
           		</div>
           </div>
           
           <div class="form-group">
           		<label class="col-sm-2 control-label col-sm-offset-2">장학금액</label>
           		<div class="col-sm-6">
           			<input type="text" value="${sc.scholaship_amount }" id="scholaship_amount" name="scholaship_amount"  class="form-control">
           		</div>
           </div>
           
           <div class="form-group">
           		<label class="col-sm-2 control-label col-sm-offset-2">선발기준</label>
           		<div class="col-sm-6">
           			<input type="text" value="${sc.scholaship_standard }" id="scholaship_standard" name="scholaship_standard"  class="form-control">
           		</div>
           </div>
           
           <div class="form-group">
           		<label class="col-sm-2 control-label col-sm-offset-2">장학금 시행</label>
           		<div class="col-sm-6">
           			<c:choose>
                          <c:when test="${sc.scholaship_use ==0}">
                             <input type="radio" value="0" id="scholaship_use" name="scholaship_use" checked> 시행중      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                              <input type="radio" value="1" id="scholaship_use" name="scholaship_use" > 폐지
                           </c:when>
                           <c:when test="${sc.scholaship_use ==1}">
                              <input type="radio" value="0" id="scholaship_use" name="scholaship_use" >시행중       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                              <input type="radio" value="1" id="scholaship_use" name="scholaship_use" checked> 폐지
                           </c:when>
                     </c:choose> 
           		</div>
           </div>
              
            <div class="form-group">
           		<label class="col-sm-2 control-label col-sm-offset-2">상세정보</label>
           		<div class="col-sm-6">
           			<textarea  rows="7" id="scholaship_note" name="scholaship_note"  class="form-control"></textarea>
                        <script>
                           $('#scholaship_note').val("${sc.scholaship_note}");
                       </script>
           		</div>
           </div>        
        </div>
   </div>
</form>

<br><br>
<div align="center">
      <a href="scSystemList.htm"class="btn btn-default">목록</a> &nbsp;&nbsp;
      <button type="submit" id ="edit_scsystem" class="btn btn-success">수정하기</button>
</div>
</div>
</div>
