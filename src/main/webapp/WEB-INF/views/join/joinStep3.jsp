<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
   <!-- 
   @Project : InitSpring
   @File name : joinStep3.jsp
   @Author : 송아름
   @Data : 2016.11.22
   @Desc : 회원가입 step3 단계 기타개인정보 입력 
-->	

<a href="${pageContext.request.contextPath}/login.htm"><img src="${pageContext.request.contextPath}/images/smLogo.png"></a>

<div class="container" style="margin-top:5%;width: 70%;">
	<div class="row">
		<div class="stepwizard">
			<div class="stepwizard-row">
				<div class="stepwizard-step">
					<a class="btn btn-default btn-circle active-step" disabled="disabled"
						data-toggle="tab">1</a>
						<p>Step 1</p>
				</div>
				<div class="stepwizard-step">
					<a class="btn btn-default btn-circle" disabled="disabled" data-toggle="tab">2</a>
					<p>Step 2</p>
				</div>
				<div class="stepwizard-step">
					<a class="btn btn-success btn-circle" data-toggle="tab">3</a>
					<p>Step 3</p>
				</div>
			</div>
		</div>
	</div>
</div>

<div style="border:1px solid green; padding:3%; border-radius: 1em;width:60%;margin: auto;">
	<div class="form-horizontal">
		<form id="memberjoin" method="post" enctype="multipart/form-data">

			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-2">아이디</label>
				<div class="col-sm-6">
					<div class="input-group">
						<input type="text" name="member_id" id="member_id"
							class="form-control"> <span class="input-group-btn">
							<input type="button" id="checkID" value="중복체크"
							class="btn btn-success">
						</span>
					</div>
					<div id="idc-error"></div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-2">비밀번호</label>
				<div class="col-sm-6">
					<input type="password" id="member_pwd" name="member_pwd" class="form-control" placeholder="영문,숫자를 혼합하여 6~15자 ">
					<div id="p-error"></div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-2">비밀번호확인</label>
				<div class="col-sm-6">
					<input type="password" name="member_pwd_confirm" id="member_pwd_confirm" class="form-control">
					<div id="p2-error"></div>
				</div>
			</div>


			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-2">휴대폰번호</label>
				<div class="col-sm-6">
					<input type="text" id="member_phone" name="member_phone"
						placeholder="ex:010-0000-0000" class="form-control">
					<div id="ph-error"></div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-2">우편번호 </label>
				<div class="col-sm-6">
					<div class="input-group">
						<input type="text" id="sample6_postcode" name="addr_num" class="form-control" readonly>
						<span class="input-group-btn">
							<input type="button" onclick="sample6_execDaumPostcode()" class="btn btn-success" value="우편번호 검색">
						</span>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-2">주소 </label>
				<div class="col-sm-6">
					<input type="text" id="sample6_address" name="member_addr" class="d_form large form-control" readonly>
					<input type="text" id="sample6_address2" name="member_addr_detail" class="form-control" placeholder="상세주소">
				</div>
			</div>



			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-2">성별</label>&nbsp;&nbsp;&nbsp;&nbsp;
				<label class="radio-inline">
			    	<input type="radio" name="member_sex" id="member_sex" value="0">남자&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</label> 
				<label class="radio-inline"> 
					<input type="radio" name="member_sex" id="member_sex" value="1">여자
				</label>
			</div>


			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-2">사진</label>
				<div class="col-sm-18">
					<div class="row-fluid">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<span class="filetype span6">
							<input type="text" class="file-text" />
							 <span class="file-btn">찾아보기</span>
							 <span class="file-select span6 "> 
							<input type="file" class="input-file form-control" name="file" id="files" accept=".jpg,.png"><br>
						</span>
						</span>
					</div>
				</div>
			</div>
			
			<div class="form-group">
            	<div id="list" class="col-sm-2 col-sm-offset-4"></div>
         	</div>
		</form>
	</div>
</div>
<br> <br>
<div align="center">
	<input type="button" value="취소" id="cancel" class="btn btn-default"> &nbsp;&nbsp;
	<input type="button" value="회원가입" id="complete" class="btn btn-success">
</div>





