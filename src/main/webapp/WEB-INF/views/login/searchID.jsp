<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 
   @Project : InitSpring
   @File name : searchID.jsp
   @Author : 김영빈
   @Data : 2016.11.22
   @Desc : 아이디 찾기 view
-->


<div class="modal fade" id="layerpop">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h4>아이디 찾기</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label for="member_name" class="col-sm-2 control-label"
						style="width: 30%">이름</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="member_name"
							name="member_name">
					</div>
				</div>

				<div class="form-group">
					<label for="member_email" class="col-sm-2 control-label"
						style="width: 30%">이메일</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="member_email"
							name="member_email">
					</div>
				</div>

			</div>
			<div class="modal-footer">
				<input type="button" id="searchIdBtn" value="찾기"
					class="btn btn-success" data-dismiss="modal">
			</div>
		</div>
	</div>
</div>



<div id="result"></div>
