<%--
@Project : InitSpring
@File name : noticWrite.jsp
@Author : 송아름
@Data : 2016.11.18
@Desc : 게시판 공지사항 글쓰기 form
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="content">
	<h2>공지사항</h2>

	<form action="" method="post" enctype="multipart/form-data">
		<div id="notice_Title" class="article-detail margin-large">
			<dl class="article-detail-row">
				<dt class="article-detail-title">제목</dt>
				<dd class="article-detail-data">
					&nbsp;<input name="title" />
				</dd>
			</dl>

			<dl class="article-detail-row">
				<dt class="article-detail-title">첨부파일</dt>
				<dd class="article-detail-data">
					&nbsp;<input type="file" id="notice_File" name="notice_File" />
				</dd>
			</dl>
			
			<div class="article-content">
				<textarea id="notice_Content" class="txtContent" name="notice_Content"></textarea>
			</div>

		</div>
		<p class="article-comment margin-small">
			<input class="btn-save button" type="submit" value="등록" id="writeBtn"/> 
			<a class="btn-cancel button" href="notice.htm">취소</a>
		</p>
	</form>
</div>
