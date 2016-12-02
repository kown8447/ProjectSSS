<%--
@Project : InitSpring
@File name : noticeWrite.jsp
@Author : 송아름
@Data : 2016.11.18
@Desc : 게시판 공지사항 글쓰기 form
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<h4 style="margin-left: 10%">
	<span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>&nbsp;공지사항 작성</h4>
<br><br>

<div id="content">
	<form method="post" action="" enctype="multipart/form-data">
		<table class="table table-bordered" style="width: 70%; margin: auto;">
			<tr>
				<th style="text-align:center;width:20%;background-color: #F8F4EC;">제목</th>
				<td>
					<input type="text" name="notice_title" id="notice_title" class="form-control"></td>
			</tr>
			<tr>
				<th style="text-align: center;background-color: #F8F4EC;margin-top: 20px;">내용</th>
					<td><textarea name="notice_content" id="notice_content" rows="12" style="resize:none;"class="form-control"></textarea></td>
			</tr>
			<tr>
				<th style="text-align: center;background-color: #F8F4EC;">첨부파일</th>
				<td><div class="form-group">
					<span class="filetype"> 
						<input type="text" class="file-text" />
						<span class="file-btn">찾아보기</span> 
						<span class="file-select">
						<input type="file" class="input-file" name="file" id="file"  value="${notice.notice_file }" readonly>
					</span>
					</span>
				</div>
				</td>	
			</tr>
			<tr>
		</table>
		
		<br>

		<div align="center" style="margin-left:55%;">
			<button type="submit" id="writeBtn" class="btn btn-success btn-sm" style="width:15%;">저장</button>
			<a href="notice.htm" class="btn btn-default btn-sm" style="width:15%;">취소</a>
		</div>
	</form>
</div>