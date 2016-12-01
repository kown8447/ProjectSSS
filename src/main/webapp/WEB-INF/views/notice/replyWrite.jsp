<%--
@Project : InitSpring
@File name : noticeWrite.jsp
@Author : 송아름
@Data : 2016.11.23
@Desc : 게시판 공지사항 답글쓰기 form
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="content">
	<h2>공지사항 답글</h2>
	<form method="POST" enctype="multipart/form-data">
	<table class="table table-bordered" style="width:70%">
	
		<tr>
			<td>
			<!-- 히든 필드로 정보 보내기 -->
			<input type="hidden" name="notice_index" value="${notice.notice_index}"/>
			<input type="hidden" name="notice_step" value="${notice.notice_step}"/>
			<input type="hidden" name="notice_refer" value="${notice.notice_refer}"/>
			<input type="hidden" name="notice_depth" value="${notice.notice_depth}"/>
			</td>
			<td align="center"><b>작성자</b></td>
			<td>관리자</td>
		</tr>
		<tr>
			<td align="center"><b>제목</b></td>
			<td colspan="3">
			<input type="text" name="notice_title" value="RE:${notice.notice_title}"></td>
		</tr>
		<tr>
			<td align="center"><b>글내용</b></td>
			<td colspan="3"><textarea rows="7" cols="50" name="notice_content">${notice.notice_content}</textarea>
			</td>
		</tr>
		<tr>
			<td align="center"><b>첨부파일</b></td>
			<td><div class="form-group">
					<span class="filetype"> <input type="text" class="file-text" />
						<span class="file-btn">찾아보기</span> <span class="file-select">
							<input type="file" class="input-file" name="file" id="file"  value="${notice.notice_file }" readonly>
					</span>
					</span>
			</div></td>
		</tr>
		<tr>
			<td colspan="4" align="center">
			<input type="submit" id="replyBtn" value="저장" /> <a href="notice.htm">취소</a>
			</td>
		</tr>
	</table>
	</form>
</div>