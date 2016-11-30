<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 
   @Project : InitSpring
   @File name : searchPwd.jsp
   @Author : 김영빈
   @Data : 2016.11.22
   @Desc : 비밀번호 찾기 view
-->  
<script src="${pageContext.request.contextPath}/js/login/searchPwd.js"></script>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">×</button>
	<h5>비밀번호 찾기</h5>
</div>

<div class="modal-body">
	<div class="form-group">
		<label for="temp_id" class="col-sm-3 control-label" align="right">아이디</label>
		<div class="col-sm-6">
			<input type="text" class="form-control" id="modal_member_id">
		</div>
	</div>
	<br><br>

	<div class="form-group">
		<label for="member_email" class="col-sm-3 control-label" align="right">이메일</label>
		<div class="col-sm-6">
			<input type="text" class="form-control" id="modal_member_email">
		</div>
	</div>
	<br>
</div>
<div class="modal-footer">
	<input type="button" id="searchPwdBtn" value="찾기" class="btn btn-success" style="width: 20%;">
</div>
