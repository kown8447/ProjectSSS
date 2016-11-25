<%--
@Project : InitSpring
@File name : noticeWrite.jsp
@Author : 송아름
@Data : 2016.11.18
@Desc : 게시판 공지사항 글쓰기 form
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="content">
	<h2>공지사항</h2>
	<form method="post" action="" enctype="multipart/form-data">
		제목<input type="text" name="notice_title" id="notice_title"/><br>
		첨부파일<input type="file" id="file" name="file" /><br>
		<textarea id="notice_content" name="notice_content"></textarea>
		<br> <button type="submit" id="WriteBtn">저장</button> <a href="notice.htm">취소</a>
	</form>
</div>