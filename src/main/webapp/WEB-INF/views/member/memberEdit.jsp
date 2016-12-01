<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="se" uri="http://www.springframework.org/security/tags" %>   
<!-- 
   @Project : InitSpring
   @File name : memberEdit.jsp
   @Author : 김영빈
   @Data : 2016.11.22
   @Desc : 회원정보 수정 view 
-->   
<div class="container" style="margin-top: 5%; ">

<h3>${member.member_id}님 정보 수정 </h3>
<br>
<div class="form">
<form  method="post" enctype="multipart/form-data">
<table class="table table-bordered" style="undefined; width:70%">
<tbody>
  <tr>
    <th class="col-sm-3" rowspan="10" style="display: table-cell; vertical-align: middle;">
      <div id="list" style="text-align: center; "><img src ="../profiles/${member.member_picture}"  class="img-rounded" style="height: 150px;  border: 1px solid #000; "></div>
      <br>
       <label class="btn btn-default btn-file col-sm-offset-3">
             찾아보기  <input type="file" class="input-file" name="file" id="files" accept=".jpg,.png">
   </label>
                
    </th>
    
    <th class="col-sm-2" style="text-align: center; display: table-cell; vertical-align: middle;"><b>이름</b></th>
    <th class="col-sm-3"><input type="text" name="member_name" id="member_name"  class="form-control" value="${member.member_name}"  readonly/></th>
    <th class="col-sm-2" style="text-align: center ; display: table-cell; vertical-align: middle;"><b>성별</b></th>
    <th class="col-sm-3">
       <c:choose>
         <c:when test="${member.member_sex == 0}">
            <input type="text" value="남자" class="form-control" readonly/>
            <input type="hidden" name="member_sex" id="member_sex" value="${member.member_sex}" />
         </c:when>
         <c:when test="${member.member_sex == 1}">
         <div class="col-sm-12">
            <input type="text" value="여자" class="form-control" readonly/>
            <input type="hidden" name="member_sex" id="member_sex" value="${member.member_sex}"  />
         </div>   
         </c:when>
      </c:choose>
   </th>
  </tr>

  <tr>
     <se:authorize access="hasRole('ROLE_STUDENT')">
      <td style="display: table-cell; vertical-align: middle; "><b>학번</b></td>
      <td colspan="3" ><input type="text" name="student_code" id="student_code" value="${member.student_code}" class="form-control" readonly/></td> 
   </se:authorize>
         <se:authorize access="hasRole('ROLE_PROFESSOR')">
         <td style="display: table-cell; vertical-align: middle; "><b>교수코드</b></td>
            <td colspan="3" ><input type="text" name="professor_code" id="professor_code" value="${member.professor_code}" class="form-control" readonly/></td> 
         </se:authorize>   
         <se:authorize access="hasRole('ROLE_ADMIN')">
         <td style="display: table-cell; vertical-align: middle; "><b>관리자코드</b></td>
         <td colspan="3" ><input type="text" name="admin_code" id="admin_code" value="${member.admin_code}" class="form-control"  readonly/></td> 
         </se:authorize>
  </tr>
  <tr>
       <td style="display: table-cell; vertical-align: middle; "><b>생년월일</b></td>
      <td colspan="3" ><input type="text"  id="member_birth" value="${member.member_birth}" class="form-control"  readonly/></td>
  </tr>
  <tr>
   <td style="display: table-cell; vertical-align: middle; "><b>아이디</b></td>   
         <td colspan="3" ><input type="text" name="member_id" id="member_id" value="${member.member_id}" class="form-control" readonly/></td>
  </tr>
  <tr>
      <td style="display: table-cell; vertical-align: middle; "> <b>비밀번호</b></td>
         <td colspan="3" ><input type="password" id="member_pwd" name="member_pwd" class="form-control" /></td>
  </tr>
  <tr>
         <td style="display: table-cell; vertical-align: middle; "><b>비밀번호확인</b></td>
         <td colspan="3" ><input type="password" name="member_pwd_confirm"    id="member_pwd_confirm" class="form-control" /></td>
  </tr>
  <tr>
    <td style="display: table-cell; vertical-align: middle; "><b>주소</b></td>
    <td colspan="3" >
       <div class="input-group">
             <input type="text" id="sample6_postcode" name="addr_num" value="${member.addr_num}" placeholder="우편번호" class="form-control" readonly>
            <span class="input-group-btn"><input type="button" class="btn btn-success" onclick="sample6_execDaumPostcode()"value="우편번호 찾기"></span>
      </div>   

      <div>
            <input type="text" id="sample6_address" name="member_addr" value="${member.member_addr}" class="form-control" placeholder="주소" readonly>
            <input type="text" id="sample6_address2" name="member_addr_detail" value="${member.member_addr_detail}" class="form-control" placeholder="상세주소">
      </div>
   </td>
  </tr>
  <tr>
    <td style="display: table-cell; vertical-align: middle; "><b>전화번호</b></td>
   <td    colspan="3"><input type="text" id="member_phone" name="member_phone" value="${member.member_phone}" class="form-control"/></td>
  </tr>
  <tr>
    <td style="display: table-cell; vertical-align: middle; "><b>이메일</b></td>
   <td colspan="3">
   <div class="input-group">
   <input type="text" id="member_email" name="member_email" value="${member.member_email}" class="form-control" readonly/>
      <span class="input-group-btn"><input type="button" class="btn btn-success" id="edit_email" value="수정하기"/></span>
   </div>
  </tr>
  <se:authorize access="hasRole('ROLE_STUDENT')">
  <tr>
    <td style="display: table-cell; vertical-align: middle; "><b>시간표 공유 여부</b> </td>
   <td colspan="3">
   <div  style="float:left;">
      <label class="radio-inline">
         <input type="radio" value="1" name="timetable_share" id="timetable_share" />허용
      </label>
      <label class="radio-inline">
         <input type="radio" value="0" name="timetable_share" id="timetable_share" checked />거부
      </label>
      </div>
   </td>
  </tr>
  </se:authorize>
  </tbody>
