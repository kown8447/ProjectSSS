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
		<table class="table table-bordered" style="width: 70%; margin: auto;">
			<tr>
				<th style="text-align: center;width:120px">제목</th>
				<td>
					<input type="text" name="notice_title" id="notice_title" class="form-control"></td>
			</tr>
			<tr style="height: 45%">
				<th style="text-align: center;">내용</th>
				<!-- <td>
					<textarea id="notice_content" style="height:180px;" name="notice_content" class="form-control"></textarea></td> -->
					<td><textarea name="notice_content" id="notice_content" rows="22" style="width:645px;"></textarea></td>
			</tr>
			<tr>
				<th style="text-align: center;">첨부파일</th>
				<td><div class="form-group">
					<span class="filetype"> <input type="text" class="file-text" />
						<span class="file-btn">찾아보기</span> <span class="file-select">
							<input type="file" class="input-file" name="file" id="file"  value="${notice.notice_file }" readonly>
					</span>
					</span>
				</div>
				</td>	
			</tr>
			<tr>
		</table>
		
		<br>

		<div align="center">
			<button type="submit" id="writeBtn" class="btn btn-success">저장</button>
			<a href="notice.htm" class="btn btn-success">취소</a>
		</div>
	</form>
</div>