<%--
@Project : InitSpring
@File name : noticeEdit.jsp
@Author : 송아름
@Data : 2016.11.18
@Desc : 게시판 공지사항 글수정하기 form
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="content">
	<h2>공지사항 수정</h2>

<form method="POST" enctype="multipart/form-data">
	<table border="1">
		<tr>
			<td width="20%" align="center"><b> 글번호 </b></td>
			<td width="30%">${notice.notice_index}</td>
			<td width="20%" align="center"><b>작성일</b></td>
			<td>${notice.notice_date}</td>
		</tr>
		<tr>
			<td width="20%" align="center"><b>글쓴이</b></td>
			<td width="30%">관리자</td>
		</tr>
		<tr>
			<td width="20%" align="center"><b>제목</b></td>
			<td colspan="3">
			<input type="text" name="notice_title" id="notice_title" value="${notice.notice_title}" size="40"></td>
		</tr>
		<tr height="100">
			<td width="20%" align="center"><b>글내용</b></td>
			<td colspan="3"><textarea rows="7" cols="50" id="notice_content" name="notice_content">${notice.notice_content}</textarea>
			</td>
		</tr>
		<tr>
			<td width="20%" align="center"><b>첨부파일</b></td>
			<td colspan="3">${notice.notice_file}
			<br /> 
			<input type="file" name="file">
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center"><input type="submit" id="editBtn"value="수정하기"><a href="noticeDetail.htm">취소</a>
			</td>
		</tr>
	</table>
	</form>
</div>