</table>
<div class="col-sm-offset-6">
<input type="reset" class="btn btn-warning" id="refer" value="취소" />
<input type="submit" class="btn btn-success" id="complete" value="회원정보 수정" />
</div>

</form>
</div>
</div>
   
<!-- 이메일 수정 모달 -->
<div class="modal fade" id="layerpop" >
  <div class="modal-dialog">
    <div class="modal-content">
      <!-- header -->
      <div class="modal-header">
        <!-- 닫기(x) 버튼 -->
        <button type="button" class="close" data-dismiss="modal">×</button>
        <!-- header title -->
        <h4 class="modal-title">이메일 수정</h4>
      </div>
      <!-- body -->
      <div class="modal-body">
      <div class="form-horizontal">
      <div class="form-group">
         <label class="col-sm-2 control-label col-sm-offset-1">이메일</label>
         <div class="col-sm-8">
            <div class="input-group">
               <input type="text" name="member_email1" id="member_email1"
                  class="form-control"> <span class="input-group-btn">
                  <input type="button" id="auth_email" value="인증요청하기" class="btn btn-success">
               </span>
            </div>
         </div>
      </div>


      <div class="form-group">
         <label class="col-sm-2 control-label col-sm-offset-1">인증번호</label>
         <div class="col-sm-8">
            <input type="text" id="auth_key" class="form-control">
            
         </div>
      
      </div>
   </div>
        
      <!-- Footer -->
      <div class="modal-footer">
        <input type="button" class="btn btn-success" id="step2btn" value="인증완료"/>
      </div>
      
    </div>
  </div>
</div>
</div>

<div class="modal fade" id="pwdEmail_layerpop2">
	<div class="modal-dialog">


		<div id="mail-container">
			<div id="line-container">
				<div class="line line-1"></div>
				<div class="line line-4"></div>
				<div class="line line-2"></div>
				<div class="line line-5"></div>
				<div class="line line-3"></div>
			</div>
			<div id="mail"></div>
		</div>


	</div>
</div>