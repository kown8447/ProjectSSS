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
<form  method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td>이름</td>
			<td><input type="text" name="member_name" id="member_name" value="${member.member_name}" readonly/></td>
			<td>성별</td>
			<c:choose>
				<c:when test="${member.member_sex == 0}">
					<td>남자</td>
					<input type="hidden" name="member_sex" id="member_sex" value="${member.member_sex}" />
				</c:when>
				<c:when test="${member.member_sex == 1}">
					<td>여자</td>
					<input type="hidden" name="member_sex" id="member_sex" value="${member.member_sex}" />
				</c:when>
			</c:choose>

		</tr>
		<tr>
		<se:authorize access="hasRole('ROLE_STUDENT')">
			<td>학번</td>
	 	<td><input type="text" name="student_code" id="student_code" value="${member.student_code}" readonly/></td> 
		</se:authorize>
		<se:authorize access="hasRole('ROLE_PROFESSOR')">
			<td>교수코드</td>
			 <td><input type="text" name="professor_code" id="professor_code" value="${member.professor_code}" readonly/></td> 
		</se:authorize>
		<se:authorize access="hasRole('ROLE_ADMIN')">
			<td>관리자코드</td>
		<td><input type="text" name="admin_code" id="admin_code" value="${member.admin_code}" readonly/></td> 
		</se:authorize>
			
			
		</tr>
		<tr>
			<td>생년월일</td>
			<td><input type="text"  id="member_birth" value="${member.member_birth}" readonly/></td>
			
		</tr> 
		<tr>
			<td>아이디</td>	
			<td><input type="text" name="member_id" id="member_id" value="${member.member_id}" readonly/></td>
			
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" id="member_pwd" name="member_pwd" /></td>
		</tr>
		<tr>
			<td>비밀번호확인</td>
			<td><input type="password" name="member_pwd_confirm"
				id="member_pwd_confirm" /></td>
		</tr>
		<tr>
			<td>주소</td>
			<td><input type="text" id="sample6_postcode" name="addr_num" value="${member.addr_num}" placeholder="우편번호" readonly>
				<input type="button" onclick="sample6_execDaumPostcode()"value="우편번호 찾기"><br>
				<input type="text" id="sample6_address" name="member_addr" value="${member.member_addr}" placeholder="주소" readonly>
				<input type="text" id="sample6_address2" name="member_addr_detail" value="${member.member_addr_detail}" placeholder="상세주소"></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="text" id="member_phone" name="member_phone" value="${member.member_phone}"/></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" id="member_email" name="member_email" value="${member.member_email}" readonly/>
			<input type="button" id="edit_email" value="수정하기"/>

		</tr>
		<se:authorize access="hasRole('ROLE_STUDENT')">
			<tr>
			<td>시간표 공유 여부 </td>
		 	<td><input type="radio" value="1" name="timetable_share"
				id="timetable_share" />허용
				 <input type="radio" value="0" name="timetable_share" 
				id="timetable_share" checked />거부
				</td> 
		</tr>
		</se:authorize> 
		<tr>
			 <td>사진</td>
			 <td>
 <input type="file" id="files" name="file"  accept=".gif, .jpg, .png"/> 
<div id="list"><img src ="../profiles/${member.member_picture}" style=" height: 75px;
    border: 1px solid #000;
    margin: 10px 5px 0 0; "></div>

</td>
			 
		<td>
		</tr>
		
	</table>
																																		
	<input type="submit" id="complete" value="회원정보 수정" />
</form>



<div class="modal fade" id="layerpop" >
  <div class="modal-dialog">
    <div class="modal-content">
      <!-- header -->
      <div class="modal-header">
        <!-- 닫기(x) 버튼 -->
        <button type="button" class="close" data-dismiss="modal">×</button>
        <!-- header title -->
        <h4 class="modal-title">이메일 수정창</h4>
      </div>
      <!-- body -->
      <div class="modal-body">
           이메일
		<input type="text" name="member_email1" id="member_email1"/>
		<input type="button" id="auth_email" value="인증요청하기"/>
		인증번호 입력
		<input type="text" id="auth_key"/>
		<input type="button" id="step2btn" value="인증완료"/>
      </div>
      <!-- Footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>





