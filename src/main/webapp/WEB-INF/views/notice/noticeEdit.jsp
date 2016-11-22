<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="content">
	<h2>공지사항</h2>

<form method="POST" enctype="multipart/form-data">
	<table width="90%" border="1">
		<tr>
			<td width="20%" align="center"><b> 글번호 </b></td>
			<td width="30%">${notice.notice_index}
			<input type="hidden" name="notice_index" value="${notice.notice_index}"></td>
			<td width="20%" align="center"><b>작성일</b></td>
			<td>${notice.notice_date}</td>
		</tr>
		<tr>
			<td width="20%" align="center"><b>글쓴이</b></td>
			<td width="30%">관리자</td>
		</tr>
		<tr>
			<td width="20%" align="center"><b>제목</b></td>
			<td colspan="3"><input type="text" name="notice_title"
				value="${notice.notice_title}" size="40"></td>
		</tr>
		<tr height="100">
			<td width="20%" align="center"><b>글내용</b></td>
			<td colspan="3"><textarea rows="7" cols="50" name="notice_content">${notice.notice_content}</textarea>
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
			<td colspan="4" align="center"><input type="submit" value="수정하기">
			</td>
		</tr>
	</table>
	</form>
</div>
