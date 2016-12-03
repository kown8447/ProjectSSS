<%--
@Project : InitSpring
@File name : noticeEdit.jsp
@Author : 송아름
@Data : 2016.11.18
@Desc : 게시판 공지사항 글수정하기 form
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<h4 style="margin-left: 10%">
	<span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>&nbsp;공지사항 수정</h4>
<br><br>

<div id="content">
<form method="POST" enctype="multipart/form-data">
	<table class="table table-bordered" style="width:70%; margin: auto;" >
		<tr>
			<th style="text-align: center;background-color: #F8F4EC;" >글번호</th>
			<td style="text-align: center; width:30%; ">${notice.notice_index}</td>
			<th style="text-align: center; background-color: #F8F4EC;">작성일</th>
			<td style="text-align: center;">${notice.notice_date}</td>
		</tr>
		<tr>
			<th style="text-align: center;background-color: #F8F4EC;">작성자</th>
			<td colspan="3">관리자</td>
		</tr>
		<tr>
			<th style="text-align: center;background-color: #F8F4EC;">제목</th>
			<td colspan="3">
			<input type="text" name="notice_title" id="notice_title"  class="form-control" value="${notice.notice_title}"></td>
		</tr>
		<tr>
			<th style="text-align: center;background-color: #F8F4EC;">글내용</th>
			<td colspan="3"><textarea rows="7" class="form-control" id="notice_content" name="notice_content">${notice.notice_content}</textarea>
			</td>
		</tr>
		<tr>
			<th style="text-align: center;background-color: #F8F4EC;">첨부파일</th>
			<td colspan="3">${notice.notice_file}
			<input type="file" name="file">
			</td>
		</tr>
	</table>

	<br>
		<div align="center" style="margin-left:55%;">
			<input type="submit" id="editBtn" value="수정하기"  class="btn btn-success btn-sm" style="width:15%;">
			<a  href="notice.htm" class="btn btn-default btn-sm" style="width:15%;">취소</a>
		</div>
	</form>
</div>
