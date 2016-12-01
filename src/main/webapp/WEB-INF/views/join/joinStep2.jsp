<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 
   @Project : InitSpring
   @File name : joinStep2.jsp
   @Author : 송아름
   @Data : 2016.11.22
   @Desc :  회원가입 step2 view 이메일 인증 
-->
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script> 
<a href="${pageContext.request.contextPath}/login.htm"><img src="${pageContext.request.contextPath}/images/smLogo.png"></a>


<div class="container" style="margin-top: 5%; width: 70%;">
	<div class="row">
		<div class="stepwizard">
			<div class="stepwizard-row">
				<div class="stepwizard-step">
					<a class="btn btn-default btn-circle active-step"
						disabled="disabled" data-toggle="tab">1</a>
					<p>Step 1</p>
				</div>
				<div class="stepwizard-step">
					<a class="btn btn-success btn-circle" href="#step-2"
						data-toggle="tab">2</a>
					<p>Step 2</p>
				</div>
				<div class="stepwizard-step">
					<a class="btn btn-default btn-circle" disabled="disabled"
						data-toggle="tab">3</a>
					<p>Step 3</p>
				</div>
			</div>
		</div>
	</div>
</div>

<div
	style="border: 1px solid green; padding: 3%; border-radius: 1em; width: 60%; margin: auto;">
	<div class="form-horizontal">
		<div class="form-group">
			<label class="col-sm-2 control-label col-sm-offset-2">이메일</label>
			<div class="col-sm-6">
				<div class="input-group">
					<input type="text" name="member_email" id="member_email"
						class="form-control"> <span class="input-group-btn">
						<input type="button" id="auth_email" value="요청하기"
						class="btn btn-success">
					</span>
				</div>
			</div>
		</div>


		<div class="form-group">
			<label class="col-sm-2 control-label col-sm-offset-2">인증번호</label>
			<div class="col-sm-6">
				<input type="text" id="auth_key" class="form-control">
			</div>
		</div>
	</div>
</div>
<br> <br>
<div align="center">
	<input type="button" value="취소" id="cancel" class="btn btn-default">
	&nbsp;&nbsp; <input type="button" value="다음 단계" id="step2btn"
		class="btn btn-success">
</div>

<div class="modal fade" id="email_layerpop">
	<div class="modal-dialog">
		<div class="character">
			<div class="head">
				<div class="eyes">. .</div>
			</div>
			<div class="body"></div>
			<img src="https://cdn4.iconfinder.com/data/icons/aiga-symbol-signs/439/aiga_mail-512.png" />
			<div class="shadow"></div>
		</div>	
	</div>
</div>